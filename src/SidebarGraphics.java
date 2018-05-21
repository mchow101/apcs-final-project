
//Graphics and JFrame for SideBar

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class SidebarGraphics extends JPanel {
	private int dim;
	private ArrayList<String> display;

	private JFrame frame;

	public SidebarGraphics(int dim) {
		this.dim = dim;
		// frame setup
		frame = new JFrame("Sidebar");

		frame.setSize(dim / 3, dim);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.add(this);
		frame.repaint();
		frame.setVisible(true);
		
		display = new ArrayList<String>();
		display.add("Generic Message");
	}

	public void paintComponent(java.awt.Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, dim / 2, dim);
		g.setColor(Color.WHITE);
		Font plain = new Font("Times New Roman", Font.PLAIN, 18);
		Font heading = new Font("Times New Roman", Font.BOLD, 20);
		for(int i = 0; i < display.size(); i++) {
			if(display.get(i).equals("Player Stats") || display.get(i).equals("Inventory"))
				g.setFont(heading);
			else
				g.setFont(plain);
			if(Runner.getSelectedIndex() == i)
				g.setColor(Color.RED);
			else
				g.setColor(Color.WHITE);
			g.drawString(display.get(i), 10, i*22 + 20);
		}
		frame.repaint();
	}
	
	public void setDisplay(ArrayList<String> toDisplay) {
		display = new ArrayList<String>();
		for(int i = 0; i < toDisplay.size(); i++) {
			display.add(toDisplay.get(i));
		}
	}
}
