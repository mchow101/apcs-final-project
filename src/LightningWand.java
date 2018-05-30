import java.util.ArrayList;

public class LightningWand extends Wand {
//seth wuz heer
	public LightningWand(int x, int y) {
		super(x, y);
	}

	@Override
	public void magic(Charecter MtD, Map map, ArrayList<Creature> enemy) {
		this.usable();
		
		// finds and kill nearest enemy
		if(enemy.size() > 0) {
			int i = 1;
			boolean canGo = true, foundEnemy = false;
			while (canGo || !foundEnemy) {
				for (int a = -i; a <= i; a++) {
					if (MtD.getX() + a + 1 >= 50 || MtD.getX() + a + 1 <= 0) {
						canGo = false;
						continue;
					}
					for (int b = -i; b <= i; b++) {
						if (MtD.getY() + b + 1 >= 50 || MtD.getY() + b + 1 <= 0) {
							canGo = false;
							continue;
						}
						if (!(a == b && a == 0)) {
							if (!foundEnemy && map.getLevel()[MtD.getY() + b][MtD.getX() + a] instanceof Creature) {
								Tile t = (Tile) ((Creature) map.getLevel()[MtD.getY() + b][MtD.getX() + a]).getTile();
								enemy.remove(enemy.indexOf((Creature) (map.getLevel()[MtD.getY() + b][MtD.getX() + a])));
								map.getLevel()[MtD.getY() + b][MtD.getX() + a] = t;
								foundEnemy = true;
								reset();
							} else if (!map.getLevel()[MtD.getY() + b][MtD.getX() + a].canContainMonster())
								canGo = false;
						}
						
					}
				}
				i++;
				if (MtD.getY() - i < 0 || MtD.getX() - i < 0)
					canGo = false;
			}
		}
	}

	@Override
	public String action() {
		return "use";
	}

	@Override
	public String getType() {
		return "Lightning Wand";
	}

}
