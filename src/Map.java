//Contains all the Tiles of the map
//Also contains variables to control inventory
import java.awt.Color;

public class Map {
	//Arrays for each level
	private Tile[][] level1;
	private Tile[][] level2;
	private int dim;
	// variables for inventory and level tracking
	private Tile[][] level;
	private int lvl = 1;

	public Map(int dim) {
		this.dim = dim;
		// character blueprint for the map
		char[][] level1bp =  {{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
				{'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'},
				{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
				{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
				{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','w','w','w','w','w','d','w','w','w','w','w','w','w','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
				{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','d','.','.','.','.','.','.','.','.','.','.','.','.','.','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','.','.','.','.','w'},
				{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','w','w','w','w','w','.','w','w','w','w','w','w','d','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','.','d','.','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','.','w','h','.','.','.','.','.','w','.','.','.','.','w','.','w','w','w','w','w','w','w','w','w','w','w','w','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','.','w','.','.','.','.','.','.','.','.','.','.','.','w','w','d','w','w','w','d','w','w','w','d','w','w','w','d','w','w','.','.','.','.','w'},
				{'w','.','w','.','.','.','s','.','.','w','.','.','.','.','w','.','d','.','.','.','.','.','.','.','.','.','.','.','d','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','d','.','.','.','.','w'},
				{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','.','w','.','.','.','.','.','.','.','.','.','.','.','w','w','d','w','w','w','d','w','w','w','d','w','w','w','d','w','w','.','.','.','.','w'},
				{'w','.','w','.','.','.','.','.','.','w','d','w','w','w','w','d','w','w','w','w','w','w','w','w','w','w','w','w','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','.','w','.','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','d','w','w','w','w','w','w','w','w','w','w','w','.','w','d','w','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','.','.','d','.','.','.','.','.','.','.','.','w','.','w','.','.','w','.','.','.','.','.','.','.','.','.','.','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','d','w','w'},
				{'w','.','.','w','w','w','w','.','.','.','.','.','w','d','w','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','w','w','d','w','w','w','w','w','w','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','d','.','.','.','.','.','.','.','.','d','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
				{'w','w','w','w','w','w','d','w','w','w','w','d','w','w','w','w','w','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','w','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','w','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','w','w','w','w','w','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','.','.','w','.','.','.','.','w','w','d','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','d','w','w','w','d','w','w','w'},
				{'w','.','.','.','.','w','w','w','w','w','w','.','w','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','w','.','d','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','d','.','.','.','.','w','.','w','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','w','.','w','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','w','w','w','w','w','d','w','w','w','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','.','.','.','.','w','.','.','.','d','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','.','.','.','.','w','w','d','w','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','d','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','w','d','w','w','w','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','.','.','.','.','w','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','w','w','w','w','w','w','w','w','w','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','d','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
				{'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'}
				};
		//translate character blueprint to tile array
		level1 = new Tile[50][50];
		for (int i = 0; i < level1.length; i++) {
			for (int j = 0; j < level1[i].length; j++) {
				switch (level1bp[i][j]) {
				case '.':
					level1[i][j] = new EmptySpace();
					break;
				case 'w':
					level1[i][j] = new Wall();
					break;
				case 'd':
					level1[i][j] = new Door(j, i);
					break;
				case 's':
					level1[i][j] = new Stairs(j, i);
					break;
				case 'h':
					level1[i][j] = new HealthPotion(j, i);
					break;
				default:
					level1[i][j] = new EmptySpace();
				}
			}
		}
		//level 2
		char[][] level2bp =  {{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
				{'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'},
				{'w','.','.','.','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','w','.','.','.','.','w','w','w','w','w','w','d','w','w','w','w','w','w','w','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','w','.','.','.','.','d','.','.','.','.','.','.','.','.','.','.','.','.','.','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','w','.','.','.','.','w','w','w','w','w','w','.','w','w','w','w','w','w','d','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','w','.','.','.','.','w','.','w','w','w','w','w','w','w','w','w','w','w','w','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','w','.','.','.','.','w','.','w','.','.','.','.','.','.','.','.','.','.','.','w','w','d','w','w','w','d','w','w','w','d','w','w','w','d','w','w','.','.','.','.','w'},
				{'w','.','.','.','.','.','s','.','.','w','.','.','.','.','w','.','d','.','.','.','.','.','.','.','.','.','.','.','d','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','d','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','w','.','.','.','.','w','.','w','.','.','.','.','.','.','.','.','.','.','.','w','w','d','w','w','w','d','w','w','w','d','w','w','w','d','w','w','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','w','d','w','w','w','w','d','w','w','w','w','w','w','w','w','w','w','w','w','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','d','w','w','w','w','w','w','w','w','w','w','w','.','w','d','w','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','w','.','.','.','.','w'},
				{'w','.','.','d','.','.','.','.','.','.','.','.','w','.','w','.','.','w','.','.','.','.','.','.','.','.','.','.','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','d','w','w'},
				{'w','.','.','w','w','w','w','.','.','.','.','.','w','d','w','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','w','.','.','w','.','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','w','w','d','w','w','w','w','w','w','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','d','.','.','.','.','.','.','.','.','d','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'},
				{'w','w','w','w','w','w','d','w','w','w','w','d','w','w','w','w','w','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','w','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','w','.','.','.','.','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','w','w','w','w','w','w','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','.','.','w','.','.','.','.','w','w','d','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','d','w','w','w','d','w','w','w'},
				{'w','.','.','.','.','w','w','w','w','w','w','.','w','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','w','.','d','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','d','.','.','.','.','w','.','w','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','w','.','w','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','w','w','w','w','w','d','w','w','w','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','.','.','.','.','w','.','.','.','d','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','.','.','.','.','w','w','d','w','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','d','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','w','d','w','w','w','.','.','.','.','.','.','.','.','w','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','.','.','.','.','.','.','.','.','w','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','w','w','w','w','w','w','w','w','w','w','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','d','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','.','w','w','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w','.','.','.','.','.','w'},
				{'w','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','w'}, 
				{'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'}
				};
		level2 = new Tile[50][50];
		for (int i = 0; i < level2.length; i++) {
			for (int j = 0; j < level2[i].length; j++) {
				switch (level2bp[i][j]) {
				case '.':
					level2[i][j] = new EmptySpace();
					break;
				case 'w':
					level2[i][j] = new Wall();
					break;
				case 'd':
					level2[i][j] = new Door(j, i);
					break;
				case 's':
					level2[i][j] = new Stairs(j, i);
					break;
//				case 'p':
//					level2[i][j] = new Potion(j, i);
//					break;
				}
			}
		}
//		inventory = new ArrayList<Tile>();
		setLevel(level1);
//		Map.graphics = mapGraphics;
	}

	// converts array to actual map
	public void drawMap(java.awt.Graphics g) {
		level = null;
		// sets to current level
		switch (getLvl()) {
		case 1:
			level = level1;
			break;
		case 2:
			level = level2;
			break;
		}
		// draws the map on the JFrame
		int x = dim / level.length;
		int y = dim / level[0].length;
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[i].length; j++) {
				g.setColor(Color.WHITE);
				// different colors for different types of Tiles
				if (level[i][j] instanceof Door) {
					g.setColor(Color.LIGHT_GRAY);
				} else if (level[i][j] instanceof Stairs) {
					g.setColor(Color.LIGHT_GRAY);
				} else if (level[i][j] instanceof Potion) {
					g.setColor(Color.MAGENTA);
				} else if (level[i][j] instanceof Charecter) {
					double hp = (double)((Charecter) (level[i][j])).getHealth()/((Charecter) (level[i][j])).getMaxHealth();
					g.setColor(new Color((int)(225 - 225*hp), (int)(hp*225), (int)(hp*225)));
				} else if (level[i][j] instanceof Creature) {
					double hp = (double)((Creature) (level[i][j])).getHealth()/((Creature) (level[i][j])).getMaxHealth();
					g.setColor(new Color((int)(225 - 225*hp), (int)(hp*225), (int)(hp*225)));
				}
				g.drawString(level[i][j].toString(), j * y, i * x);
			}
		}
	}

	//getters and setters

	public Tile[][] getLevel() {
		return level;
	}

	public void setLevel(Tile[][] level) {
		this.level = level;
	}

	public int getLvl() {
		return lvl;
	}

	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

}