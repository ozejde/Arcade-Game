import java.util.ArrayList;

/**
 * 
 * Abstract class for Character objects
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 */
abstract class Character {
	protected int size = 36;
	private boolean destructible = false;
	private boolean passable = false;
	protected double x1, x2, y1, y2;
	protected ArrayList<Tile> tiles = new ArrayList<>();
	protected double startX1, startX2, startY1, startY2;
	private double offset = 0;

	/**
	 * 
	 * Constructs a Character class
	 * 
	 * @param j
	 * @param i
	 *
	 */
	public Character(int i, int j) {
		super();
		this.x1 = i - this.size / 2;
		this.y1 = j - this.size / 2;
		this.x2 = i + this.size / 2;
		this.y2 = j + this.size / 2;
		this.startX1 = this.x1;
		this.startX2 = this.x2;
		this.startY1 = this.y1;
		this.startY2 = this.y2;
	}

	/**
	 * 
	 * Returns whether Character is destructible or not
	 *
	 * @return destructible field
	 */

	public boolean isDestructible() {
		return this.destructible;
	}

	public void reset() {
		setX1(this.startX1);
		setX2(this.startX2);
		setY1(this.startY1);
		setY2(this.startY2);
	}

	/**
	 * 
	 * Sets destructible field to given boolean
	 *
	 * @param destructible
	 *            Boolean to change destructible to
	 */
	public void setdestructible(boolean destructible) {
		this.destructible = destructible;
	}

	/**
	 * 
	 * Returns whether Character is passable or not
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

	/**
	 * 
	 * Checks if moving is possible
	 *
	 * @return Boolean of if move is possible
	 */
	protected boolean checkMove() {
		for (Tile tile : this.tiles) {
			if (this.x1 + this.offset >= tile.getX1() && this.x1 + this.offset <= tile.getX2()
					&& (this.y1 + this.offset >= tile.getY1() && this.y1 + this.offset <= tile.getY2())) {
				if (!tile.isPassable()) {
					return false;
				}
			} else if (this.x1 + this.offset >= tile.getX1() && this.x1 + this.offset <= tile.getX2()
					&& (this.y1 + this.size - this.offset >= tile.getY1()
							&& this.y1 + this.size - this.offset <= tile.getY2())) {
				if (!tile.isPassable()) {
					return false;
				}
			} else if (this.x2 - this.offset >= tile.getX1() && this.x2 - this.offset <= tile.getX2()
					&& this.y2 - this.offset >= tile.getY1() && this.y2 - this.offset <= tile.getY2()) {
				if (!tile.isPassable()) {
					return false;
				}
			} else if (this.x2 - this.offset >= tile.getX1() && this.x2 - this.offset <= tile.getX2()
					&& this.y2 - this.offset - this.size >= tile.getY1()
					&& this.y2 - this.size - this.offset <= tile.getY2()) {
				if (!tile.isPassable()) {
					return false;
				}
			}
		}
		return true;
	}
	/**
	 * 
	 * Checks if a character is currently in a passed in tile.
	 *
	 * @param tile
	 * @return true or false
	 */
	public boolean checkIfInTile(Tile tile) {
		if (this.x1 >= tile.getX1() && this.x1 <= tile.getX2()
				&& (this.y1 >= tile.getY1() && this.y1 <= tile.getY2())) {
			return true;
		} else if (this.x1 >= tile.getX1() && this.x1 <= tile.getX2()
				&& (this.y1 + this.size >= tile.getY1() && this.y1 + this.size <= tile.getY2())) {
			return true;
		} else if (this.x2 >= tile.getX1() && this.x2 <= tile.getX2() && this.y2 >= tile.getY1()
				&& this.y2 <= tile.getY2()) {
			return true;
		} else if (this.x2 >= tile.getX1() && this.x2 <= tile.getX2() && this.y2 - this.size >= tile.getY1()
				&& this.y2 - this.size <= tile.getY2()) {
			return true;
		}

		return false;
	}

	/**
	 * 
	 * Gets list of Tiles
	 *
	 * @param tiles
	 *            Arraylist of Tiles in the game
	 */
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}

	/**
	 * 
	 * Outputs x-coordinate at upper left-hand corner
	 *
	 * @return x-coordinate at upper left-hand corner
	 */
	public double getX1() {
		return this.x1;
	}

	/**
	 * 
	 * Sets x-coordinate at upper left-hand corner
	 *
	 * @param startX12
	 *            new x-coordinate at upper left-hand corner
	 */
	public void setX1(double startX12) {
		this.x1 = startX12;
	}

	/**
	 * 
	 * Outputs x-coordinate at lower right-hand corner
	 *
	 * @return x-coordinate at lower right-hand corner
	 */
	public double getX2() {
		return this.x2;
	}

	/**
	 * 
	 * sets x-coordinate at lower right-hand corner
	 *
	 * @param startX22
	 *            new x-coordinate at lower right-hand corner
	 */
	public void setX2(double startX22) {
		this.x2 = startX22;
	}

	/**
	 * 
	 * Outputs y-coordinate at upper left-hand corner
	 *
	 * @return y-coordinate at upper left-hand corner
	 */
	public double getY1() {
		return this.y1;
	}

	/**
	 * 
	 * Sets y-coordinate at upper left-hand corner
	 *
	 * @param x1
	 *            new y-coordinate at upper left-hand corner
	 */
	public void setY1(double startY12) {
		this.y1 = startY12;
	}

	/**
	 * 
	 * Outputs y-coordinate at lower right-hand corner
	 *
	 * @return y-coordinate at lower right-hand corner
	 */
	public double getY2() {
		return this.y2;
	}

	/**
	 * 
	 * sets y-coordinate at lower right-hand corner
	 *
	 * @param x2
	 *            new y-coordinate at lower right-hand corner
	 */
	public void setY2(double startY22) {
		this.y2 = startY22;
	}
	/**
	 * 
	 * Sets the size of the Character
	 *
	 * @param size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	/**
	 * 
	 * Sets the offSet used in checkMove() method
	 *
	 * @param d
	 */
	public void setOffset(double d) {
		this.offset = d;
	}

}
