// Increase Strength by 50%
public class StrengthPotion extends Potion implements Tile {

	public StrengthPotion(int x, int y) {
		super(x, y);
	}

	@Override
	public void quaff(Charecter MtD) {
		MtD.setStrength((int) (MtD.getStrength() * 1.5));
	}

}
