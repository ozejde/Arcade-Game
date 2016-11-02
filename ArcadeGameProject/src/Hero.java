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
	protected ArrayList<Bomb> bombs;
	private int heroSize = 34;
	private int lives;
	protected ArrayList<Monster> monsters;

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
	public Hero(int i, int j, ArrayList<Monster> m) {
		super(i, j);
		this.bombs = new ArrayList<>();
		this.setSize(this.heroSize);
		this.lives = 3;
		this.monsters = m;
		this.setOffset(2);
	}

	/**
	 * 
	 * Draws the Hero and Bombs dropped
	 *
	 * @param g
	 *            Graphics object used to draw Hero and Bomb
	 */
	public void drawCharacter(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect((int) this.x1, (int) this.y1, this.size, this.size);
	}

	/**
	 * 
	 * Moves Hero in desired direction
	 *
	 * @param direction
	 *            String of direction Hero will move in
	 */
	@SuppressWarnings("incomplete-switch")
	public void move(String direction) {

		switch (direction) {
		case "up":
			this.setY1(getY1() - 3);
			this.setY2(getY2() - 3);
			if (!this.checkMove()) {
				this.setY1(getY1() + 3);
				this.setY2(getY2() + 3);
			}
			break;
		case "down":
			this.setY1(getY1() + 3);
			this.setY2(getY2() + 3);
			if (!this.checkMove()) {
				this.setY1(getY1() - 3);
				this.setY2(getY2() - 3);
			}
			break;
		case "left":
			this.setX1(getX1() - 3);
			this.setX2(getX2() - 3);
			if (!this.checkMove()) {
				this.setX1(getX1() + 3);
				this.setX2(getX2() + 3);
			}
			break;
		case "right":
			this.setX1(getX1() + 3);
			this.setX2(getX2() + 3);
			if (!this.checkMove()) {
				this.setX1(getX1() - 3);
				this.setX2(getX2() - 3);
			}
			break;
		}
	}

	/**
	 *
	 * Drops bomb at center of Hero's current location
	 *
	 */
	@SuppressWarnings("null")
	public void dropBomb() {
		Tile tempTile = null;
		for (Tile tile : this.tiles) {
			if (this.checkIfInTile(tile)) {
				tempTile = tile;
				continue;
			}
		}
		this.bombs.add(new Bomb((tempTile.getX1()), tempTile.getY1(), this.tiles, this, this.monsters));
	}

	public void addLife() {
		this.lives++;
	}

	public void subtractLife() {
		this.lives--;
	}

	public int getLives() {
		return this.lives;
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

	protected boolean checkMonster() {
		for (Monster m : this.monsters) {
			if (this.x1 >= m.getX1() && this.x1 <= m.getX2() && (this.y1 >= m.getY1() && this.y1 <= m.getY2())) {
				return true;
			} else if (this.x1 >= m.getX1() && this.x1 <= m.getX2()
					&& (this.y1 + this.size >= m.getY1() && this.y1 + this.size <= m.getY2())) {
				return true;
			} else if (this.x2 >= m.getX1() && this.x2 <= m.getX2() && this.y2 >= m.getY1() && this.y2 <= m.getY2()) {
				return true;
			} else if (this.x2 >= m.getX1() && this.x2 <= m.getX2() && this.y2 - this.size >= m.getY1()
					&& this.y2 - this.size <= m.getY2()) {
				return true;
			}
		}
		return false;
	}
}
