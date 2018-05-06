
public class Charecter implements Tile{
	
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	public Charecter() {
		x = 5;
		y = 5;
		
	}
	
	public void move() {		
		x = x + dx;
		y = y + dy;
		dx = 0;
		dy = 0;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setDx(int dx) {
		this.dx = dx;
	}
	
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	public String toString() {
		return "@";
	}

	@Override
	public boolean canContainMtD() {
		return true;
	}

	@Override
	public boolean canContainMonster() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
