
public class Charecter implements Tile, Creature {

	private int health;
	private int strength;
	private int maxHealth;

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

	private int x;
	private int y;

	private int dx;
	private int dy;

	private int AC;

	private Tile tile;
	private boolean dead = false;

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Charecter() {
		health = 5000;
		strength = 15;
		maxHealth = 5000;

		x = 5;
		y = 5;

		AC = 12;

		tile = new EmptySpace();
	}

	public int getAC() {
		return AC;
	}

	public void setAC(int aC) {
		AC = aC;
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public String[] stats() {
		return new String[] {"Health: " + health, "Strength: " + strength, "Max Health: " + maxHealth};
	}

	public void move() {
		x = x + dx;
		y = y + dy;
		dx = 0;
		dy = 0;
	}

	public void attack(Creature enemy, Map map) {
		int damage = 0;

		damage += this.strength;

		if (enemy.getAC() > Math.random() * 100) {
			damage = 0;
		}

		enemy.setHealth(enemy.getHealth() - damage);
		
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
		return "!?";
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
		map.getLevel()[this.y][this.x] = this.getTile();
		this.setDead(true);
	}

	public void move(Charecter MtD) {
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

}
