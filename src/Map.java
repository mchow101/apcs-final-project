import java.util.ArrayList;

public class Map {
	private Tile[][] level1;
	private Tile[][] level2;
	private int dim;
	
	private static Tile[][] level;
	private static ArrayList<Tile> inventory;
	private static int lvl = 1;
	
	public Map(int dim) {
		this.dim = dim;
		//character blueprint for the map
		char[][] level1bp = 
		   {{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
			{'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'},
			{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
			{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
			{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','w','w','w','w','w','.','w','w','w','w','w','w','w','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
			{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','.','.','.','.','w'},
			{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','w','w','w','w','w','.','w','w','w','w','w','w','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
			{'w','.','d','.','s','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
			{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','.','w','w','w','w','w','w','w','w','w','w','w','w','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
			{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','.','w','.','.','.','.','.','.','.','.','.','.','.','w','w','.','w','w','w','.','w','w','w','.','w','w','w','.','w','w','.','.','.','.','w'},
			{'w','.','w','.','.','.','s','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
			{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','.','w','.','.','.','.','.','.','.','.','.','.','.','w','w','.','w','w','w','.','w','w','w','.','w','w','w','.','w','w','.','.','.','.','w'},
			{'w','.','w','.','.','.','.','.','.','w','.','w','w','w','w','.','w','w','w','w','w','w','w','w','w','w','w','w','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
			{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
			{'w','d','w','w','w','w','w','w','w','w','w','w','w','.','w','.','w','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','w','.','w','.','.','w','.','.','.','.','.','.','.','.','.','.','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','.','w','w'},
			{'w','.','.','w','w','w','w','.','.','.','.','.','w','.','w','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','w','w','.','w','w','w','w','w','w','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
			{'w','w','w','w','w','w','.','w','w','w','w','.','w','w','w','w','w','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','w','.','w','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','w','.','w','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','w','.','w','w','w','w','w','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','w','.','.','.','.','.','.','w','.','.','.','.','w','w','.','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','.','w','w','w','.','w','w','w'},
			{'w','.','.','.','.','w','w','w','w','w','w','.','w','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','w','.','w','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','w','.','.','.','.','w','.','w','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','w','w','w','w','w','w','.','w','w','w','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','w','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','w','.','.','.','.','.','.','.','.','w','w','.','w','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','w','.','w','w','w','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','w','.','.','.','.','.','.','.','.','w','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','w','w','w','w','w','w','w','w','w','w','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
			{'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'},
			{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'}
			};
		level1 = new Tile[50][50];
		for(int i = 0; i < level1.length; i++) {
			for(int j = 0; j < level1[i].length; j++) {
				switch(level1bp[i][j]) {
				case '.': level1[i][j] = new EmptySpace(); break;
				case 'w': level1[i][j] = new Wall(); break;
				case 'd': level1[i][j] = new Door(j, i); break;
				case 's': level1[i][j] = new Stairs(j, i); break;
				}
			}
		}
		level2 = level1;
		inventory = new ArrayList<Tile>();
	}
	
	public Tile[][] getLevel1() {
		return level1;
	}

	public void setLevel1(Tile[][] level1) {
		this.level1 = level1;
	}

	public Tile[][] getLevel2() {
		return level2;
	}

	public void setLevel2(Tile[][] level2) {
		this.level2 = level2;
	}

	//converts array to actual map
	public void drawMap(java.awt.Graphics g) {
		level = null;
		//sets to current level
		switch(getLvl()) {
		case 1: level = level1; break;
		case 2: level = level2; break;
		}
		//draws the map on the JFrame
		int x = dim/level.length;
		int y = dim/level[0].length;
		for(int i = 0; i < level.length; i++) {
			for(int j = 0; j < level[i].length; j++) {
				g.drawString(level[i][j].toString(), j*y, i*x);
			}
		}
	}

	public static int getLvl() {
		return lvl;
	}

	public static void setLvl(int lvl) {
		Map.lvl = lvl;
	}

	public static ArrayList<Tile> inventory() {
		return inventory;
	}
	
	public static ArrayList<Items> updateInventory(int locx, int locy, ArrayList<Items> choices) {
		choices.clear();
		inventory.clear();
		for(int i = 0; i < level.length; i++) {
			for(int j = 0; j < level[i].length; j++) {
				//Doors
				if((level[i][j] instanceof Door) && ((Door)(level[i][j])).canUse(locx, locy)) {
					choices.add(Items.DOORS);
					inventory.add(level[i][j]);
				}
				//Stairs
				if((level[i][j] instanceof Stairs) && ((Stairs)(level[i][j])).canUse(locx, locy)) {
					choices.add(Items.STAIRS);
					inventory.add(level[i][j]);
				}
			}
		}
		
		if(choices.size() == 0) choices.add(Items.NONE);
		return choices;
	}
	
	public static void act(Items item) {
		if(item.equals(Items.DOORS)) {
			((Door) inventory.get(0)).setOpen(!(((Door) inventory.get(0)).getOpen()));
		}
	}
	
}