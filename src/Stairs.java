
public class Stairs implements Tile, Item {
	private boolean up;
	private int x, y, i;

	public Stairs(int x, int y) {	
		this.x = x;
		this.y = y;
		this.i = 0;
	}

	@Override
	public boolean canContainMtD() {
		return true;
	}

	@Override
	public boolean canContainMonster() {
		return true;
	}
	
	public void nextLevel() {
		Map.setLvl(Map.getLvl()+1);
	}
	
	public String toString() {
		return ">";
	}

	@Override
	public String action() {
		return "Next Level";
	}

	@Override
	public boolean canUse(int locx, int locy) {
		if (locx == x && locy == y)
			return true;
		return false;
	}

	@Override
	public Items getType() {
		return Items.STAIRS;
	}

	@Override
	public int getIndex() {
		return i;
	}

	@Override
	public void setIndex(int i) {
		this.i = i;
	}
	
}
