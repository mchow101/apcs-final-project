import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Graphics extends JPanel implements KeyListener, Runnable {
	private Thread t;
	private Charecter MtD;
	private JFrame frame;
	private int dim = 750;
	private Map map;
	private Tile imSad; // because he's empty inside D=

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
		imSad = new EmptySpace();
		frame.setVisible(true);
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
			moveMtD(MtD.getX(), MtD.getX() + 1, MtD.getY(), MtD.getY(), 1, 0);
		}
		
		if (event.getKeyCode() == KeyEvent.VK_LEFT) {
			moveMtD(MtD.getX(), MtD.getX() - 1, MtD.getY(), MtD.getY(), -1, 0);
			
		}
		if (event.getKeyCode() == KeyEvent.VK_UP) {
			moveMtD(MtD.getX(), MtD.getX(), MtD.getY(), MtD.getY() - 1, 0, -1);
		}
		if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			moveMtD(MtD.getX(), MtD.getX(), MtD.getY(), MtD.getY() + 1, 0, 1);
		}
	}
	
	public void moveMtD(int x1, int x2, int y1, int y2, int dx, int dy) {
		if (map.getLevel1()[y2][x2].canContainMtD()) {
			map.getLevel1()[y1][x1] = imSad;
			imSad = map.getLevel1()[y2][x2];
			MtD.setDx(dx);
			MtD.setDy(dy);
			MtD.move();
			map.getLevel1()[MtD.getY()][MtD.getX()] = MtD;
			
		}

		else if (map.getLevel1()[y2][x2] instanceof Door){
			((Door) map.getLevel1()[y2][x2]).setOpen(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		frame.repaint();
	}
}