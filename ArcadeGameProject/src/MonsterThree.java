import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * 
 * Class for MonsterThree objects, or the boss monster.
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 * 
 */
public class MonsterThree extends Monster implements GetTilesFunctions{
	protected HashMap<String, Tile> surroundingTiles;
	protected Tile currentTile;
	protected boolean movingUp;
	protected boolean movingDown;
	protected boolean isMovingRight;
	protected boolean isMovingLeft;
	protected int backup = 3;
	protected double speed = .15;
	private String previousTrue;
	private int lives = 2;
	protected ArrayList<Bomb> bombs;
	protected Hero hero;
	protected boolean firstTime;

	/**
	 * 
	 * Constructs a new MonsterThree object.
	 * 
	 * @param j
	 * 
	 * @param i
	 * 
	 * @param hero
	 *
	 */
	public MonsterThree(int i, int j, Hero hero) {
		super(i, j);
		this.setSize(34);
		this.setOffset(3.75);
		this.surroundingTiles = new HashMap<>();
		this.isMovingLeft = false;
		this.isMovingRight = false;
		this.movingUp = false;
		this.movingDown = true;
		this.previousTrue = "down";
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.scheduleAtFixedRate(this.bombRunnable, 0, 5, TimeUnit.SECONDS);
		this.bombs = new ArrayList<>();
		this.hero = hero;
		this.firstTime = true;
	}

