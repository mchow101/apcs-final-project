import java.util.ArrayList;

public class Tyler implements Creature, Tile {

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
	private ArrayList<Creature> enemy;

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public Tyler(int x, int y, ArrayList<Creature> enemy) {
		health = 500;
		maxHealth = 500;
		strength = 15;
		intel = 5;
		AC = 35;
		this.x = y;
		this.y = x;
		prevX = x;
		prevY = y;
		tile = new EmptySpace();

		right = 0;
		left = 0;
		up = 0;
		down = 0;

		this.enemy = enemy;
	}

	public void setEnemy(ArrayList<Creature> enemy) {
		this.enemy = enemy;
	}

	public ArrayList<Creature> getEnemy() {
		return enemy;
	}

	public void move(Creature MtD, Map map) {

		int direction = 0;

		if (Math.abs(this.x - ((Charecter) MtD).getX()) + Math.abs(this.y - ((Charecter) MtD).getY()) == 1) {
			this.attack((Charecter) MtD, map);
		} else if (Math.random() > .125) {

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
		}
		else {
			doMagic(MtD, map);
		}
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

	private void doMagic(Creature MtD, Map map) {
		int x = MtD.getX();
		int y = MtD.getY();
		int randomx;
		int randomy;
		double rando = Math.random();
		
		if (((((double) this.health)/this.maxHealth) < .2) && rando > .7) {
			genTyler(map);
		}
		
		
		if (rando > .85 && straightLine(MtD, map) && ((Charecter) MtD).getStrength() > 4)
			((Charecter) MtD).setStrength(((Charecter) MtD).getStrength()-5);
		else if (rando > .65 && straightLine(MtD, map)) {
			((Charecter) MtD).setHealth(((Charecter) MtD).getHealth()-15);
			((Charecter) MtD).setMaxHealth(((Charecter) MtD).getMaxHealth()-5);
		}
		
		else if (rando > .55){
			this.health = maxHealth;
	}
		else if (rando > .35 && straightLine(MtD, map)) {
			((Charecter) MtD).setHealth(((Charecter) MtD).getHealth()-10);
		}
		
		else if (rando > .25) {
			while (x != this.x || y != this.y) {
				if (!map.getLevel()[y][x].canContainMonster() && !map.getLevel()[y][x].canContainMtD())
					map.getLevel()[y][x] = new EmptySpace();
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
		}
		
		
	}

	private void genTyler(Map map) {
				
			int randomx;
			int randomy;
			
			randomx = (int) (Math.random() * 49) + 1;
			randomy = (int) (Math.random() * 49) + 1;

				if (map.getLevel()[randomy][randomx].canContainMonster()) {
					map.getLevel()[this.y][this.x] = this.tile;
					enemy.add(new Tyler(randomy, randomx, enemy));
				}
				else
					genTyler(map);
		
	}

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

	public void attack(Creature MtD, Map map) {

		int damage = 0;

		damage += this.strength;

		if (MtD.getAC() - 10 > Math.random() * 100) {
			damage = 0;
		}

		MtD.setHealth(MtD.getHealth() - damage);

		if (MtD.getHealth() <= 0) {
			MtD.die(map);
		}
	}

	@Override
	public void die(Map map) {
		map.getLevel()[this.y][this.x] = this.getTile();
		this.dead = true;

	}

	public String toString() {
		return "TY";
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
}