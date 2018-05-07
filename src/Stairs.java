
public class Stairs implements Tile{
	private boolean up;

	public Stairs() {	
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
	
}
