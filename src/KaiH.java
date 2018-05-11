
public class KaiH implements Creature, Tile{

	private int health;
	private int strength;
	private int intel;
	private int AC;
	
	private int x;
	private int y;
	private int prevX;
	private int prevY;
	
	int up;
	int down;
	int left;
	int right;
	
	private boolean dead;
	
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getPrevX() {
		return prevX;
	}

	public int getPrevY() {
		return prevY;
	}

	private int dx;
	private int dy;
	
	private Tile tile;

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public KaiH(int x, int y) {
		health = 25;
		strength = 5;
		intel = (int) (12 * Math.random() + 6);
		AC = (int) (4 * Math.random()) + 2;
		this.y = x;
		this.x = y;
		prevX = x;
		prevY = y;
		tile = new EmptySpace();
		
		right = 0;
		left = 0;
		up = 0;
		down = 0;
	}

	public void move(Creature MtD, Map map) {
		
		int direction = 0;
		
		if (Math.abs(this.x -  ((Charecter) MtD).getX()) + Math.abs(this.y-((Charecter) MtD).getY()) == 1){
			this.attack((Charecter) MtD);
		}
		else {
//			if (Math.abs(this.x - ((Charecter) MtD).getX()) >= Math.abs(this.y - ((Charecter) MtD).getY())) {
//				if (this.x - ((Charecter) MtD).getX() > 0) { 
//					this.setDx(-1);
//					//System.out.println("check 1");
//				}
//				else {
//					this.setDx(1);
//					//System.out.println("check 2");
//				}
//			}
//			
//			else {
//				if (this.y - ((Charecter) MtD).getY() > 0) {
//					this.setDy(-1);
//					//System.out.println("check 3");
//				}
//				else {
//					//System.out.println("check 4");
//					this.setDy(1);
//				}
//			}
			
			up = Math.abs(this.x - ((Charecter) MtD).getX()) + Math.abs((this.y+1)-((Charecter) MtD).getY());
			down = Math.abs(this.x - ((Charecter) MtD).getX()) + Math.abs((this.y-1)-((Charecter) MtD).getY());
			left = Math.abs((this.x -1)- ((Charecter) MtD).getX()) + Math.abs(this.y-((Charecter) MtD).getY());
			right = Math.abs((this.x + 1)- ((Charecter) MtD).getX()) + Math.abs(this.y-((Charecter) MtD).getY());
			
		//	System.out.println("up is: " + up + " down is: " + down + " left is: " + left + " right is: " + right);
			
			if (!map.getLevel1()[this.y+1][this.x].canContainMonster())
				up = 10000;
			if (!map.getLevel1()[this.y-1][this.x].canContainMonster())
				down = 10000;
			if (!map.getLevel1()[this.y][this.x+1].canContainMonster())
				right = 10000;
			if (!map.getLevel1()[this.y][this.x-1].canContainMonster())
				left = 10000;
				
			if (up < down && up < left && up < right )
				this.setDy(1);
			else if (down < left && down < right)
				this.setDy(-1);
			else if (left < right)
				this.setDx(-1);
			else
				this.setDx(1);
			
		}
		
		
		//System.out.println(this.tile.toString());
		
		this.prevX = this.x;
		this.prevY = this.y;
		
		this.x = this.x + this.dx;
		this.y = this.y + this.dy;
		this.dx = 0;
		this.dy = 0;
		this.setTile(map.getLevel1()[this.y][this.x]);
		//System.out.println(this.tile.toString());
//	
//		System.out.println("the numb " + (MtD.getX() - x) + "  " + (MtD.getY()-y));
		
	}

	public int getStrength() {
		return strength;
	}

	public int getIntel() {
		return intel;
	}

	public int getAC() {
		return AC;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}

	public void attack(Charecter MtD) {
		MtD.setHealth(MtD.getHealth()-strength);
	//	System.out.println(MtD.getHealth());
	}

	@Override
	public void die(Map map) {
		map.getLevel1()[this.y][this.x] = this.getTile();
		this.dead = true;
		
	}

	public String toString() {
		return "k";
	}

	@Override
	public boolean canContainMtD() {
		return false;
	}

	@Override
	public boolean canContainMonster() {
		return false;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setHealth(int a) {
		health = a;
		
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public void setDx(int dx) {
		this.dx = dx;
	}

	@Override
	public void setDy(int dy) {
		this.dy = dy;
		
	}
	

}