	/**
	 * 
	 * Draws the MonsterThree object.
	 * 
	 * @param g
	 *
	 */
	@Override
	public void drawMonster(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) this.x1, (int) this.y1, this.size, this.size);
	}

	Runnable bombRunnable = new Runnable() {
		@Override
		public void run() {
			System.out.println("Dropped Bomb before (or was supposed to)");
			Tile tempTile = MonsterThree.this.setCurrentTile();
			MonsterThree.this.bombs.add(new Bomb((tempTile.getX1()), tempTile.getY1(), MonsterThree.this.tiles,
					MonsterThree.this.hero, 1, true, MonsterThree.this));
			System.out.println("Dropped Bomb after (or was supposed to)");
		}
	};

	/**
	 * 
	 * Moves the MonsterThree object.
	 *
	 */
	@Override
	public void monsterMove() {
		if (this.movingUp) {
			this.setY1(getY1() - this.speed);
			this.setY2(getY2() - this.speed);
			if (!this.checkMove()) {
				this.previousTrue = "up";
				this.setY1(getY1() + this.backup);
				this.setY2(getY2() + this.backup);
				setNewDirection();
			}
		} else if (this.movingDown) {
			this.setY1(getY1() + this.speed);
			this.setY2(getY2() + this.speed);
			if (!this.checkMove()) {
				this.previousTrue = "down";
				this.setY1(getY1() - this.backup);
				this.setY2(getY2() - this.backup);
				setNewDirection();
			}
		} else if (this.isMovingRight) {
			this.setX1(getX1() + this.speed);
			this.setX2(getX2() + this.speed);
			if (!this.checkMove()) {
				this.previousTrue = "right";
				this.setX1(getX1() - this.backup);
				this.setX2(getX2() - this.backup);
				setNewDirection();
			}
		} else if (this.isMovingLeft) {
			this.setX1(getX1() - this.speed);
			this.setX2(getX2() - this.speed);
			if (!this.checkMove()) {
				this.previousTrue = "left";
				this.setX1(getX1() + this.backup);
				this.setX2(getX2() + this.backup);
				setNewDirection();
			}
		}
	}

	/**
	 * 
	 * Finds and sets the tiles surrounding MonsterThree.
	 *
	 */
	@Override
	public void setSurroundingTiles() {
		this.currentTile = this.setCurrentTile();
		
		// x coordinate of the center of the tile above the tile containing
		// the bomb
		double ux = this.currentTile.getX1() + 24;
		
		// y coordinate of the center of the tile above the tile containing
		// the bomb
		double uy = this.currentTile.getY1() - 24;

		// x coordinate of the center of the tile to the left the tile
		// containing the bomb
		double lx = this.currentTile.getX1() - 24;
		
		// y coordinate of the center of the tile to the left the tile
		// containing the bomb
		double ly = this.currentTile.getY1() + 24;

		// x coordinate of the center of the tile to the right the tile
		// containing the bomb
		double rx = this.currentTile.getX2() + 24;
		
		// y coordinate of the center of the tile to the right the tile
		// containing the bomb
		double ry = this.currentTile.getY2() - 24;

		// x coordinate of the center of the tile below the tile containing
		// the bomb
		double dx = this.currentTile.getX1() + 24;
		 
		// y coordinate of the center of the tile below the tile containing
		// the bomb
		double dy = this.currentTile.getY2() + 24;

		Tile tileUp = null;
		Tile tileRight = null;
		Tile tileLeft = null;
		Tile tileDown = null;

		// finds the tiles above, below, to the right, and to the left of
		// the tile containing the bomb
		for (Tile tile : this.tiles) {
			if (tile.getX1() <= ux && tile.getX2() >= ux && tile.getY1() <= uy && tile.getY2() >= uy) {
				tileUp = tile;
				this.surroundingTiles.put("up", tileUp);
			} else if (tile.getX1() <= rx && tile.getX2() >= rx && tile.getY1() <= ry && tile.getY2() >= ry) {
				tileRight = tile;
				this.surroundingTiles.put("right", tileRight);
			} else if (tile.getX1() <= lx && tile.getX2() >= lx && tile.getY1() <= ly && tile.getY2() >= ly) {
				tileLeft = tile;
				this.surroundingTiles.put("left", tileLeft);
			} else if (tile.getX1() <= dx && tile.getX2() >= dx && tile.getY1() <= dy && tile.getY2() >= dy) {
				tileDown = tile;
				this.surroundingTiles.put("down", tileDown);
			}
		}
	}

	/**
	 * 
	 * Finds and sets the tile which the MonsterThree object is in.
	 *
	 */
	public Tile setCurrentTile() {
		for (Tile tile : this.tiles) {
			if (this.checkIfInTile(tile)) {
				return tile;
			}
		}
		return null;
	}

	/**
	 * 
	 * Sets a new direction for the MonsterThree object to move in.
	 *
	 */
	public void setNewDirection() {
		this.isMovingLeft = false;
		this.isMovingRight = false;
		this.movingUp = false;
		this.movingDown = false;
		int indexNum = 0;
		ArrayList<String> temp = new ArrayList<>();
		setSurroundingTiles();
		for (String str : this.surroundingTiles.keySet()) {
			if (this.surroundingTiles.get(str).isPassable() || this.surroundingTiles.get(str).getPassableToBoss()) {
				if (str.equals("up")) {
					temp.add("up");
					indexNum++;
				} else if (str.equals("down")) {
					temp.add("down");
					indexNum++;
				} else if (str.equals("right")) {
					temp.add("right");
					indexNum++;
				} else if (str.equals("left")) {
					temp.add("left");
					indexNum++;
				}
			}
		}

		int getIndex = ThreadLocalRandom.current().nextInt(0, indexNum);
		String str = temp.get(getIndex);
		if (str.equals("up")) {
			if (str.equals(this.previousTrue)) {
				this.y1 -= 50;
				this.y2 -= 50;
			}
			this.movingUp = true;
		} else if (str.equals("down")) {
			if (str.equals(this.previousTrue)) {
				this.y1 += 50;
				this.y2 += 50;
			}
			this.movingDown = true;
		} else if (str.equals("right")) {
			if (str.equals(this.previousTrue)) {
				this.x1 += 50;
				this.x2 += 50;
			}
			this.isMovingRight = true;
		} else if (str.equals("left")) {
			if (str.equals(this.previousTrue)) {
				this.x1 -= 50;
				this.x2 -= 50;
			}
			this.isMovingLeft = true;
		}
	}

	/**
	 * 
	 * Gets the number of lives of the MonsterThree object.
	 * 
	 * @return lives
	 *
	 */
	public int getLives() {
		return this.lives;
	}

	/**
	 * 
	 * Removes a life from the MonsterThree object.
	 *
	 */
	public void subLives() {
		this.lives--;
	}

}
