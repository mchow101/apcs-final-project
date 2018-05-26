// Allows player to defend self
public class Armor implements Item, Tile {

	private int x, y, i, a;

	public Armor(int x, int y) {
		this.x = x;
		this.y = y;
		this.i = 0;
		this.a = (int)(Math.random()*10);
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
		return "Wear";
	}

	@Override
	public char key() {
		return 'w';
	}

	@Override
	public String getType() {
		return "Armor";
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
	
	public String toString() {
		return "()";
	}
	
	public void wear(Charecter MtD) {
		MtD.setAC(MtD.getAC() + a);
	}

}
