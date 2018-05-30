//The player's character, despite the lovely spelling mistake
public class Charecter implements Tile, Creature {
	// stats
	private int health;
	private int strength;
	private int maxHealth;
	// location variables
	private int x;
	private int y;
	// movement variables
	private int dx;
	private int dy;
	// armor class
	private int AC;
	// weapon
	private int weapon;
	// prevents eating of special tiles
	private Tile tile;
	// checks for death
	private boolean dead = false;
	private int xp;
	private int need;
	private int levelPL;

	public Charecter() {
		// initialize stats
		health = 400;
		strength = 15;
		maxHealth = 400;
		// initialize location
		x = 5;
		y = 5;
		// initialize AC and weapon
		AC = 12;
		weapon = 3;
		// initialize starting space as an EmptySpace
		tile = new EmptySpace();
		xp = 0;
		need = 10;
		setLevelPL(1);
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}

	// moves character
	public void move() {
		x = x + dx;
		y = y + dy;
		dx = 0;
		dy = 0;
	}

	// attacks enemy
	public void attack(Creature enemy, Map map) {
		int damage = weapon;

		damage += this.strength;

		if (enemy.getAC() > Math.random() * 100) {
			// random chance of blocking damage
			damage = 0;
		}

		enemy.setHealth(enemy.getHealth() - damage);

		if (enemy.getHealth() <= 0) {
			enemy.die(map);
			xp++; 
			if (need <= xp)
				this.setlevel();
		}
	}

	private void setlevel() {
		setLevelPL(getLevelPL() + 1);
		need =+10;
		xp = 0;
		
		this.setMaxHealth(maxHealth + 10);
		this.setStrength(strength + 4);
		
	}

	public String toString() {
		// because the character has no idea what's going on
		return "!?";
	}

	// implemented methods for interfaces

	@Override
	public boolean canContainMtD() {
		return true;
	}

	@Override
	public boolean canContainMonster() {
		return false;
	}

	@Override
	public void die(Map map) {
		map.getLevel()[this.y][this.x] = this.getTile();
		this.setDead(true);
	}

	@Override
	public void move(Creature a, Map map) {
		this.move();
	}

	@Override
	public boolean isDead() {
		return dead;
	}

	@Override
	public int getPrevX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(int prevX) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getPrevY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setY(int prevY) {
		// TODO Auto-generated method stub

	}

	// getters and setters

	public void setHealth(int a) {
		if(a >=  0)
			health = a;
		else
			health = 0;
	}

	public int getHealth() {
		return health;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getAC() {
		return AC;
	}

	public void setAC(int aC) {
		AC = aC;
	}
	
	public int getWeapon() {
		return weapon;
	}
	
	public void setWeapon(int w) {
		weapon = w;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}

	public String[] stats() {
		String s;
		
		return new String[] { "Player Stats", "Health: " + health, "Strength: " + strength, "Max Health: " + maxHealth, 
				"Weapon: " + weapon, "Armor Class: " + AC, "Player Level: " + levelPL};
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

	public int getLevelPL() {
		return levelPL;
	}

	public void setLevelPL(int levelPL) {
		this.levelPL = levelPL;
	}

	@Override
	public boolean didAttack() {
		return false;
	}
	
	public void setAttack(boolean b) {
	}

}
