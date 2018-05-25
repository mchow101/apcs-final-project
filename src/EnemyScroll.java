import java.util.ArrayList;

// Kills all enemies within a random radius
public class EnemyScroll extends Scroll {
	int r;

	public EnemyScroll(int x, int y) {
		super(x, y);
		r = 10;// (int) (Math.random() * 10);
	}

	@Override
	public void read(Charecter MtD, Map map, ArrayList<Creature> enemy) {
		// finds all enemies in random radius and turns them into a potion
		int i = 1;
		boolean canGo = true;
		while (canGo && i <= r) {
			for (int a = -i; a <= i; a++) {
				for (int b = -i; b <= i; b++) {
					if (!(a == b && a == 0)) {
						if (map.getLevel()[MtD.getY() + b][MtD.getX() + a] instanceof Creature) {
							enemy.remove(enemy.indexOf((Creature) (map.getLevel()[MtD.getY() + b][MtD.getX() + a])));
							int random = (int)(Math.random()*3);
							Potion p;
							switch(random) {
							case 0: p = new StrengthPotion(MtD.getX() + a, MtD.getY() + b); break;
							case 1: p = new MaxHealthPotion(MtD.getX() + a, MtD.getY() + b); break;
							default: p = new HealthPotion(MtD.getX() + a, MtD.getY() + b); break;
							}
							map.getLevel()[MtD.getY() + b][MtD.getX() + a] = p;
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