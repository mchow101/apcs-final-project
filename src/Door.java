//Doors: act like a Wall
//When opened by character, act like an EmptySpace
public class Door implements Tile, Item {

	private boolean open; // tracks state
	private int x, y, i; // location and inventory index

	public Door(int x, int y) {
		open = false;
		this.x = x;
		this.y = y;
		this.i = 0;
	}

	public String toString() {
		// visual representations of open and closed
		if (open)
			return "'";
		return "+";
	}

	// implemented methods for interfaces

	@Override
	public boolean canContainMtD() {
		return open;
	}

	@Override
	public boolean canContainMonster() {
		return open;
	}

	@Override
	public String action() {
		if (open)
			return "Close";
		return "Open";
	}

	@Override
	public boolean canUse(int locx, int locy) {
		// only used when character is directly adjacent to door
		if (Math.abs(locy - y) == 1 && locx == x) {
			return true;
		} else if (Math.abs(locx - x) == 1 && locy == y) {
			return true;
		}
		return false;
	}

	@Override
	public String getType() {
		return "Door";
	}

	@Override
	public int getIndex() {
		return i;
	}

	@Override
	public void setIndex(int i) {
		this.i = i;
	}

	// getters and setters
	
	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean getOpen() {
		return this.open;
	}

	@Override
	public char key() {
		return !open ? 'o' : 'c';
	}

}