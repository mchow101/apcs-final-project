//Wall in Map
public class Wall implements Tile {

	//prevent anything from passing through
	
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