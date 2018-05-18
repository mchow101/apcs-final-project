//Basic EmptySpace to fill spaces in the map
public class EmptySpace implements Tile {
	
	public EmptySpace() {
	}
	
	public boolean canContainMtD() {
		return true;
	}

	public boolean canContainMonster() {
		return true;
	}

	public String toString() {
		return ".";
	}
}