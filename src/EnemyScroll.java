import java.util.ArrayList;

// Kills all enemies within a random radius
public class EnemyScroll extends Scroll {
	int r;

	public EnemyScroll(int x, int y) {
		super(x, y);
		r = (int) (Math.random() * 10);
	}

	@Override
	public void read(Charecter MtD, Map map, ArrayList<Creature> enemy) {
		// finds all enemies in random radius and kills them
		int i = 1;
		boolean canGo = true;
		while (canGo && i <= r) {
			for (int a = -i; a <= i; a++) {
				for (int b = -i; b <= i; b++) {
					if (!(a == b && a == 0)) {
						if (map.getLevel()[MtD.getY() + b][MtD.getX() + a] instanceof Creature) {
							Tile t = (Tile)((Creature) map.getLevel()[MtD.getY() + b][MtD.getX() + a]).getTile();
							enemy.remove(enemy.indexOf((Creature) (map.getLevel()[MtD.getY() + b][MtD.getX() + a])));
							map.getLevel()[MtD.getY() + b][MtD.getX() + a] = t;
						} else {
							if(!map.getLevel()[MtD.getY() + b][MtD.getX() + a].canContainMtD()) {
								canGo = false;
							}
						}
					}
				}
			}
			i++;
			if (MtD.getY() - i < 0 || MtD.getX() - i < 0)
				canGo = false;
		}

		MtD.setTile(new EmptySpace());
	}
}