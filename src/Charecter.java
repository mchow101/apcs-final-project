
public class Charecter implements Tile, Creature {
	
	private int health;
	private int strength;
	
	private int x;
	private int y;
	
	private int dx;
	private int dy;
	
	private Tile tile;
	
	public Charecter() {
		health = 25;
		strength = 5;
		
		x = 5;
		y = 5;
		
		tile = new EmptySpace();
	}
	
	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public void move() {		
		x = x + dx;
		y = y + dy;
		dx = 0;
		dy = 0;
	}
	
	public void attack(Creature enemy, Map map) {
		enemy.setHealth(enemy.getHealth()-strength);
		
		if (enemy.getHealth() <= 0)
			enemy.die(map);
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

	public void setHealth(int a) {
		health = a;
		
	}
	
	public int getHealth() {
		return health;
	}

	@Override
	public void die(Map map) {
		// TODO Auto-generated method stub
		
	}

	public void move(Charecter MtD) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Creature a, Map map) {
		this.move();
		
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

}
