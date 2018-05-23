//Scrolls: do stuff to dungeon
public abstract class Scroll implements Tile, Item {

	private int x, y, i;

	public Scroll (int x, int y) {
		this.setX(x);
		this.setY(y);
		this.i = 0;
	}

	// does stuff based on scroll type
	public abstract void read(Charecter MtD, Map map);
	
	// implemented methods for interfaces

	@Override
	public String action() {
		return "Read";
	}

	@Override
	public boolean canUse(int locx, int locy) {
		return this.getX() == locx && this.getY() == locy;
	}

	@Override
	public String getType() {
		return "Scroll";
	}

	@Override
	public int getIndex() {
		return i;
	}

	@Override
	public void setIndex(int i) {
		this.i = i;
	}

	@Override
	public boolean canContainMtD() {
		return true;
	}

	@Override
	public boolean canContainMonster() {
		return false;
	}

	@Override
	public String toString() {
		return "$";
	}

	@Override
	public char key() {
		return 'r';
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}