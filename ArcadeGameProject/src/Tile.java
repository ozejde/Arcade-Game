import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 * 
 * Abstract class that creates Tiles
 *
 * @author ejdeoz.
 *         Created Oct 27, 2016.
 */
public abstract class Tile {
	private int ulhx = 0;
	private int ulhy = 0;
	private int lrhx = 0;
	private int lrhy = 0;
	private static final int SIZE = 48;
	private boolean destructible = false;
	private boolean passable = false;
	private boolean hasChanged = false;

	private BufferedImage tileSheet;

	/**
	 * 
	 * Returns if tile is destructible
	 *
	 * @return
	 * distructible 
	 */
	public boolean isDestructible() {
		return this.destructible;
	}

	public void setDestructible(boolean destructible) {
		this.destructible = destructible;
	}

	public boolean isPassable() {
		return this.passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

	private int indexX;
	private int indexY;
	private int x1,x2,y1,y2;

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
		System.out.println((this.indexX * Engine.TILE_WIDTH) +" "+ (this.indexY * Engine.TILE_HIEGHT));
		System.out.println(((this.indexX * Engine.TILE_WIDTH) + Engine.TILE_WIDTH) +" "+ ((this.indexY * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT));
		System.out.println();
	}

	public int getX1() {
		return this.x1;
	}

	public int getX2() {
		return this.x2;
	}

	public int getY1() {
		return this.y1;
	}

	public int getY2() {
		return this.y2;
	}


	public void drawTile(Graphics g) {
		g.drawImage(this.tileSheet, this.indexX * Engine.TILE_WIDTH, this.indexY * Engine.TILE_HIEGHT,
				(this.indexX * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
				(this.indexY * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.ulhx, this.ulhy, this.lrhx, this.lrhy,
				null);
	}

}
