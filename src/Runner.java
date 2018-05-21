import java.util.ArrayList;

//Run me!
public class Runner {
	private static SidebarGraphics sidebar;
	private static MapGraphics graphics;

	public static void main (String [] args) {
		int dim = 1000; // to easily change dimensions
		sidebar = new SidebarGraphics(dim);
		graphics = new MapGraphics(dim);
	}
	
	public static void updateDisplay(ArrayList<String> toDisplay) {
		sidebar.setDisplay(graphics.getToDisplay());
	}

	public static int getSelectedIndex() {
		return graphics.getSelectedIndex();
	}
}