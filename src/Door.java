public class Door implements Tile, Item {
	
	private boolean open;
	private int x, y;

	public Door(int x, int y) {
		open = false;
		this.x = x;
		this.y = y;
	}
	
	public String toString(){
		if (open)
			return "'";
		return"+";
	}
		
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	public boolean getOpen() {
		return this.open;
	}

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
		if(open) return "Close";
		return "Open";
	}

	@Override
	public boolean canUse(int locx, int locy) {
		if(Math.abs(locy - y) <= 1 && locx == x) {
			return true;
		} else if(Math.abs(locx - x) <= 1 && locy == y) {
			return true;
		} 
		return false;
	}

	@Override
	public Items getType() {
		return Items.DOORS;
	}
}