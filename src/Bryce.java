//Level 2 Monster
//Add description of abilities
public class Bryce implements Creature, Tile {

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
	private boolean attack;

	private int dx;
	private int dy;

	private Tile tile;

	public Bryce(int x, int y) {
		// initialize variables
		health = 35;
		maxHealth = 35;
		strength = 5;
		intel = 5;
		AC = 12;
		this.y = x;
		this.x = y;
		prevX = x;
		prevY = y;
		tile = new EmptySpace();

		right = 0;
		left = 0;
		up = 0;
		down = 0;
		attack = false;
	}

	public void move(Creature MtD, Map map) {

		int direction = 0;
		// checks to attack MtD
		if (straightLine(MtD, map)) {
			this.attack((Charecter) MtD, map);
			attack = true;
		} else {
			attack = false;
			// finds best way to run away from MtD
			up = Math.abs(this.x - ((Charecter) MtD).getX()) + Math.abs((this.y + 1) - ((Charecter) MtD).getY());
			down = Math.abs(this.x - ((Charecter) MtD).getX()) + Math.abs((this.y - 1) - ((Charecter) MtD).getY());
			left = Math.abs((this.x - 1) - ((Charecter) MtD).getX()) + Math.abs(this.y - ((Charecter) MtD).getY());
			right = Math.abs((this.x + 1) - ((Charecter) MtD).getX()) + Math.abs(this.y - ((Charecter) MtD).getY());

			if (!map.getLevel()[this.y + 1][this.x].canContainMonster())
				up = -10000;
			if (!map.getLevel()[this.y - 1][this.x].canContainMonster())
				down = -10000;
			if (!map.getLevel()[this.y][this.x + 1].canContainMonster())
				right = -10000;
			if (!map.getLevel()[this.y][this.x - 1].canContainMonster())
				left = -10000;

			if (up > down && up > left && up > right)
				this.setDy(1);
			else if (down > left && down > right)
				this.setDy(-1);
			else if (left > right)
				this.setDx(-1);
			else if (right != -10000)
				this.setDx(1);
//				System.out.println(this.dx + " " + this.dy);

		}

		// random health regeneration
		if (this.strength >= Math.random() * 25 && this.health != this.maxHealth) {
			this.health++;
		}

		this.prevX = this.x;
		this.prevY = this.y;

		this.x = this.x + this.dx;
		this.y = this.y + this.dy;
		this.dx = 0;
		this.dy = 0;
		this.setTile(map.getLevel()[this.y][this.x]);

	}

	// checks if MtD can be attacked
	// this can be done when *insert when*
	private boolean straightLine(Creature MtD, Map map) {

		int x = MtD.getX();
		int y = MtD.getY();

		// yeah you put something here
		while (x != this.x || y != this.y) {
			if (!map.getLevel()[y][x].canContainMonster() && !map.getLevel()[y][x].canContainMtD())
				return false;
			if (Math.abs(this.x - x) > Math.abs(this.y - y)) {
				if (x - this.x > 0)
					x--;
				else
					x++;
			} else {
				if (y - this.y > 0)
					y--;
				else
					y++;
			}
		}

		return true;
	}

	public void attack(Creature MtD, Map map) {
		int distance = Math.abs(this.y - MtD.getY()) + Math.abs(this.x - MtD.getX());
		
		int damage = 0;

		damage += this.strength;

		if (MtD.getAC() > Math.random() * 100) {
			damage = 0;
		}
		
		if (distance > 25) {
			if (Math.random() < .95) {
				damage = 0;
			}
			
			else
				damage = (int) (damage * .2);
		}
		
		if (distance < 25 && distance > 15) {
			if (Math.random() < .85) {
				damage = 0;
			}
			
			else {
				damage = (int) (damage*.33);
			}
			
		}
		
		else if (distance > 5) {
			if (Math.random() < .65) {
				damage = 0;
			}
		}
		
			else {
				if (Math.random() < .17) {
					damage = 0;
				}
			}
		
		
		MtD.setHealth(MtD.getHealth()-damage);
		
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
		return "b";
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

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
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
	
	public boolean didAttack() {
		return attack;
	}
	
	public void setAttack(boolean b) {
		attack = b;
	}
}