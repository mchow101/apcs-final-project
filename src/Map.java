
//Contains all the Tiles of the map
//Also contains variables to control inventory
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;

public class Map implements ImageObserver {
	// Arrays for each level
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
		setMap(BluePrint.bp1(), 1);
		// level 2
		setMap(BluePrint.bp2(), 2);
		// level 3
		setMap(BluePrint.bp3(), 3);
		// level 4
		setMap(BluePrint.bp4(), 4);
		// level 5
		setMap(BluePrint.bp5(), 5);
		setLevel(level1);
	}

	// translate character blueprint to tile array
	public void setMap(char[][] levelbp, int l) {
		level = new Tile[50][50];

		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[i].length; j++) {
				switch (levelbp[i][j]) {
				case '.':
					level[i][j] = new EmptySpace();
					break;
				case 'w':
					level[i][j] = new Wall();
					break;
				case 'd':
					level[i][j] = new Door(j, i);
					break;
				case 'L':
					level[i][j] = new Stairs(j, i);
					break;
				case 'h':
					level[i][j] = new HealthPotion(j, i);
					break;
				case 's':
					level[i][j] = new StrengthPotion(j, i);
					break;
				case 'm':
					level[i][j] = new MaxHealthPotion(j, i);
					break;
				case 'e':
					level[i][j] = new EnemyScroll(j, i);
					break;
				case 'W':
					level[i][j] = new Weapons(j, i);
					break;
				case 'a':
					level[i][j] = new Armor(j, i);
					break;
				case 'l':
					level[i][j] = new LightningWand(j, i);
					break;
				case 'b':
					level[i][j] = new LightningBall(j, i);
					break;
				default:
					level[i][j] = new EmptySpace();
				}
			}
		}

		// sets to current level
		switch (l) {
		case 1:
			level1 = level;
			break;
		case 2:
			level2 = level;
			break;
		case 3:
			level3 = level;
			break;
		case 4:
			level4 = level;
			break;
		case 5:
			level5 = level;
			break;
		}
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
					double hp = (double) ((Charecter) (level[i][j])).getHealth()
							/ ((Charecter) (level[i][j])).getMaxHealth();
					g.setColor(new Color((int) (225 - 225 * hp), (int) (hp * 255), (int) (hp * 150)));
				} else if (level[i][j] instanceof Creature) {
					double hp = (double) ((Creature) (level[i][j])).getHealth()
							/ ((Creature) (level[i][j])).getMaxHealth();
					g.setColor(new Color((int) (225 - 225 * hp), (int) (hp * 150), (int) (hp * 100)));
				} else if (level[i][j].toString().equals(".")) {
					g.setColor(Color.WHITE);
				} 
				// sets images based on Tile
				if (level[i][j].toString().equals(".")) {
					Image image = Toolkit.getDefaultToolkit().getImage("src/floor.jpg");
					// image from
					// http://www.dundjinni.com/forums/uploads/heruca/Dungeon_Floor_Tile_hrc.jpg
					g.drawImage(image, j * y, i * x, x, y, this);
				} else if (level[i][j].toString().equals("#")) {
					Image image = Toolkit.getDefaultToolkit().getImage("src/wall.jpg");
					// image from
					// https://s3.envato.com/files/45171752/main2_590.JPG
					g.drawImage(image, j * y, i * x, x, y, this);
				} else if (level[i][j].toString().equals("+")) {
					Image image = Toolkit.getDefaultToolkit().getImage("src/closedoor.jpg");
					// image from
					// https://orig00.deviantart.net/a007/f/2012/074/7/6/rpg_maker_vx___gate_iv_by_ayene_chan-d4sutyi.png
					g.drawImage(image, j * y, i * x, x, y, this);
				} else if (level[i][j].toString().equals("'")) {
					Image image = Toolkit.getDefaultToolkit().getImage("src/opendoor.jpg");
					// image from
					// https://orig00.deviantart.net/a007/f/2012/074/7/6/rpg_maker_vx___gate_iv_by_ayene_chan-d4sutyi.png
					g.drawImage(image, j * y, i * x, x, y, this);
				} else if (level[i][j].toString().equals("%")) {
					Image image = Toolkit.getDefaultToolkit().getImage("src/potion.png");
					// image from
					// http://game-icons.net/tags/glass.html
					g.drawImage(image, j * y, i * x, x, y, this);
				}  else if (level[i][j].toString().equals("$")) {
					Image image = Toolkit.getDefaultToolkit().getImage("src/scroll.png");
					// image from
					// http://game-icons.net/lorc/originals/tied-scroll.html
					g.drawImage(image, j * y, i * x, x, y, this);
				} else if (level[i][j].toString().equals("-")) {
					Image image = Toolkit.getDefaultToolkit().getImage("src/wand.png");
					// image from
					// http://game-icons.net/tags/wand.html 
					g.drawImage(image, j * y, i * x, x, y, this);
				} else if (level[i][j].toString().equals("()")) {
					Image image = Toolkit.getDefaultToolkit().getImage("src/armor.png");
					// image from
					// http://game-icons.net/tags/armor.html 
					g.drawImage(image, j * y, i * x, x, y, this);
				} else if (level[i][j].toString().equals("/")) {
					Image image = Toolkit.getDefaultToolkit().getImage("src/weapon.png");
					// image from
					// http://game-icons.net/tags/blade.html
					g.drawImage(image, j * y, i * x, x, y, this);
				} else {
					Image image = Toolkit.getDefaultToolkit().getImage("src/floor.jpg");
					// image from
					// http://www.dundjinni.com/forums/uploads/heruca/Dungeon_Floor_Tile_hrc.jpg
					g.drawImage(image, j * y, i * x, x, y, this);
					g.drawString(level[i][j].toString(), j * y + 5, i * x + 15);
				}
			}
		}
	}

	// getters and setters

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

	@Override
	public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
		// TODO Auto-generated method stub
		return true;
	}

}