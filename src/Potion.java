//Potions: give character special abilities
public abstract class Potion implements Tile, Item {

	private int x, y, i;

	public Potion(int x, int y) {
		this.x = x;
		this.y = y;
		this.i = 0;
	}

	// gives character abilities based on potion type
	public abstract void quaff(Charecter MtD);
	
	// implemented methods for interfaces

	@Override
	public String action() {
		return "Quaff";
	}

	@Override
	public boolean canUse(int locx, int locy) {
		return this.x == locx && this.y == locy;
	}

	@Override
	public String getType() {
		return "Potion";
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
		return true;
	}

	@Override
	public String toString() {
		return "%";
	}

	@Override
	public char key() {
		return 'q';
	}

}