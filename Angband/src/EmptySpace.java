public class EmptySpace implements Tile {
	private TileStatus contents;
	
	public EmptySpace() {
		contents = TileStatus.EMPTY;
	}
	
	public EmptySpace(TileStatus c) {
		contents = c;
	}
	
	public boolean canContainMtD() {
		return !containsMonster();
	}

	public boolean canContainMonster() {
		return !(containsMonster() || containsMtD());
	}

	public boolean containsMtD() {
		return (contents.equals(TileStatus.MTD));
	}
	
	public boolean containsMonster() {
		return !(contents.equals(TileStatus.MTD) || contents.equals(TileStatus.EMPTY));
	}
	
	public void setContents(TileStatus c) {
		contents = c;
	}
	
	public TileStatus getContents() {
		return contents;
	}
	
	public String toString() {
		if(contents.equals(TileStatus.EMPTY)) return "";
		return ("" + contents).substring(0, 1);
	}
}