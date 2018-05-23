import java.util.ArrayList;

//Run me!
public class Runner {
	private static SidebarGraphics sidebar;
	private static MapGraphics graphics;

	public static void main (String [] args) {
		int dim = 600; // to easily change dimensions
		sidebar = new SidebarGraphics(dim);
		graphics = new MapGraphics(dim);
	}
	
	public static void updateDisplay() {
		try {
			sidebar.setDisplay(graphics.getToDisplay());
		} catch (NullPointerException e) {
		}
	}

	public static int getSelectedIndex() {
		try {
			return graphics.getSelectedIndex();
		} catch (NullPointerException e) {
			return 0;
		}
	}
	
	public static ArrayList<Creature> getEnemy() {
		return graphics.getEnemy();
	}
}