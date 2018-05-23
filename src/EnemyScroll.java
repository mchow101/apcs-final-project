import java.util.ArrayList;

// Kills all enemies within a random radius
public class EnemyScroll extends Scroll {
	int r;

	public EnemyScroll(int x, int y) {
		super(x, y);
		r = (int) (Math.random() * 10);
	}

	@Override
	public void read(Charecter MtD, Map map) {
		// FIX ME!
		// finds all enemies in random radius
		int i = 1;
		boolean canGo = true;
		while(canGo && i <= r) {
			if(!(map.getLevel()[getX() + i][getY()].canContainMonster() || map.getLevel()[getX() + i][getY()] instanceof Creature))
				canGo = false;
			if(!(map.getLevel()[getX() + i][getY() + i].canContainMonster() || map.getLevel()[getX() + i][getY() + i] instanceof Creature))
				canGo = false;
			if(!(map.getLevel()[getX() + i][getY() - i].canContainMonster() || map.getLevel()[getX() + i][getY() - i] instanceof Creature))
				canGo = false;
			if(!(map.getLevel()[getX()][getY() + i].canContainMonster() || map.getLevel()[getX()][getY() + i] instanceof Creature))
				canGo = false;
			if(!(map.getLevel()[getX()][getY() - i].canContainMonster() || map.getLevel()[getX()][getY() - i] instanceof Creature))
				canGo = false;
			if(!(map.getLevel()[getX() - i][getY() + i].canContainMonster() || map.getLevel()[getX() - i][getY() + i] instanceof Creature))
				canGo = false;
			if(!(map.getLevel()[getX() - i][getY()].canContainMonster() || map.getLevel()[getX() - i][getY()] instanceof Creature))
				canGo = false;
			if(!(map.getLevel()[getX() - i][getY() - i].canContainMonster() || map.getLevel()[getX() - i][getY() - i] instanceof Creature))
				canGo = false;
			System.out.println(i + " " + canGo);
			i++;
		}
		System.out.println(i);
		
		MtD.setTile(new EmptySpace());
	}
}