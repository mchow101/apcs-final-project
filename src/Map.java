public class Map {
	private Tile[][] level1;
	private Tile[][] level2;
	private int dim;
	
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
				case 'd': level1[i][j] = new Door(); break;
				case 's': level1[i][j] = new Stairs(); break;
				}
			}
		}
		level2 = level1;
		
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
		Tile[][] level = null;
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
	
}