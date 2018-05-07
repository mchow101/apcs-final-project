
public class Door implements Tile {
	
	private boolean open;

	public Door() {
		open = false;
	}
	
	public String toString(){
		if (open)
			return "'";
		return"+";
	}
		
	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public boolean canContainMtD() {
		return open;
	}
	
	@Override
	public boolean canContainMonster() {
		return open;
	}

}
