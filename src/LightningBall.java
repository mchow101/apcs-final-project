import java.util.ArrayList;

public class LightningBall extends Wand {

	public LightningBall(int x, int y) {
		super(x, y);
	}

	@Override
	public void magic(Charecter MtD, Map map, ArrayList<Creature> enemy) {
		this.usable();

		// finds and kill nearest enemy
		int i = 1;
		boolean canGo = true;
		while (canGo) {
			for (int a = -i; a <= i; a++) {
				for (int b = -i; b <= i; b++) {
					if (!(a == b && a == 0)) {
						if (map.getLevel()[MtD.getY() + b][MtD.getX() + a] instanceof Creature) {
							Tile t = (Tile) ((Creature) map.getLevel()[MtD.getY() + b][MtD.getX() + a]).getTile();
							enemy.remove(enemy.indexOf((Creature) (map.getLevel()[MtD.getY() + b][MtD.getX() + a])));
							map.getLevel()[MtD.getY() + b][MtD.getX() + a] = t;
							reset();
						} else {
							if (!map.getLevel()[MtD.getY() + b][MtD.getX() + a].canContainMtD()) {
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

	}

	@Override
	public String action() {
		return "use";
	}

	@Override
	public String getType() {
		return "Lightning Ball";
	}

}
