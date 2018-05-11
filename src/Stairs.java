
public class Stairs implements Tile, Item {
	private boolean up;
	private int x, y;

	public Stairs(int x, int y) {	
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean canContainMtD() {
		return true;
	}

	@Override
	public boolean canContainMonster() {
		return true;
	}
	
	public void toDo() {
		Map.setLvl(Map.getLvl()+1);
	}
	
	public String toString() {
		return ">";
	}

	@Override
	public String action() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean canUse(int locx, int locy) {
		System.out.println(x + " " + y + " " + locx + " " + locy);
		return (locx == x && locy == y);
	}

	@Override
	public Items getType() {
		return Items.STAIRS;
	}
	
}
