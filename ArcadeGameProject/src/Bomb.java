import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * A black, circular graphic that can destroy BrickWall, dropped when B pressed
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 */
public class Bomb {
	private double x;
	private double y;
	private ArrayList<Tile> tiles;
	private double size;
	protected Hero hero;
	private double range;
	private ArrayList<Monster> monsters;
	private ArrayList<Tile> surroundingTiles = new ArrayList<>();
	protected Tile bombTile = null;

	/**
	 * 
	 * Sets the coordinates of Bomb
	 *
	 * @param d
	 *            x-coordinate
	 * @param e
	 *            y-coordinate
	 * @param monsters
	 */
	public Bomb(double d, double e, ArrayList<Tile> tiles, Hero hero, ArrayList<Monster> monsters) {
		this.x = d;
		this.y = e;
		this.tiles = tiles;
		Timer timer = new Timer();
		timer.schedule(new Task(), 5000);
		this.size = 48;
		this.hero = hero;
		this.setRange(1);
		this.monsters = monsters;
		Bomb.this.setBombTile();
		Bomb.this.bombTile.setPassable(false);
	}

	class Task extends TimerTask {

		@Override
		public void run() {
			System.out.println(Bomb.this.hero.bombs.toString());
			Bomb.this.explode();
			System.out.println("Bomb has exploded");
			
			Bomb.this.hero.bombs.remove(0);
			System.out.println(Bomb.this.hero.bombs.toString());
			Bomb.this.bombTile.setPassable(true);
		}
	}
	
	@Override
	public String toString() {
		
		return "Bomb at " + this.x +", "+this.y;
	}

	/**
	 * 
	 * Draws image of Bomb
	 * 
	 * @param graphics
	 *            Graphics that Bomb is painted onto
	 * 
	 */
	public void drawCharacter(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		Graphics2D gCast = (Graphics2D) graphics;
		Ellipse2D.Double bomb = new Ellipse2D.Double(this.x, this.y, this.size, this.size);
		gCast.fill(bomb);
	}

	public void explode() {
		// remove bomb from screen
		// find tile that bomb is in
		// use tile coordinates to find 4 tiles/characters surrounding it
		// determine if objects are destructible, if so remove them from screen.

		// remove bomb from arraylist of bombs
		// remove graphic of bomb from screen

		int ux = this.bombTile.getX1() + 24;
		int uy = this.bombTile.getY1() - 24;
		
		int lx = this.bombTile.getX1() - 24;
		int ly = this.bombTile.getY1() + 24;
		
		int rx = this.bombTile.getX2() + 24;
		int ry = this.bombTile.getY2() -24;
		
		int dx = this.bombTile.getX1() + 24;
		int dy = this.bombTile.getY2() + 24;

		Tile tileUp = null;
		Tile tileRight = null;
		Tile tileLeft = null;
		Tile tileDown = null;

		for (Tile tile : this.tiles) {
			if (tile.getX1() <= ux && tile.getX2() >= ux && tile.getY1() <= uy && tile.getY2() >= uy) {
				tileUp = tile;
				this.surroundingTiles.add(tileUp);
			} else if (tile.getX1() <= rx && tile.getX2() >= rx && tile.getY1() <= ry && tile.getY2() >= ry) {
				tileRight = tile;
				this.surroundingTiles.add(tileRight);
			} else if (tile.getX1() <= lx && tile.getX2() >= lx && tile.getY1() <= ly && tile.getY2() >= ly) {
				tileLeft = tile;
				this.surroundingTiles.add(tileLeft);
			} else if (tile.getX1() <= dx && tile.getX2() >= dx && tile.getY1() <= dy && tile.getY2() >= dy) {
				tileDown = tile;
				this.surroundingTiles.add(tileDown);
			}
		}
		for (Tile tile : this.surroundingTiles) {
			if (tile.isDestructible()) {
				tile.createNewGroundTile();
			}
		}
		// if hero is in surroundingTiles, hero.death
		this.destroyCharacters();
		// if bomb is in surroundingTiles, explode bomb
	}

	public void destroyCharacters() {
		for (Monster m : this.monsters) {
			for (Tile tile : this.surroundingTiles) {
				if (m.checkIfInTile(tile)) {
					System.out.println("Monster Died");
				}
			}
		}

	}

	public double getRange() {
		return this.range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public void setBombTile() {
		for (Tile tile : this.tiles) {
			if (this.x == tile.getX1() && this.y == tile.getY1()) {
				this.bombTile = tile;
				this.surroundingTiles.add(this.bombTile);
			}
		}
	}

}
