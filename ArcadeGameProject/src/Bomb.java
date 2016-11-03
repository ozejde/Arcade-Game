import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Iterator;
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
	private Tile bombTile = null;
	protected boolean removed;
	protected Timer timer;
	protected Timer leave;

	/**
	 * 
	 * Sets the coordinates of Bomb
	 *
	 * @param d
	 *            x-coordinate
	 * @param e
	 *            y-coordinate
	 * @param tiles
	 * 
	 * @param hero
	 * 
	 * @param monsters
	 * 
	 */
	public Bomb(double d, double e, ArrayList<Tile> tiles, Hero hero, ArrayList<Monster> monsters) {
		this.x = d;
		this.y = e;
		this.tiles = tiles;
		this.removed = false;
		this.timer = new Timer();
		this.timer.schedule(new Task(), 5000);
		this.leave = new Timer();
		this.leave.schedule(new LeaveTimer(), 2000);
		this.size = 48;
		this.hero = hero;
		this.setRange(1);
		this.monsters = monsters;
		Bomb.this.setBombTile();

	}

	/**
	 * 
	 * Class to set timer that allows the hero to leave the tile that they
	 * placed a bomb in for up to two seconds.
	 *
	 * @author youngqom. Created Nov 1, 2016.
	 */
	class LeaveTimer extends TimerTask {
		@Override
		public void run() {
			Bomb.this.bombTile.setPassable(false);
		}
	}

	/**
	 * 
	 * Class to handle a bomb interactions with the environment after a given
	 * amount of time has passed.
	 *
	 * @author youngqom. Created Nov 1, 2016.
	 */
	class Task extends TimerTask {

		@Override
		public void run() {
			if (!Bomb.this.removed) {
				Bomb.this.hero.bombs.remove(0);
			}
			Bomb.this.bombTile.setPassable(true);
			Bomb.this.explode();
		}
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

	/**
	 * 
	 * Sets removed variable to true.
	 *
	 */
	public void setRemoved() {
		this.removed = true;
	}

	/**
	 * 
	 * Simulates the explosion of a bomb by destroying the brick walls, killing
	 * any character(s), and exploding any bomb(s) within the blast radius of
	 * the bomb.
	 * 
	 */
	public void explode() {

		// x coordinate of the center of the tile above the tile containing the
		// bomb
		int ux = this.bombTile.getX1() + 24;
		// y coordinate of the center of the tile above the tile containing the
		// bomb
		int uy = this.bombTile.getY1() - 24;

		// x coordinate of the center of the tile to the left the tile
		// containing the bomb
		int lx = this.bombTile.getX1() - 24;
		// y coordinate of the center of the tile to the left the tile
		// containing the bomb
		int ly = this.bombTile.getY1() + 24;

		// x coordinate of the center of the tile to the right the tile
		// containing the bomb
		int rx = this.bombTile.getX2() + 24;
		// y coordinate of the center of the tile to the right the tile
		// containing the bomb
		int ry = this.bombTile.getY2() - 24;

		// x coordinate of the center of the tile below the tile containing the
		// bomb
		int dx = this.bombTile.getX1() + 24;
		// y coordinate of the center of the tile below the tile containing the
		// bomb
		int dy = this.bombTile.getY2() + 24;

		Tile tileUp = null;
		Tile tileRight = null;
		Tile tileLeft = null;
		Tile tileDown = null;

		// finds the tiles above, below, to the right, and to the left of the
		// tile containing the bomb
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

		// blows up the tiles which can be destroyed
		for (Tile tile : this.surroundingTiles) {
			if (tile.isDestructible()) {
				tile.createNewGroundTile();
			}
		}

		// blows up the characters in the blast radius of the bomb
		if (!this.monsters.isEmpty()) {
			this.destroyMonsters();
		}
		// Kill hero
		this.killHero();
		// blows up any bombs in the blast radius of the bomb
		this.destroyBombs();
	}

	/**
	 * 
	 * Blows up any bombs within the blast radius of the bomb.
	 * 
	 */
	private void destroyBombs() {
		Iterator<Bomb> bombIterator = Bomb.this.hero.bombs.iterator();
		while (bombIterator.hasNext()) {
			Bomb bomb = bombIterator.next();
			if (!bomb.equals(Bomb.this)) {
				for (Tile tile : this.surroundingTiles) {
					int tileX = tile.getX1();
					int tileY = tile.getY2();
					int bombX = bomb.getBombTile().getX1();
					int bombY = bomb.getBombTile().getY2();

					if (tileX == bombX && tileY == bombY) {
						bomb.explode();
//						bomb.setRemoved();
						bombIterator.remove();
						bomb.bombTile.setPassable(true);
						bomb.timer.cancel();
						bomb.timer.purge();
					}
				}
			}
		}
	}

	/**
	 * 
	 * Blows up any characters within the blast radius of the bomb.
	 * 
	 */
	public void destroyMonsters() {
		for (Monster m : this.monsters) {
			for (Tile tile : this.surroundingTiles) {
				if (m.checkIfInTile(tile)) {
					this.monsters.remove(m);
				}
			}
		}
	}

	public void killHero() {
		for (Tile tile : this.surroundingTiles) {
			if (this.hero.checkIfInTile(tile)) {
				this.hero.subtractLife();
				this.hero.reset();
			}
		}
	}

	/**
	 * 
	 * Returns the range of the bomb's blast radius.
	 *
	 * @return double The range of the bomb's blast radius.
	 */
	public double getRange() {
		return this.range;
	}

	/**
	 * 
	 * Sets the range of the bomb's blast radius.
	 *
	 * @param double
	 *            The range of the bomb's blast radius.
	 */
	public void setRange(double range) {
		this.range = range;
	}

	/**
	 * 
	 * Finds and sets the bombTile which is the tile in which the bomb is
	 * located.
	 *
	 */
	public void setBombTile() {
		for (Tile tile : this.tiles) {
			if (this.x == tile.getX1() && this.y == tile.getY1()) {
				this.bombTile = tile;
				this.surroundingTiles.add(this.bombTile);
				this.bombTile.setPassable(true);
			}
		}
	}

	/**
	 * 
	 * Returns the tile which contains the bomb.
	 *
	 * @return Tile The tile containing the bomb.
	 */
	public Tile getBombTile() {
		return this.bombTile;
	}

}
