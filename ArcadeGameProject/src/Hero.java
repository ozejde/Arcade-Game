import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * 
 * Main Hero of the game
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 */
public class Hero extends Character {
	private int x1, x2, y1, y2;
	private ArrayList<Bomb> bombs;
	private ArrayList<Tile> tiles;

	/**
	 * 
	 * Creates a Hero object
	 *
	 * @param i
	 *            x-coordinate of middle of the top edge of Hero
	 * @param j
	 *            y-coordinate of middle of the top edge of Hero
	 * 
	 */
	public Hero(int i, int j) {
		super();
		this.x1 = i - 18;
		this.y1 = j - 18;
		this.x2 = i + 18;
		this.y2 = j + 18;
		this.bombs = new ArrayList<>();
	}

	/**
	 * 
	 * Draws the Hero and Bombs dropped
	 *
	 * @param g
	 *            Graphics object used to draw Hero and Bomb
	 */
	public void drawCharacter(Graphics g) {
		if (this.bombs != null) {
			for (Bomb bomb : this.bombs) {
				bomb.drawCharacter(g);
			}
		}
		g.setColor(Color.CYAN);
		g.fillRect(this.x1, this.y1, 36, 36);
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
	 * Sets x-coordinate at upper left-hand corner
	 *
	 * @param x1
	 *            new x-coordinate at upper left-hand corner
	 */
	public void setX1(int x1) {
		this.x1 = x1;
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
	 * sets x-coordinate at lower right-hand corner
	 *
	 * @param x2
	 *            new x-coordinate at lower right-hand corner
	 */
	public void setX2(int x2) {
		this.x2 = x2;
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
	 * Sets y-coordinate at upper left-hand corner
	 *
	 * @param x1
	 *            new y-coordinate at upper left-hand corner
	 */
	public void setY1(int y1) {
		this.y1 = y1;
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
	 * sets y-coordinate at lower right-hand corner
	 *
	 * @param x2
	 *            new y-coordinate at lower right-hand corner
	 */
	public void setY2(int y2) {
		this.y2 = y2;
	}

	/**
	 * 
	 * Moves Hero in desired direction
	 *
	 * @param direction
	 * String of direction Hero will move in
	 */
	public void move(String direction) {

		switch (direction) {
		case "up":
			this.setY1(getY1() - 3);
			this.setY2(getY2() - 3);
			System.out.println("Moved up");
			if (!this.checkMove()) {
				this.setY1(getY1() + 3);
				this.setY2(getY2() + 3);
				System.out.println("false");
			}
			break;
		case "down":
			this.setY1(getY1() + 3);
			this.setY2(getY2() + 3);
			System.out.println("Moved down");
			if (!this.checkMove()) {
				this.setY1(getY1() - 3);
				this.setY2(getY2() - 3);
				System.out.println("false");
			}
			break;
		case "left":
			this.setX1(getX1() - 3);
			this.setX2(getX2() - 3);
			System.out.println("Moved left");
			if (!this.checkMove()) {
				this.setX1(getX1() + 3);
				this.setX2(getX2() + 3);
				System.out.println("false");
			}
			break;
		case "right":
			this.setX1(getX1() + 3);
			this.setX2(getX2() + 3);
			System.out.println("Moved right");
			if (!this.checkMove()) {
				this.setX1(getX1() - 3);
				this.setX2(getX2() - 3);
				System.out.println("false");
			}
			break;
		}
	}

	/**
	 * 
	 *Checks if moving is possible
	 *
	 * @return
	 * Boolean of if move is possible
	 */
	private boolean checkMove() {
		for (Tile tile : this.tiles) {

			if (this.x1 >= tile.getX1() && this.x1 <= tile.getX2()
					&& (this.y1 >= tile.getY1() && this.y1 <= tile.getY2())) {
				if (!tile.isPassable()) {
					System.out.println("first if " + tile.isPassable());
					return false;
				}
			} else if (this.x1 >= tile.getX1() && this.x1 <= tile.getX2()
					&& (this.y1 + 36 >= tile.getY1() && this.y1 + 36 <= tile.getY2())) {
				if (!tile.isPassable()) {
					System.out.println("second if " + tile.isPassable());
					return false;
				}
			} else if (this.x2 >= tile.getX1() && this.x2 <= tile.getX2() && this.y2 >= tile.getY1()
					&& this.y2 <= tile.getY2()) {
				if (!tile.isPassable()) {
					System.out.println("third if " + tile.isPassable());
					return false;
				}
			} else if (this.x2 >= tile.getX1() && this.x2 <= tile.getX2() && this.y2 - 36 >= tile.getY1()
					&& this.y2 - 36 <= tile.getY2()) {
				if (!tile.isPassable()) {
					System.out.println("fourth if " + tile.isPassable());
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 
	 *Drops bomb at center of Hero's current location
	 *
	 */
	public void dropBomb() {
		this.bombs.add(new Bomb((this.x1 + 12), this.y1 + 12));

	}

	/**
	 * 
	 * Gets list of Tiles
	 *
	 * @param tiles
	 * Arraylist of Tiles in the game 
	 */
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}

}
