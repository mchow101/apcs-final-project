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
	ArrayList<Items> choices = new ArrayList<Items>();
	private ArrayList<Creature> kai = new ArrayList<>();

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
		kai.add(new KaiH(5, 8));
		kai.add(new KaiH(5, 20));
		kai.add(new KaiH(5, 11));
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
			g.drawString("Oh darn, I died", 10, 10);
		}
		
		frame.repaint();
	}

	@Override
	public void keyTyped(KeyEvent event) {

	}

	@Override
	public void keyPressed(KeyEvent event) {
<<<<<<< HEAD

		if (!MtD.isDead()) {

			choices = Map.updateInventory(MtD.getX(), MtD.getY(), choices);
			if (event.getKeyChar() == 'i' || event.getKeyChar() == 'I') {
				Items[] aChoices = new Items[choices.size()];
				choices.toArray(aChoices);
				String choice = (Inventory.showDialog(frame, "Here are your items and such", "Inventory", aChoices,
						null));
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

=======
		boolean turn = true;
		choices = Map.updateInventory(MtD.getX(), MtD.getY(), choices);
		if (event.getKeyChar() == 'i' || event.getKeyChar() == 'I') {
			turn = false;
			Items[] aChoices = new Items[choices.size()];
			choices.toArray(aChoices);
			String choice = (Inventory.showDialog(frame, "Here are your items and such", "Inventory", aChoices, null));
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
		if (turn) {
>>>>>>> 2f70eadb2a31d79d625d6b71fe7de984824549d5
			for (int i = 0; i < kai.size(); i++) {
				map.getLevel1()[kai.get(i).getY()][kai.get(i).getX()] = kai.get(i).getTile();

				if (kai.get(i).isDead()) {
					kai.remove(i);
					break;
				}

				kai.get(i).move(MtD, map);

				if (map.getLevel1()[kai.get(i).getY()][kai.get(i).getX()].canContainMonster()) {
<<<<<<< HEAD

					// map.getLevel1()[kai.getPrevY()][kai.getPrevX()] = kai.getTile();
					map.getLevel1()[kai.get(i).getY()][kai.get(i).getX()] = (Tile) kai.get(i);
=======
					map.getLevel1()[kai.get(i).getY()][kai.get(i).getX()] = kai.get(i);
>>>>>>> 2f70eadb2a31d79d625d6b71fe7de984824549d5
				} else {
					kai.get(i).setX(kai.get(i).getPrevX());
					kai.get(i).setY(kai.get(i).getPrevY());
				}
<<<<<<< HEAD
			}
			
			
				
			}
		
=======
			}
		}
>>>>>>> 2f70eadb2a31d79d625d6b71fe7de984824549d5
	}

	public void move(int x1, int x2, int y1, int y2, int dx, int dy, Creature thing) {
		if (map.getLevel1()[y2][x2].canContainMtD()) {
			map.getLevel1()[y1][x1] = thing.getTile();
			thing.setTile(map.getLevel1()[y2][x2]);
			thing.setDx(dx);
			thing.setDy(dy);
			thing.move(MtD, map);
			map.getLevel1()[thing.getY()][thing.getX()] = MtD;
			
		}

		else if (map.getLevel1()[y2][x2] instanceof Door) {
			((Door) map.getLevel1()[y2][x2]).setOpen(true);
		}

		else if (map.getLevel1()[y2][x2] instanceof Creature) {
			MtD.attack((Creature) map.getLevel1()[y2][x2], map);
		}
		
		if (MtD.getStrength() > Math.random()*25 && MtD.getHealth() != MtD.getMaxHealth()) {
			MtD.setHealth(MtD.getHealth() + 1);
			System.out.println(MtD.getHealth());
=======
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