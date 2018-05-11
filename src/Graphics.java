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
	private ArrayList<KaiH> kai = new ArrayList<>();

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
		kai.add(new KaiH(5, 8));
		kai.add(new KaiH(5, 20));
		kai.add(new KaiH(5, 11));
	}

	public void paint(java.awt.Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, dim, dim);
		g.setColor(Color.WHITE);
		map.drawMap(g);
		frame.repaint();
	}

	@Override
	public void keyTyped(KeyEvent event) {

	}

	@Override
	public void keyPressed(KeyEvent event) {
		
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
		
		for (int i = 0; i < kai.size(); i++) {
		map.getLevel1()[kai.get(i).getY()][kai.get(i).getX()] = kai.get(i).getTile();
		
		if (kai.get(i).isDead()) {
			kai.remove(i);
			break;
		}
		
			kai.get(i).move(MtD, map);
			
			if (map.getLevel1()[kai.get(i).getY()][kai.get(i).getX()].canContainMonster()) {
			
		//	map.getLevel1()[kai.getPrevY()][kai.getPrevX()] = kai.getTile();
			map.getLevel1()[kai.get(i).getY()][kai.get(i).getX()] = kai.get(i);
			}
			else {
				kai.get(i).setX(kai.get(i).getPrevX());
				kai.get(i).setY(kai.get(i).getPrevY());
			}
			}
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

		else if (map.getLevel1()[y2][x2] instanceof Door){
			((Door) map.getLevel1()[y2][x2]).setOpen(true);
		}
		
		else if (map.getLevel1()[y2][x2] instanceof Creature){
			  MtD.attack((Creature) map.getLevel1()[y2][x2], map);
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