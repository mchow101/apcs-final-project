public class Wall implements Tile {

	public boolean canContainMtD() {
		return false;
	}

	public boolean canContainMonster() {
		return false;
	}
	
	public String toString() {
		return "#";
	}
}