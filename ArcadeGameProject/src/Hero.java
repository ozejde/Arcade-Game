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
	protected ArrayList<Monster> monsters = new ArrayList<>();
;
	protected double range;
	protected int bombCount;
	protected boolean isDetonatable;
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
		super(i, j);
		this.bombs = new ArrayList<>();
		this.setSize(this.heroSize);
		this.lives = 3;
		this.setOffset(2);
		this.bombCount = 1;
		this.range = 1;
		this.isDetonatable = false;
	}

	public ArrayList<Monster> getMonsters() {
		return this.monsters;
	}

	public void setMonsters(ArrayList<Monster> monsters) {
		this.monsters = monsters;
	}

	public boolean isDetonatable() {
		return this.isDetonatable;
	}

	public void setDetonatable(boolean isDetonatable) {
		this.isDetonatable = isDetonatable;
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
			this.setY1(getY1() - 4);
			this.setY2(getY2() - 4);
			if (!this.checkMove()) {
				this.setY1(getY1() + 4);
				this.setY2(getY2() + 4);
			}
			break;
		case "down":
			this.setY1(getY1() + 4);
			this.setY2(getY2() + 4);
			if (!this.checkMove()) {
				this.setY1(getY1() - 4);
				this.setY2(getY2() - 4);
			}
			break;
		case "left":
			this.setX1(getX1() - 4);
			this.setX2(getX2() - 4);
			if (!this.checkMove()) {
				this.setX1(getX1() + 4);
				this.setX2(getX2() + 4);
			}
			break;
		case "right":
			this.setX1(getX1() + 4);
			this.setX2(getX2() + 4);
			if (!this.checkMove()) {
				this.setX1(getX1() - 4);
				this.setX2(getX2() - 4);
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
		if (this.bombs.size() < this.bombCount) {
			if (this.isDetonatable) {
				this.bombs.add(new Bomb((tempTile.getX1()), tempTile.getY1(), this.tiles, this, this.monsters,
						this.range, true));
			} else {
				this.bombs.add(
						new Bomb((tempTile.getX1()), tempTile.getY1(), this.tiles, this, this.monsters, this.range));
			}
		}

	}

	public void blowUpBomb(int i) {
		if (this.bombs.get(i).isDetonatable) {
			this.bombs.get(i).bombTile.setPassable(true);
			this.bombs.get(i).explode();
		}
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
	@Override
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

	public void addRange() {
		this.range++;
	}

	public void addBombCount() {
		this.bombCount++;
	}
	@Override
	public void reset() {
		setX1(this.startX1);
		setX2(this.startX2);
		setY1(this.startY1);
		setY2(this.startY2);
		this.bombCount = 1;
		this.range = 1;
		this.isDetonatable = false;
	}

}
