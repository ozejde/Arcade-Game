import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * 
 * Abstract class that creates Tiles
 *
 * @author ejdeoz. Created Oct 27, 2016.
 */
public abstract class Tile {
	protected int ulhx = 0;
	protected int ulhy = 0;
	protected int lrhx = 0;
	protected int lrhy = 0;
	private static final int SIZE = 48;
	private boolean destructible = false;
	private boolean passable = false;
	private boolean hasChanged = false;

	protected BufferedImage tileSheet;

	/**
	 * 
	 * Returns whether Tile is destructible or not
	 *
	 * @return destructible field
	 */
	public boolean isDestructible() {
		return this.destructible;
	}

	/**
	 * 
	 * Sets destructible field to given boolean
	 *
	 * @param destructible
	 *            Boolean to change destructible to
	 */
	public void setDestructible(boolean destructible) {
		this.destructible = destructible;
	}

	/**
	 * 
	 * Returns whether Tile is passable or not
	 *
	 * @return passable field
	 */
	public boolean isPassable() {
		return this.passable;
	}

	/**
	 * 
	 * Sets passable field to given boolean
	 *
	 * @param passable
	 *            Boolean to change passable to
	 */
	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	protected int indexX;
	protected int indexY;
	private int x1, x2, y1, y2;

	/**
	 * Creates Tile object
	 *
	 * @param x1
	 *            x-coordinate at upper left-hand corner
	 * @param y1
	 *            y-coordinate at upper left-hand corner
	 * @param x2
	 *            x-coordinate at lower right-hand corner
	 * @param y2
	 *            y-coordinate at lower right-hand corner
	 * @param tileSheet
	 *            File being read from
	 * @param x
	 *            x-coordinate of index of Tile image
	 * @param y
	 *            y-coordinate of index of Tile image
	 */
	public Tile(int x1, int y1, int x2, int y2, BufferedImage tileSheet, int x, int y) {
		this.ulhx = x1;
		this.ulhy = y1;
		this.lrhx = x2;
		this.lrhy = y2;
		this.tileSheet = tileSheet;
		this.indexX = x;
		this.indexY = y;
		this.x1 = (this.indexX * Engine.TILE_WIDTH);
		this.y1 = (this.indexY * Engine.TILE_HIEGHT);
		this.x2 = ((this.indexX * Engine.TILE_WIDTH) + Engine.TILE_WIDTH);
		this.y2 = ((this.indexY * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT);

	}

	/**
	 * 
	 * Outputs x-coordinate at upper left-hand corner
	 *
	 * @return x-coordinate at upper left-hand corner
	 */
	public int getX1() {
		return this.x1;
	}

	/**
	 * 
	 * Outputs x-coordinate at lower right-hand corner
	 *
	 * @return x-coordinate at lower right-hand corner
	 */
	public int getX2() {
		return this.x2;
	}

	/**
	 * 
	 * Outputs y-coordinate at upper left-hand corner
	 *
	 * @return y-coordinate at upper left-hand corner
	 */
	public int getY1() {
		return this.y1;
	}

	/**
	 * 
	 * Outputs y-coordinate at lower right-hand corner
	 *
	 * @return y-coordinate at lower right-hand corner
	 */
	public int getY2() {
		return this.y2;
	}

	/**
	 * 
	 * Draws a Tile object
	 *
	 * @param g
	 *            Graphic to draw on
	 */
	public void drawTile(Graphics g) {
		g.drawImage(this.tileSheet, this.indexX * Engine.TILE_WIDTH, this.indexY * Engine.TILE_HIEGHT,
				(this.indexX * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
				(this.indexY * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.ulhx, this.ulhy, this.lrhx, this.lrhy,
				null);
	}

//	public Tile createNewGroundTile() {
//		return new GroundTile(this.getX1(), this.getY1(), this.getX2(), this.getY2(), this.tileSheet, 0, 0);
//	}
	public void createNewGroundTile() {
		this.ulhx = 0;
		this.ulhy = 0;
		this.lrhx = 48;
		this.lrhy = 48;
	}

}
