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
	private EmptySpace imSad; //because he's empty inside D=
    
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
         if(event.getKeyCode() == KeyEvent.VK_RIGHT && map.getLevel1()[MtD.getY()][MtD.getX()+1].canContainMtD())              
            {
             MtD.setDx(1);
             MtD.move();
             map.getLevel1()[MtD.getY()][MtD.getX()] = MtD;
             map.getLevel1()[MtD.getY()][MtD.getX()-1] = imSad;
            }
         if(event.getKeyCode() == KeyEvent.VK_LEFT && map.getLevel1()[MtD.getY()][MtD.getX()-1].canContainMtD())              
         {
          MtD.setDx(-1);
          MtD.move();
          map.getLevel1()[MtD.getY()][MtD.getX()] = MtD;
          map.getLevel1()[MtD.getY()][MtD.getX()+1] = imSad;
         }
         if(event.getKeyCode() == KeyEvent.VK_UP && map.getLevel1()[MtD.getY()-1][MtD.getX()].canContainMtD())              
         {
          MtD.setDy(-1);
          MtD.move();
          map.getLevel1()[MtD.getY()][MtD.getX()] = MtD;
          map.getLevel1()[MtD.getY()+1][MtD.getX()] = imSad;
         }
         if(event.getKeyCode() == KeyEvent.VK_DOWN&& map.getLevel1()[MtD.getY()+1][MtD.getX()].canContainMtD())              
         {
          MtD.setDy(1);
          MtD.move();
          map.getLevel1()[MtD.getY()][MtD.getX()] = MtD;
          map.getLevel1()[MtD.getY()-1][MtD.getX()] = imSad;
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