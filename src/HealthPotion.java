// Regenerate to full health
public class HealthPotion extends Potion implements Tile {

	public HealthPotion(int x, int y) {
		super(x, y);
	}

	@Override
	public void quaff(Charecter MtD) {
		MtD.setHealth(MtD.getMaxHealth());
	}
}
