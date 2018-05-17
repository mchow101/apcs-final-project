//Potions: give character special abilities
public class Potion implements Item, Tile {

	private int x, y, i;
	private CharacterStats type;

	public Potion(int x, int y) {
		this.x = x;
		this.y = y;
		this.i = 0;
	}

	// gives character abilities based on potion type
	public void quaff(Charecter mtD) {
		System.out.println("yay");
	}

	// implemented methods for interfaces

	@Override
	public String action() {
		return "Quaff";
	}

	@Override
	public boolean canUse(int locx, int locy) {
		return (locx == this.x) && (locy == this.y);
	}

	@Override
	public Items getType() {
		return Items.POTION;
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
		return "@";
	}

}