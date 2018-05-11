public interface Item {
	public String action();
	public boolean canUse(int locx, int locy);
	public Items getType();
}