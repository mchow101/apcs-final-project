public class PileOfDead implements Tile{
	
	int count;
	
	public PileOfDead() {
		count = 5;
	}

	@Override
	public boolean canContainMtD() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean canContainMonster() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean increment() {
		count--;
		if(count <= 0) {
			return true;
		} 
		return false;
	}
	
	public String toString() {
		return "~";
	}
	
}