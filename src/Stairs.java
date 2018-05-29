//Stairs: allows character to go to next level
public class Stairs implements Tile, Item {
	private boolean up;
	private int x, y, i;

	public Stairs(int x, int y) {	
		this.x = x;
		this.y = y;
		this.i = 0;
		up = true;
	}
	
	public Stairs(int x, int y, boolean up) {	
		this.x = x;
		this.y = y;
		this.i = 0;
		this.up = up;
	}
	
	//increases or decreases level by 1
	public void nextLevel() {
		Runner.getMapObj().setLvl(Runner.getMapObj().getLvl() + 1);
		Runner.seteNum();
	}
	
	public String toString() {
		if(Runner.getEnemy().equals(null))
			return "";
		return Runner.getEnemy().size() <= Runner.geteNum() - Runner.getLvl()*10 ? ">" : ".";
	}
	
	//implemented methods for interfaces

	@Override
	public boolean canContainMtD() {
		return true;
	}

	@Override
	public boolean canContainMonster() {
		return true;
	}

	@Override
	public String action() {
		return "Climb";
	}

	@Override
	public boolean canUse(int locx, int locy) {
		return this.x == locx && this.y == locy && Runner.getEnemy().size() <= Runner.geteNum() - Runner.getLvl()*10;
	}

	@Override
	public String getType() {
		return "Stairs" + (up ? " to Next Level" : " to Previous Level");
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
	public char key() {
		return 's';
	}
	
}
