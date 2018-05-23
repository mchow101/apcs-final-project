import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MapGraphics extends JPanel implements KeyListener {
	private JFrame frame;

	private int dim;
	private Charecter MtD;
	private Map map;
	private ArrayList<Creature> enemy; // contains live enemies
	private ArrayList<String> toDisplay; // stats, notifications, etc. to be displayed
	private ArrayList<Item> inventory; // items available for use
	private int base = 5; // for item selection
	private int selected = base;

	public MapGraphics(int dim) {
		this.dim = dim;
		MtD = new Charecter();
		map = new Map(dim);

		JPanel panel = new JPanel();
		this.setPreferredSize(new Dimension(dim, dim));
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(this);

		frame = new JFrame("Super Fun Game");
		frame.setSize(dim, dim);
		frame.setLocation(dim/3, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(panel);
		frame.pack();
		frame.repaint();
		frame.setVisible(true);

		addKeyListener(this);
		this.setFocusable(true);

		enemy = new ArrayList<Creature>();
		toDisplay = new ArrayList<String>();
		inventory = new ArrayList<Item>();
		
		// random enemies
		int randomx;
		int randomy;
		for (int i = 0; i < 10; i++) {
			randomx = (int) (Math.random() * 49) + 1;
			randomy = (int) (Math.random() * 49) + 1;

			if (map.getLevel()[randomy][randomx].canContainMonster()) {
				if (Math.random() > .5)
					enemy.add(new Bryce(randomy, randomx));
				else 
					addKais(randomy, randomx);
				
			} else
				i--;
		}
						
		for (int i = 0; i < MtD.stats().length; i++) {
			toDisplay.add(MtD.stats()[i]);
		}
			
	}

	private void addKais(int y, int x) {
		
		int randomx;
		int randomy;
		
		for (int i = 0; i < ((int) Math.random() * 8) + 8; i++) {
			randomx =(int) Math.random()*9 - 4;
			randomy =(int) Math.random()*9 - 4;
			
			
			if (!(randomy+y >= 50 || randomx+x >= 50)) {
			if (map.getLevel()[randomy+y][randomx+x].canContainMonster())
			enemy.add(new KaiH(randomy+y, randomx+x));
			}
			
		}
		
	}

	public void setEnemy(ArrayList<Creature> enemy) {
		System.out.println(enemy.size());
		this.enemy = enemy;
	}

	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, dim, dim);
		g.setColor(Color.WHITE);
		// draw map
		if (!MtD.isDead()) {
			map.drawMap(g);
		}
		// display game over screen
		else {
			Image image = Toolkit.getDefaultToolkit().getImage("src/game-over1.jpg");
			// image from https://experiencesminimalistes.com/2016/12/29/burn-out-saisonnier-de-la-quarantaine-en-crise/
			g.drawImage(image, 50, 5, 500, 375, this);
		}
		this.repaint();

		Runner.updateDisplay(toDisplay);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent event) {
		// key press advances turn
		if (!MtD.isDead()) {
			if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				move(MtD.getX(), MtD.getX() + 1, MtD.getY(), MtD.getY(), 1, 0, MtD);
				updateEnemies();
			} else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				move(MtD.getX(), MtD.getX() - 1, MtD.getY(), MtD.getY(), -1, 0, MtD);
				updateEnemies();
			} else if (event.getKeyCode() == KeyEvent.VK_UP) {
				move(MtD.getX(), MtD.getX(), MtD.getY(), MtD.getY() - 1, 0, -1, MtD);
				updateEnemies();
			} else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
				move(MtD.getX(), MtD.getX(), MtD.getY(), MtD.getY() + 1, 0, 1, MtD);
				updateEnemies();
			} else {
				char key = event.getKeyChar();
				act(key);
			}

			updateStats();
			updateInventory();
		}
	}

	public Tile[][] getMap() {
		System.out.println("double nice");
		return map.getLevel();
	}

	public void setMap(Map map) {
		this.map = map;
	}

	// checks for valid spaces and performs an action
	public void move(int x1, int x2, int y1, int y2, int dx, int dy, Creature thing) {
		// check and move
		if (map.getLevel()[y2][x2].canContainMtD()) {
			map.getLevel()[y1][x1] = thing.getTile();
			thing.setTile(map.getLevel()[y2][x2]);
			thing.setDx(dx);
			thing.setDy(dy);
			thing.move(MtD, map);
			map.getLevel()[thing.getY()][thing.getX()] = MtD;

		}
		// open door
		else if (map.getLevel()[y2][x2] instanceof Door) {
			((Door) map.getLevel()[y2][x2]).setOpen(true);
		}
		// attack enemy
		else if (map.getLevel()[y2][x2] instanceof Creature) {
			MtD.attack((Creature) map.getLevel()[y2][x2], map);
		}
		// randomly regenerate health
		if (MtD.getStrength() > Math.random() * 25 && MtD.getHealth() != MtD.getMaxHealth()) {
			MtD.setHealth(MtD.getHealth() + 1);
		}
		
		// reset tracking of inventory
		if (selected >= inventory.size()) selected = base;
	}
	
	// update enemies
	public void updateEnemies() {
		for (int i = 0; i < enemy.size(); i++) {
			map.getLevel()[enemy.get(i).getY()][enemy.get(i).getX()] = enemy.get(i).getTile();
			// check health
			if (enemy.get(i).isDead()) {
				enemy.remove(i);
				break;
			}
			// move
			enemy.get(i).move(MtD, map);

			if (map.getLevel()[enemy.get(i).getY()][enemy.get(i).getX()].canContainMonster()) {
				map.getLevel()[enemy.get(i).getY()][enemy.get(i).getX()] = (Tile) enemy.get(i);
			} else {
				enemy.get(i).setX(enemy.get(i).getPrevX());
				enemy.get(i).setY(enemy.get(i).getPrevY());
			}
		}
	}
	
	// update player stats
	public void updateStats() {
		toDisplay.clear();
		inventory.clear();
		for (int i = 0; i < MtD.stats().length; i++) {
			toDisplay.add(MtD.stats()[i]);
		}
	}
	
	// update inventory
	public void updateInventory() {
		toDisplay.add("Inventory");
		for (int i = 0; i < map.getLevel().length; i++) {
			for (int j = 0; j < map.getLevel()[i].length; j++) {
				if (map.getLevel()[i][j] instanceof Item && (((Item) map.getLevel()[i][j]).canUse(MtD.getX(), MtD.getY()))) {
					toDisplay.add("Press " + (((Item) map.getLevel()[i][j]).key() + " to " + (((Item) map.getLevel()[i][j]).action()).toLowerCase() + 
							" " + (((Item) map.getLevel()[i][j]).getType())).toLowerCase());
					inventory.add((Item) map.getLevel()[i][j]);
					((Item) map.getLevel()[i][j]).setIndex(inventory.size() - 1);
				}
			}
		}

		if (MtD.getTile() instanceof Item && ((Item) MtD.getTile()).canUse(MtD.getX(), MtD.getY())) {
			toDisplay.add("Press " +  (((Item) MtD.getTile()).key()) + " to " + (((Item) MtD.getTile()).action()).toLowerCase() + " " + (((Item) MtD.getTile()).getType()).toLowerCase());
			inventory.add((Item) MtD.getTile());
			((Item) MtD.getTile()).setIndex(inventory.size() - 1);
		}
	}

	// perform an action if applicable
	public void act(char key) {
		if (key == ' ') {
			if(selected - base < inventory.size() - 1) selected++;
			else selected = base;
		}
		
		for(int i = 0; i < inventory.size(); i++) {
			if(selected - base == inventory.get(i).getIndex()) {
				switch (key) {
				case ('q'): // Quaff a potion
					if(inventory.get(i) instanceof Potion) {
						((Potion) inventory.get(i)).quaff(MtD);
						MtD.setTile(new EmptySpace());
					}
					break;
				case ('w'): // wear/wield an item
					break;
				case ('e'): // Equipment list
					break;
				case ('r'): // Read a scroll
					break;
				case ('t'): // Take of an item
					break;
				case ('i'): // Open inventory
					break;
				case ('o'): // Open a door
					if(inventory.get(i) instanceof Door) {
						((Door) inventory.get(i)).setOpen(true);
					}
					break;
				case ('p'): // Cast a prayer
					break;
				case ('l'): // Look a direction
					break;
				case ('m'): // Cast a magic spell
					break;
				case ('j'): // Jam a door
					break;
				case ('s'): // Stairs
					//what is search
					if(inventory.get(i) instanceof Stairs)
						map.setLvl(map.getLvl() + 1);
					break;
				case ('u'): // Use a staff
					break;
				case ('c'): // Close a Door
					if(inventory.get(i) instanceof Door) {
						((Door) inventory.get(i)).setOpen(false);
					}
					break;
				case ('d'): // Drop an item
					break;
				case ('f'): // fire/throw an item
					break;
				case ('b'): // Browse a book
					break;
				case ('C'): // Display character
					break;
				case ('D'): // Disarm a trap
					break;
				case ('E'): // Eat some food
					break;
				case ('F'): // Fill a lamp with fuel
					break;
				case ('R'): // Rest for x amount of time
					break;
				case ('a'): // aim/fire a wand
					break;
				case ('z'): // Zap a rod
					break;
				case ('T'): // Tunnel into the earth.
					break;
				}
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	public ArrayList<String> getToDisplay() {
		return toDisplay;
	}

	public void setToDisplay(ArrayList<String> toDisplay) {
		this.toDisplay = toDisplay;
	}

	public ArrayList<Item> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	public int getSelectedIndex() {
		return selected;
	}

	public ArrayList<Creature> getEnemy() {
		System.out.println("goood");
		return enemy;
	}
}
