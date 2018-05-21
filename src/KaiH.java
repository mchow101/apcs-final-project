//Level 1 Monster
//Add description of abilities
public class KaiH implements Creature, Tile {

	private int health;
	private int strength;
	private int intel;
	private int AC;
	private int maxHealth;

	private int x;
	private int y;
	private int prevX;
	private int prevY;

	private int up;
	private int down;
	private int left;
	private int right;

	private boolean dead;

	private int dx;
	private int dy;

	private Tile tile;

	public KaiH(int x, int y) {
		// initialize stats
		health = 25;
		maxHealth = 25;
		strength = 5;
		intel = 5;
		AC = 12;
		this.y = x;
		this.x = y;
		prevX = x;
		prevY = y;
		tile = new EmptySpace();
		// initialize movement variables
		right = 0;
		left = 0;
		up = 0;
		down = 0;

	}

	// either attacks or moves monster per turn
	// add some more comments
	public void move(Creature MtD, Map map) {

		int direction = 0;

		if (Math.abs(this.x - ((Charecter) MtD).getX()) + Math.abs(this.y - ((Charecter) MtD).getY()) == 1) {
			this.attack((Charecter) MtD, map);
		} else {

			up = Math.abs(this.x - ((Charecter) MtD).getX()) + Math.abs((this.y + 1) - ((Charecter) MtD).getY());
			down = Math.abs(this.x - ((Charecter) MtD).getX()) + Math.abs((this.y - 1) - ((Charecter) MtD).getY());
			left = Math.abs((this.x - 1) - ((Charecter) MtD).getX()) + Math.abs(this.y - ((Charecter) MtD).getY());
			right = Math.abs((this.x + 1) - ((Charecter) MtD).getX()) + Math.abs(this.y - ((Charecter) MtD).getY());

			if (!map.getLevel()[this.y + 1][this.x].canContainMonster())
				up = 10000;
			if (!map.getLevel()[this.y - 1][this.x].canContainMonster())
				down = 10000;
			if (!map.getLevel()[this.y][this.x + 1].canContainMonster())
				right = 10000;
			if (!map.getLevel()[this.y][this.x - 1].canContainMonster())
				left = 10000;

			if (up < down && up < left && up < right)
				this.setDy(1);
			else if (down < left && down < right)
				this.setDy(-1);
			else if (left < right)
				this.setDx(-1);
			else if (right != 10000)
				this.setDx(1);
			else {}
				//System.out.println(this.dx + " " + this.dy);


		}

		if (this.strength >= Math.random() * 25 && this.health != this.maxHealth) {
			this.health++;
		}

		
		if (prevX != x)
		this.prevX = this.x;
		
		if (prevY != y)
		this.prevY = this.y;

		this.x = this.x + this.dx;
		this.y = this.y + this.dy;
		this.dx = 0;
		this.dy = 0;
		this.setTile(map.getLevel()[this.y][this.x]);

	}

	// attacks MtD
	public void attack(Creature MtD, Map map) {

		int damage = 0;

		damage += this.strength;

		if (MtD.getAC() > Math.random() * 100) {
			// random chance to block damage
			damage = 0;
		}

		MtD.setHealth(MtD.getHealth() - damage);

		if (MtD.getHealth() <= 0) {
			MtD.die(map);
		}
	}

	// implemented methods for interfaces

	@Override
	public void die(Map map) {
		map.getLevel()[this.y][this.x] = this.getTile();
		this.dead = true;
	}

	@Override
	public boolean canContainMtD() {
		return false;
	}

	@Override
	public boolean canContainMonster() {
		return false;
	}

	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public int getMaxHealth() {
		return maxHealth;
	}

	@Override
	public void setDx(int dx) {
		this.dx = dx;
	}

	@Override
	public void setDy(int dy) {
		this.dy = dy;

	}

	// toString, getters and setters

	public String toString() {
		return "k";
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

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
}