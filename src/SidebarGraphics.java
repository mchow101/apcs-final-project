
//Graphics and JFrame for SideBar

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SidebarGraphics extends JPanel {
	private int dim;
	private ArrayList<String> display;

	private Thread t;
	private JFrame frame;

	public SidebarGraphics(int dim) {
		this.dim = dim;
		// frame setup
		frame = new JFrame("Sidebar");

		frame.setSize(dim / 2, dim);
		frame.setLocation(dim + 10, 0);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.repaint();
		frame.setVisible(true);
	}

	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, dim / 2, dim);
		g.setColor(Color.WHITE);
		// for(int i = 0; i < MtD.stats())
		frame.repaint();
	}
}
