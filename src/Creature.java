
public interface Creature {
	public void setHealth(int a);
	public int getHealth();
	public void die(Map map);
	void move(Creature a, Map map);
	public void setDx(int dx);
	public void setDy(int dy);
	public int getY();
	public int getX();
	public Tile getTile();
	public void setTile(Tile tile);
	public boolean isDead();
	public int getPrevX();
	public void setX(int prevX);
	public int getPrevY();
	public void setY(int prevY);
	public int getAC();
	
}
