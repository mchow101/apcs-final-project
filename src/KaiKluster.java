import java.util.ArrayList;

public class KaiKluster implements Creature {
	
	public KaiKluster(int x, int y, Map map) {
		
		ArrayList<Creature> enemy = new ArrayList<Creature>();
		
		int randomx;
		int randomy;
			
		for (int i = 0; i < ((int) Math.random() * 8) + 8; i++) {
			randomx =(int) Math.random()*9 - 4;
			randomy =(int) Math.random()*9 - 4;
			
			if (map.getLevel()[randomy+y][randomx+x].canContainMonster())
			enemy.add(new KaiH(randomx+x, randomy+y));
		}
		
		Runner.setEnemy(enemy);
		
	}

	@Override 
	public void setHealth(int a) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void die(Map map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(Creature a, Map map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDx(int dx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDy(int dy) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Tile getTile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTile(Tile tile) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getPrevX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getPrevY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getAC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void attack(Creature enemy, Map map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMaxHealth() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String toString() {
		return "darn";
	}
	
}
