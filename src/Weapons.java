// Increase amount of damage done by character
public class Weapons implements Item, Tile {

	private int x, y, i, w;

	public Weapons(int x, int y) {
		this.x = x;
		this.y = y;
		this.i = 0;
		this.w = (int)(Math.random()*10);
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
	public String action() {
		return "Wield";
	}

	@Override
	public char key() {
		return 'w';
	}

	@Override
	public String getType() {
		return "Weapon";
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
	public boolean canUse(int locx, int locy) {
		return (this.x == locx && this.y == locy);
	}
	
	public void use(Charecter MtD) {
		MtD.setWeapon(this.w);
	}
	
	public String toString() {
		return "/";
	}
	
}
