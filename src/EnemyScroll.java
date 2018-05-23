import java.util.ArrayList;

// Kills all enemies within a random radius
public class EnemyScroll extends Scroll {
	int r;

	public EnemyScroll(int x, int y) {
		super(x, y);
		r = 10;// (int) (Math.random() * 10);
	}

	@Override
	public void read(Charecter MtD, Map map) {
		// FIX ME!
		// finds all enemies in random radius
		int i = 1;
		boolean canGo = true;
		while(canGo && i <= r) {
			for(int a = -i; a <= i; a++) {
				for(int b = -i; b <= i; b++) {
					if(!(a == b && a == 0)) {
						if(map.getLevel()[MtD.getY() + b][MtD.getX() + a].canContainMtD()) {
							System.out.println("yay " + map.getLevel()[MtD.getY() + b][MtD.getX() + a]);
							map.getLevel()[MtD.getY() + b][MtD.getX() + a] = new StrengthPotion(MtD.getX() + a, MtD.getY() + b);
						} else {
							System.out.println("faisl at " + map.getLevel()[MtD.getY() + b][MtD.getX() + a]);
							canGo = false;
							System.out.println(i);
						}
					}
				}
			}
			i++;
			if(MtD.getY() - i < 0 || MtD.getX() - i < 0)
				canGo = false;
		}
		
		MtD.setTile(new EmptySpace());
	}
}