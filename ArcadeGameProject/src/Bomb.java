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
	protected double x;
	protected double y;
	protected ArrayList<Tile> tiles;
	protected double size;
	protected Hero hero;
	protected double range;
	protected ArrayList<Monster> monsters;
	private ArrayList<Tile> surroundingTiles = new ArrayList<>();
	protected Tile bombTile;
	protected boolean removed;
	protected Timer timer;
	protected Timer leave;
	protected boolean isDetonatable;

	public Bomb(double d, double e, ArrayList<Tile> tiles, Hero hero, ArrayList<Monster> monsters, double range,
			boolean isDetonatable) {
		this.x = d;
		this.y = e;
		this.tiles = tiles;
		this.removed = false;
		this.leave = new Timer();
		this.leave.schedule(new LeaveTimer(), 2000);
		this.size = 48;
		this.hero = hero;
		this.range = range;
		this.monsters = monsters;
		this.isDetonatable = isDetonatable;
		setBombTile();
	}

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
	public Bomb(double d, double e, ArrayList<Tile> tiles, Hero hero, ArrayList<Monster> monsters, double range) {
		this.x = d;
		this.y = e;
		this.tiles = tiles;
		this.removed = false;
		this.timer = new Timer();
		this.timer.schedule(new Task(), 3000);
		this.leave = new Timer();
		this.leave.schedule(new LeaveTimer(), 2000);
		this.size = 48;
		this.hero = hero;
		this.range = range;
		this.monsters = monsters;
		setBombTile();
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
			Bomb.this.getBombTile().setPassable(false);
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
				Bomb.this.hero.bombs.remove(Bomb.this);
				Bomb.this.explode();
				Bomb.this.getBombTile().setPassable(true);
			}

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
		for (int i = 1; i <= this.range; i++) {
			// x coordinate of the center of the tile above the tile containing
			// the
			// bomb
			double ux = this.bombTile.getX1() + 24;
			// y coordinate of the center of the tile above the tile containing
			// the
			// bomb
			double uy = this.bombTile.getY1() - 24 * i;

			// x coordinate of the center of the tile to the left the tile
			// containing the bomb
			double lx = this.bombTile.getX1() - 24 * i;
			// y coordinate of the center of the tile to the left the tile
			// containing the bomb
			double ly = this.bombTile.getY1() + 24;

			// x coordinate of the center of the tile to the right the tile
			// containing the bomb
			double rx = this.bombTile.getX2() + 24 * i;
			// y coordinate of the center of the tile to the right the tile
			// containing the bomb
			double ry = this.bombTile.getY2() - 24;

			// x coordinate of the center of the tile below the tile containing
			// the
			// bomb
			double dx = this.bombTile.getX1() + 24;
			// y coordinate of the center of the tile below the tile containing
			// the
			// bomb
			double dy = this.bombTile.getY2() + 24 * i;

			Tile tileUp = null;
			Tile tileRight = null;
			Tile tileLeft = null;
			Tile tileDown = null;

			// finds the tiles above, below, to the right, and to the left of
			// the
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
		}

		// blows up the tiles which can be destroyed
		for (Tile tile : this.surroundingTiles) {
			if (tile.isDestructible()) {
				tile.setBlownUp(true);
				if (tile.getPowerUp()) {
					tile.createPowerUpTile(tile.getPowerTileType());
				} else {
					tile.createNewGroundTile();
				}
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
		
		if(this.isDetonatable){
			this.bombTile.setPassable(true);
			this.hero.bombs.remove(this);
		}
	}

	/**
	 * 
	 * Blows up any bombs within the blast radius of the bomb.
	 * 
	 */
	private void destroyBombs() {
		ArrayList<Bomb> toRemove = new ArrayList<>();
		for (Bomb b : Bomb.this.hero.bombs) {
			if (!b.equals(Bomb.this)) {
				for (Tile tile : this.surroundingTiles) {
					int tileX = tile.getX1();
					int tileY = tile.getY2();
					int bombX = b.getBombTile().getX1();
					int bombY = b.getBombTile().getY2();
					if (tileX == bombX && tileY == bombY) {
						toRemove.add(b);
					}
				}
			}
		}
		Bomb.this.hero.bombs.removeAll(toRemove);
		for (Bomb b : toRemove) {
			b.leave.cancel();
			b.leave.purge();
			b.explode();
			b.bombTile.setPassable(true);
			b.setRemoved();
			if (!b.isDetonatable) {
				b.timer.cancel();
				b.timer.purge();
			}
		}
	}

	/**
	 * 
	 * Blows up any characters within the blast radius of the bomb.
	 * 
	 */
	public void destroyMonsters() {
		ArrayList<Monster> toRemove = new ArrayList<>();
		for (Monster m : this.monsters) {
			for (Tile tile : this.surroundingTiles) {
				if (m.checkIfInTile(tile)) {
					toRemove.add(m);
				}
			}
		}
		this.monsters.removeAll(toRemove);
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
	 * Finds and sets the bombTile which is the tile in which the bomb is
	 * located.
	 * 
	 * @return
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
