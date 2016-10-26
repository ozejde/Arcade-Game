import java.awt.Graphics;
import java.awt.image.BufferedImage;

abstract class Character extends Tile implements Runnable {

	private int ulhx = 0;
	private int ulhy = 0;
	private int lrhx = 0;
	private int lrhy = 0;
	private static final int SIZE = 48;
	private boolean distructable = false;
	private boolean passable = false;
	private Graphics g;
	private BufferedImage tileSheet;
	private int indexX;
	private int indexY;

	public Character(int x1, int y1, int x2, int y2, Graphics g, BufferedImage tileSheet, int x, int y) {
		super(x1, y1, x2, y2, g, tileSheet, x, y);
		this.ulhx = x1;
		this.ulhy = y1;
		this.lrhx = x2;
		this.lrhy = y2;
		this.g = g;
		this.tileSheet = tileSheet;
		this.indexX = x;
		this.indexY = y;
	}

	public void drawTile() {
		this.g.drawImage(this.tileSheet, this.indexX * Engine.TILE_WIDTH, this.indexY * Engine.TILE_HIEGHT,
				(this.indexX * Engine.TILE_WIDTH) + Engine.TILE_WIDTH,
				(this.indexY * Engine.TILE_HIEGHT) + Engine.TILE_HIEGHT, this.ulhx, this.ulhy, this.lrhx, this.lrhy,
				null);
	}
	


	public boolean isDistructable() {
		return this.distructable;
	}

	public void setDistructable(boolean distructable) {
		this.distructable = distructable;
	}

	public boolean isPassable() {
		return this.passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

}
