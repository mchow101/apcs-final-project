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
	private int eNum;
	private ArrayList<String> toDisplay; // stats, notifications, etc. to be displayed
	private ArrayList<Item> inventory; // items available for use
	private int base = 8; // for item selection
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
		frame.setLocation(dim / 3 + 10, 0);
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
		genEnemies(5);
						
		for (int i = 0; i < MtD.stats().length; i++) {
			toDisplay.add(MtD.stats()[i]);
		}
			
	}

	private void genEnemies(int size) {
		double random;
		int randomx;
		int randomy;
		for (int i = 0; i < size; i++) {
			randomx = (int) (Math.random() * 49) + 1;
			randomy = (int) (Math.random() * 49) + 1;
			random = Math.random();
			
			if (map.getLevel()[randomy][randomx].canContainMonster()) {
				if (random > .75) 
					enemy.add(new Bryce(randomy, randomx));
				else if (random > .5)
					addKais(randomy, randomx);
				else if (random > .25)
					enemy.add(new Cammy(randomy, randomx));
				else
					enemy.add(new Leo(randomy, randomx));
				
			}   else
				i--;
		}

		for (int i = 0; i < MtD.stats().length; i++) {
			toDisplay.add(MtD.stats()[i]);
		}
		eNum = enemy.size();
	}

	private void addKais(int y, int x) {

		int randomx;
		int randomy;
		

		for (int i = 0; i < ((int) Math.random() * 8) + 8; i++) {
			randomx = (int) Math.random() * 9 - 4;
			randomy = (int) Math.random() * 9 - 4;

			if (!(randomy + y >= 50 || randomx + x >= 50 || randomy + y < 0 || randomx + x < 0)) {
				if (getMapObj().getLevel()[randomy + y][randomx + x].canContainMonster())
					enemy.add(new KaiH(randomy + y, randomx + x));
			}

		}

	}

	public void setEnemy(ArrayList<Creature> enemy) {
		this.enemy = enemy;
	}

	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, dim, dim);
		g.setColor(Color.WHITE);
		// draw map
		if (!MtD.isDead()) {
			getMapObj().drawMap(g);
		}
		// display game over screen
		else {
			Image image = Toolkit.getDefaultToolkit().getImage("src/game-over1.jpg");
			// image from
			// https://experiencesminimalistes.com/2016/12/29/burn-out-saisonnier-de-la-quarantaine-en-crise/
			g.drawImage(image, 50, 5, 500, 375, this);
		}
		this.repaint();

		Runner.updateDisplay();
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
		return getMapObj().getLevel();
	}

	public void setMap(Map map) {
		this.setMapObj(map);
	}

	// checks for valid spaces and performs an action
	public void move(int x1, int x2, int y1, int y2, int dx, int dy, Creature thing) {
		// check and move
		if (getMapObj().getLevel()[y2][x2].canContainMtD()) {
			getMapObj().getLevel()[y1][x1] = thing.getTile();
			thing.setTile(getMapObj().getLevel()[y2][x2]);
			thing.setDx(dx);
			thing.setDy(dy);
			thing.move(MtD, getMapObj());
			getMapObj().getLevel()[thing.getY()][thing.getX()] = MtD;

		}
		// open door
		else if (getMapObj().getLevel()[y2][x2] instanceof Door) {
			((Door) getMapObj().getLevel()[y2][x2]).setOpen(true);
		}
		// attack enemy
		else if (getMapObj().getLevel()[y2][x2] instanceof Creature) {
			MtD.attack((Creature) getMapObj().getLevel()[y2][x2], getMapObj());
		}
		// randomly regenerate health
		if (MtD.getStrength() > Math.random() * 25 && MtD.getHealth() != MtD.getMaxHealth()) {
			MtD.setHealth(MtD.getHealth() + 1);
		}

		// reset tracking of inventory
		if (selected >= inventory.size())
			selected = base;
	}

	// update enemies
	public void updateEnemies() {
		for (int i = 0; i < enemy.size(); i++) {
			getMapObj().getLevel()[enemy.get(i).getY()][enemy.get(i).getX()] = enemy.get(i).getTile();
			// check health
			if (enemy.get(i).isDead()) {
				map.getLevel()[enemy.get(i).getY()][enemy.get(i).getX()] = new PileOfDead();
				enemy.remove(i);
				break;
			}
			// move
			enemy.get(i).move(MtD, getMapObj());

			if (getMapObj().getLevel()[enemy.get(i).getY()][enemy.get(i).getX()].canContainMonster()) {
				getMapObj().getLevel()[enemy.get(i).getY()][enemy.get(i).getX()] = (Tile) enemy.get(i);
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
		toDisplay.add("Current Level: " + map.getLvl());
		toDisplay.add("Inventory");
		for (int i = 0; i < getMapObj().getLevel().length; i++) {
			for (int j = 0; j < getMapObj().getLevel()[i].length; j++) {
				if (getMapObj().getLevel()[i][j] instanceof Item
						&& (((Item) getMapObj().getLevel()[i][j]).canUse(MtD.getX(), MtD.getY()))) {
					toDisplay.add("Press " + (((Item) getMapObj().getLevel()[i][j]).key() + " to "
							+ (((Item) getMapObj().getLevel()[i][j]).action()).toLowerCase() + " "
							+ (((Item) getMapObj().getLevel()[i][j]).getType())).toLowerCase());
					inventory.add((Item) getMapObj().getLevel()[i][j]);
					((Item) getMapObj().getLevel()[i][j]).setIndex(inventory.size() - 1);
				}
				if (getMapObj().getLevel()[i][j] instanceof Wand)
					((Wand) (getMapObj().getLevel()[i][j])).count();
			}
		}

		if (MtD.getTile() instanceof Item && ((Item) MtD.getTile()).canUse(MtD.getX(), MtD.getY())) {
			toDisplay.add("Press " + (((Item) MtD.getTile()).key()) + " to " + (((Item) MtD.getTile()).action()).toLowerCase()
							+ " " + (((Item) MtD.getTile()).getType()).toLowerCase());
			inventory.add((Item) MtD.getTile());
			((Item) MtD.getTile()).setIndex(inventory.size() - 1);
		}

	}

	// perform an action if applicable
	public void act(char key) {
		if (key == ' ') {
			if (selected - base < inventory.size() - 1)
				selected++;
			else
				selected = base;
		}

		for (int i = 0; i < inventory.size(); i++) {
			if (selected - base == inventory.get(i).getIndex()) {
				switch (key) {
				case ('q'): // Quaff a potion
					if (inventory.get(i) instanceof Potion) {
						((Potion) inventory.get(i)).quaff(MtD);
						MtD.setTile(new EmptySpace());
					}
					break;
				case ('w'): // wear/wield an item
					if (inventory.get(i) instanceof Weapons) {
						((Weapons) inventory.get(i)).use(MtD);
						MtD.setTile(new EmptySpace());
					} else if (inventory.get(i) instanceof Armor) {
						((Armor) inventory.get(i)).wear(MtD);
						MtD.setTile(new EmptySpace());
					}
					break;
				case ('r'): // Read a scroll
					if (inventory.get(i) instanceof Scroll) {
						((Scroll) inventory.get(i)).read(MtD, getMapObj(), enemy);
					}
					break;
				case ('t'): // Take off an item
					break;
				case ('o'): // Open a door
					if (inventory.get(i) instanceof Door) {
						((Door) inventory.get(i)).setOpen(true);
					}
					break;
				case ('m'): // Cast a magic spell
					break;
				case ('s'): // Stairs
					if (inventory.get(i) instanceof Stairs)
						((Stairs) inventory.get(i)).nextLevel();
					break;
				case ('c'): // Close a Door
					if (inventory.get(i) instanceof Door) {
						((Door) inventory.get(i)).setOpen(false);
					}
					break;
				case ('f'): // fire/throw an item
					break;
				case ('a'): // aim/fire a wand
					if (inventory.get(i) instanceof Wand) {
						((Wand) inventory.get(i)).magic(MtD, getMapObj(), enemy);
						inventory.remove(i);
					}
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
		return enemy;
	}

	public Map getMapObj() {
		return map;
	}

	public void setMapObj(Map map) {
		this.map = map;
	}

	public int geteNum() {
		return eNum;
	}

	public void seteNum(int eNum) {
		this.eNum = eNum;
	}
}
