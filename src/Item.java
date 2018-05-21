//All Items to be included in Inventory
public interface Item {
	public String action();
	public char key();
	public String getType();
	public int getIndex();
	public void setIndex(int i);
	boolean canUse(int locx, int locy);
}