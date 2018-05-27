import java.util.ArrayList;

// Allows player to have a new ability
public abstract class Wand implements Tile, Item {

	private int x, y, i, r, c;
	private boolean canUse;

	public Wand(int x, int y) {
		this.x = x;
		this.y = y;
		this.i = 0;
		this.r = ((int)(Math.random()*10) + 10);
		this.c = 0;
		this.canUse = false;
	}
	
	public abstract void magic(Charecter MtD, Map map, ArrayList<Creature> enemy);
	
	public String toString() {
		return canUse ? "." : "-";
	}
	
	public void usable() {
		this.canUse = true;
	}
	public int getR() {
		return this.r;
	}

	public void setR(int r) {
		this.r = r;
	}
	
	public int getC() {
		return this.c;
	}
	
	public void count() {
		if(c > 0 && canUse) c--;
	}
	
	public void reset() {
		c = r;
	}
	
	//implemented methods for interfaces
	

	@Override
	public abstract String action();

	@Override
	public char key() {
		return 'a'; 
	}

	@Override
	public abstract String getType();

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
		if(this.x == locx && this.y == locy) canUse = true;
		return (canUse && this.c == 0);
	}

	@Override
	public boolean canContainMtD() {
		return true;
	}

	@Override
	public boolean canContainMonster() {
		return false;
	}

}
