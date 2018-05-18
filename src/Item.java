//All Items to be included in Inventory
public interface Item {
	public String action();
	public boolean canUse(int locx, int locy);
	public Items getType();
	public int getIndex();
	public void setIndex(int i);
}