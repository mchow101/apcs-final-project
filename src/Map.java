//Contains all the Tiles of the map
//Also contains variables to control inventory
import java.awt.Color;

public class Map {
	//Arrays for each level
	private Tile[][] level1;
	private Tile[][] level2;
	private Tile[][] level3;
	private Tile[][] level4;
	private Tile[][] level5;
	private int dim;
	// variables for inventory and level tracking
	private Tile[][] level;
	private int lvl = 1;

	public Map(int dim) {
		this.dim = dim;
		// character blueprint for the map
		char[][] level1bp =  BluePrint.bp1();
			  
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
				case 'L':
					level1[i][j] = new Stairs(j, i);
					break;
				case 'h':
					level1[i][j] = new HealthPotion(j, i);
					break;
				case 's': 
					level1[i][j] = new StrengthPotion(j, i);
					break;
				case 'm':
					level1[i][j] = new MaxHealthPotion(j, i);
					break;
				case 'e':
					level1[i][j] = new EnemyScroll(j, i);
					break;
				case 'W':
					level1[i][j] = new Weapons(j, i);
					break;
				case 'a':
					level1[i][j] = new Armor(j, i);
					break;
				case 'l':
					level1[i][j] = new LightningWand(j, i);
					break;
				case 'b':
					level1[i][j] = new LightningBall(j, i);
					break;
				default:
					level1[i][j] = new EmptySpace();
				}
			}
		}
		//level 2
		char[][] level2bp =  BluePrint.bp2();
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
				case 'L':
					level2[i][j] = new Stairs(j, i);
					break;
				case 'h':
					level2[i][j] = new HealthPotion(j, i);
					break;
				case 's': 
					level2[i][j] = new StrengthPotion(j, i);
					break;
				case 'm':
					level2[i][j] = new MaxHealthPotion(j, i);
					break;
				case 'e':
					level2[i][j] = new EnemyScroll(j, i);
					break;
				case 'W':
					level2[i][j] = new Weapons(j, i);
					break;
				case 'a':
					level2[i][j] = new Armor(j, i);
					break;
				case 'l':
					level2[i][j] = new LightningWand(j, i);
					break;
				case 'b':
					level2[i][j] = new LightningBall(j, i);
					break;
				default:
					level2[i][j] = new EmptySpace();
				}
			}
		}
		//level 3
		char[][] level3bp = BluePrint.bp3();
		level3 = new Tile[50][50];
			for (int i = 0; i < level3.length; i++) {
				for (int j = 0; j < level3[i].length; j++) {
					switch (level3bp[i][j]) {
					case '.':
						level3[i][j] = new EmptySpace();
						break;
					case 'w':
						level3[i][j] = new Wall();
						break;
					case 'd':
						level3[i][j] = new Door(j, i);
						break;
					case 'L':
						level3[i][j] = new Stairs(j, i);
						break;
					case 'h':
						level3[i][j] = new HealthPotion(j, i);
						break;
					case 's': 
						level3[i][j] = new StrengthPotion(j, i);
						break;
					case 'm':
						level3[i][j] = new MaxHealthPotion(j, i);
						break;
					case 'e':
						level3[i][j] = new EnemyScroll(j, i);
						break;
					case 'W':
						level3[i][j] = new Weapons(j, i);
						break;
					case 'a':
						level3[i][j] = new Armor(j, i);
						break;
					case 'l':
						level3[i][j] = new LightningWand(j, i);
						break;
					case 'b':
						level3[i][j] = new LightningBall(j, i);
						break;
					default:
						level3[i][j] = new EmptySpace();
					}
				}
			}
			//level 4
			char[][] level4bp = BluePrint.bp4();	   
			level4 = new Tile[50][50];
				for (int i = 0; i < level4.length; i++) {
					for (int j = 0; j < level4[i].length; j++) {
						switch (level4bp[i][j]) {
						case '.':
							level4[i][j] = new EmptySpace();
							break;
						case 'w':
							level4[i][j] = new Wall();
							break;
						case 'd':
							level4[i][j] = new Door(j, i);
							break;
						case 'L':
							level4[i][j] = new Stairs(j, i);
							break;
						case 'h':
							level4[i][j] = new HealthPotion(j, i);
							break;
						case 's': 
							level4[i][j] = new StrengthPotion(j, i);
							break;
						case 'm':
							level4[i][j] = new MaxHealthPotion(j, i);
							break;
						case 'e':
							level4[i][j] = new EnemyScroll(j, i);
							break;
						case 'W':
							level4[i][j] = new Weapons(j, i);
							break;
						case 'a':
							level4[i][j] = new Armor(j, i);
							break;
						case 'l':
							level4[i][j] = new LightningWand(j, i);
							break;
						case 'b':
							level4[i][j] = new LightningBall(j, i);
							break;
						default:
							level4[i][j] = new EmptySpace();
						}
					}
				}
			//level 5
			char[][] level5bp = BluePrint.bp5();
			level5 = new Tile[50][50];
				for (int i = 0; i < level5.length; i++) {
					for (int j = 0; j < level5[i].length; j++) {
						switch (level5bp[i][j]) {
						case '.':
							level5[i][j] = new EmptySpace();
							break;
						case 'w':
							level5[i][j] = new Wall();
							break;
						case 'd':
							level5[i][j] = new Door(j, i);
							break;
						case 'L':
							level5[i][j] = new Stairs(j, i);
							break;
						case 'h':
							level5[i][j] = new HealthPotion(j, i);
							break;
						case 's': 
							level5[i][j] = new StrengthPotion(j, i);
							break;
						case 'm':
							level5[i][j] = new MaxHealthPotion(j, i);
							break;
						case 'e':
							level5[i][j] = new EnemyScroll(j, i);
							break;
						case 'W':
							level5[i][j] = new Weapons(j, i);
							break;
						case 'a':
							level5[i][j] = new Armor(j, i);
							break;
						case 'l':
							level5[i][j] = new LightningWand(j, i);
							break;
						case 'b':
							level5[i][j] = new LightningBall(j, i);
							break;
						default:
							level5[i][j] = new EmptySpace();
						}
					}
				}		
		setLevel(level1);
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
		case 3:
			level = level3;
			break;
		case 4: 
			level = level4;
			break;
		case 5: 
			level = level5;
			break;
		default:
			level = level1;
		}
		// draws the map on the JFrame
		int x = dim / level.length;
		int y = dim / level[0].length;
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[i].length; j++) {
				g.setColor(Color.WHITE);
				// different colors for different types of Tiles
				if (level[i][j] instanceof Door) {
					g.setColor(Color.DARK_GRAY);
				} else if (level[i][j] instanceof Stairs || level[i][j] instanceof Wall) {
					g.setColor(Color.LIGHT_GRAY);
				} else if (level[i][j] instanceof HealthPotion || level[i][j] instanceof MaxHealthPotion) {
					g.setColor(Color.ORANGE.darker());
				} else if (level[i][j] instanceof StrengthPotion) {
					g.setColor(Color.ORANGE.brighter());
				} else if (level[i][j] instanceof Wand) {
					g.setColor(Color.MAGENTA.brighter());
				} else if (level[i][j] instanceof Armor) {
					g.setColor(Color.BLUE);
				} else if (level[i][j] instanceof Weapons) {
					g.setColor(Color.PINK);
				} else if (level[i][j] instanceof Scroll) {
					g.setColor(Color.MAGENTA.darker());
				} else if (level[i][j] instanceof Charecter) {
					double hp = (double)((Charecter) (level[i][j])).getHealth()/((Charecter) (level[i][j])).getMaxHealth();
					g.setColor(new Color((int)(225 - 225*hp), (int)(hp*255), (int)(hp*150)));
				} else if (level[i][j] instanceof Creature) {
					double hp = (double)((Creature) (level[i][j])).getHealth()/((Creature) (level[i][j])).getMaxHealth();
					g.setColor(new Color((int)(225 - 225*hp), (int)(hp*150), (int)(hp*100)));
				} else if (level[i][j].toString().equals(".")) {
					g.setColor(Color.WHITE);
				}
				g.drawString(level[i][j].toString(), j * y, i * x + 10);
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