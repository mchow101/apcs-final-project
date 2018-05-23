import java.util.ArrayList;

//Run me!
public class Runner {
	private static SidebarGraphics sidebar;
	private static MapGraphics graphics;

	public static void main (String [] args) {
		int dim = 750; // to easily change dimensions
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
		try {
			return graphics.getEnemy();
		} catch (NullPointerException e) {
			return null;
		}
	}
	
	public static void setMap(Map noob){
		try {
		graphics.setMap(noob);
		} catch (NullPointerException e) {
		}
		
	}
	
	public static Tile[][] getMap() {
		try {
		return graphics.getMap();
		} catch (NullPointerException e) {
			System.out.println("nice");
		}
		return null;
		
	}
	
	public static MapGraphics getGraphics() {
		try {
		return graphics;
		} catch (NullPointerException e) {
		return null;
		}
	}

	public static void setEnemy(ArrayList<Creature> enemy) {
		try {
			System.out.println("bob");
		graphics.setEnemy(enemy);
		} catch (NullPointerException e) {
		}
	}
	
}