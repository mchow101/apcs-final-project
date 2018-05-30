// Increase Max Health by 50%
public class MaxHealthPotion extends Potion implements Tile {

	public MaxHealthPotion(int x, int y) {
		super(x, y);
	}

	@Override
	public void quaff(Charecter MtD) {
		MtD.setMaxHealth((int) (MtD.getMaxHealth() * 1.15));
	}

}
