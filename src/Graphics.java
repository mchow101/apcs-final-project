import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Graphics extends JPanel implements KeyListener, Runnable {
	private Thread t;
	private Charecter MtD;
	private JFrame frame;
	private int dim = 600;
	private Map map;
	private Tile imSad; // because he's empty inside D=
	ArrayList<Items> choices = new ArrayList<Items>();
	private ArrayList<Creature> enemy = new ArrayList<>();

	public Graphics() {
		frame = new JFrame("Super Fun Game");
		frame.setSize(dim, dim);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.repaint();
		t = new Thread(this);
		t.start();
		map = new Map(dim);
		addKeyListener(this);
		this.setFocusable(true);
		MtD = new Charecter();
		frame.setVisible(true);
		choices.add(Items.NONE);
		
		int randomx;
		int randomy;
		
		for (int i = 0; i < 25; i++) {
			randomx = (int) (Math.random()*42);
			randomy = (int) (Math.random()*42);
			
			if (map.getLevel()[randomy][randomx].canContainMonster()) {
			if (Math.random() > .5)
		enemy.add(new Bryce (randomy,randomx));
			else
		enemy.add(new KaiH(randomy, randomx));
		}
		}
		
//		for (int i = 4; i < map.getLevel1().length-4; i++) {
//			for (int j = 4; j < map.getLevel1()[i].length-4; j++) {
//				if (map.getLevel1()[i][j].canContainMonster())
//					enemy.add(new KaiH(i, j));
//			}
//		}
		}

	public void paint(java.awt.Graphics g) {

		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, dim, dim);
		g.setColor(Color.WHITE);

		if (!MtD.isDead()) {
			map.drawMap(g);

		}
		
		else {	
			Image image = Toolkit.getDefaultToolkit().getImage("src/game-over1.jpg");
			// image from https://experiencesminimalistes.com/2016/12/29/burn-out-saisonnier-de-la-quarantaine-en-crise/
	        g.drawImage(image, 50, 5, 500, 375, this);
		}

		frame.repaint();
	}

	@Override
	public void keyTyped(KeyEvent event) {

	}

	@Override
	public void keyPressed(KeyEvent event) {

		if (!MtD.isDead()) {

			choices = Map.updateInventory(MtD, choices);
			if (event.getKeyChar() == 'i' || event.getKeyChar() == 'I') {
				String[] aChoices = new String[choices.size()];
				for(int i = 0; i < choices.size(); i++) {
					if(choices.get(i).equals(Items.DOORS)) 
						aChoices[i] = "Door";
					else if(choices.get(i).equals(Items.STAIRS) && enemy.size() == 0)
						aChoices[i] = "Stairs";
					else
						aChoices[i] = "None";
				}
				String choice = (Inventory.showDialog(frame, "Here are your items.", "Inventory", aChoices, null));
			}

			if (event.getKeyChar() == 's' || event.getKeyChar() == 'S') {
				Stats.showDialog(frame, "Current Level: " + Map.getLvl(), "Player Statistics", MtD.stats(), null);
			}
			
			if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
				move(MtD.getX(), MtD.getX() + 1, MtD.getY(), MtD.getY(), 1, 0, MtD);
			}

			if (event.getKeyCode() == KeyEvent.VK_LEFT) {
				move(MtD.getX(), MtD.getX() - 1, MtD.getY(), MtD.getY(), -1, 0, MtD);
			}
			if (event.getKeyCode() == KeyEvent.VK_UP) {
				move(MtD.getX(), MtD.getX(), MtD.getY(), MtD.getY() - 1, 0, -1, MtD);
			}
			if (event.getKeyCode() == KeyEvent.VK_DOWN) {
				move(MtD.getX(), MtD.getX(), MtD.getY(), MtD.getY() + 1, 0, 1, MtD);

			}

			for (int i = 0; i < enemy.size(); i++) {
				map.getLevel()[enemy.get(i).getY()][enemy.get(i).getX()] = enemy.get(i).getTile();

				if (enemy.get(i).isDead()) {
					enemy.remove(i);
					break;
				}

				enemy.get(i).move(MtD, map);

				if (map.getLevel()[enemy.get(i).getY()][enemy.get(i).getX()].canContainMonster()) {

					// map.getLevel1()[enemy.getPrevY()][enemy.getPrevX()] = enemy.getTile();
					map.getLevel()[enemy.get(i).getY()][enemy.get(i).getX()] = (Tile) enemy.get(i);
					//System.out.println(enemy.get(i).toString());
				} else {
					enemy.get(i).setX(enemy.get(i).getPrevX());
					enemy.get(i).setY(enemy.get(i).getPrevY());
				}
			}

		}

	}

	public void move(int x1, int x2, int y1, int y2, int dx, int dy, Creature thing) {
		if (map.getLevel()[y2][x2].canContainMtD()) {
			map.getLevel()[y1][x1] = thing.getTile();
			thing.setTile(map.getLevel()[y2][x2]);
			thing.setDx(dx);
			thing.setDy(dy);
			thing.move(MtD, map);
			map.getLevel()[thing.getY()][thing.getX()] = MtD;

		}

		else if (map.getLevel()[y2][x2] instanceof Door) {
			((Door) map.getLevel()[y2][x2]).setOpen(true);
		}

		else if (map.getLevel()[y2][x2] instanceof Creature) {
			MtD.attack((Creature) map.getLevel()[y2][x2], map);
		}

		if (MtD.getStrength() > Math.random() * 25 && MtD.getHealth() != MtD.getMaxHealth()) {
			MtD.setHealth(MtD.getHealth() + 1);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void run() {
		frame.repaint();
	}
}