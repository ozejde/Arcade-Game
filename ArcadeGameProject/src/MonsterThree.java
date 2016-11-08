import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class MonsterThree extends Monster {
	private HashMap<String, Tile> surroundingTiles;
	private Tile currentTile;
	private boolean movingUp;
	private boolean movingDown;
	private boolean isMovingRight;
	private boolean isMovingLeft;
	private int backup = 4;

	public MonsterThree(int i, int j) {
		super(i, j);
		this.setSize(34);
		this.setOffset(3.75);
		this.surroundingTiles = new HashMap<>();
		this.isMovingLeft = false;
		this.isMovingRight = false;
		this.movingUp = false;
		this.movingDown = true;
	}

	@Override
	public void drawMonster(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) this.x1, (int) this.y1, this.size, this.size);
	}

	@Override
	public void monsterMove() {
		if (this.movingUp) {
			this.setY1(getY1() - .1);
			this.setY2(getY2() - .1);
			if (!this.checkMove()) {
				System.out.println(1);
				this.setY1(getY1() + this.backup);
				this.setY2(getY2() + this.backup);
				setNewDirection();
			}
		} else if (this.movingDown) {
			this.setY1(getY1() + .1);
			this.setY2(getY2() + .1);
			if (!this.checkMove()) {
				System.out.println(2);
				this.setY1(getY1() - this.backup);
				this.setY2(getY2() - this.backup);
				setNewDirection();
			}
		} else if (this.isMovingRight) {
			this.setX1(getX1() + .1);
			this.setX2(getX2() + .1);
			if (!this.checkMove()) {
				System.out.println(3);
				this.setX1(getX1() - this.backup);
				this.setX2(getX2() - this.backup);
				setNewDirection();
			}
		} else if (this.isMovingLeft) {
			this.setX1(getX1() - .1);
			this.setX2(getX2() - .1);
			if (!this.checkMove()) {
				System.out.println(4);
				this.setX1(getX1() + this.backup);
				this.setX2(getX2() + this.backup);
				setNewDirection();
			}
		}
	}

	public void setSurroundingTiles() {
		this.currentTile = this.setCurrentTile();
		// x coordinate of the center of the tile above the tile containing
		// the
		// bomb
		double ux = this.currentTile.getX1() + 24;
		// y coordinate of the center of the tile above the tile containing
		// the
		// bomb
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
		// the
		// bomb
		double dx = this.currentTile.getX1() + 24;
		// y coordinate of the center of the tile below the tile containing
		// the
		// bomb
		double dy = this.currentTile.getY2() + 24;

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

	public Tile setCurrentTile() {
		for (Tile tile : this.tiles) {
			if (this.checkIfInTile(tile)) {
				return tile;
			}
		}
		return null;
	}

	public void setNewDirection() {
		this.isMovingLeft = false;
		this.isMovingRight = false;
		this.movingUp = false;
		this.movingDown = false;
		int indexNum=0;
		ArrayList<String> temp = new ArrayList<>();
		setSurroundingTiles();
		for (String str : this.surroundingTiles.keySet()) {
			if (this.surroundingTiles.get(str).isPassable()) {
				if (str.equals("up")) {
					temp.add("up");
					indexNum++;
				} 
				else if (str.equals("down")) {
					temp.add("down");
					indexNum++;
				} 
				else if (str.equals("right")) {
					temp.add("right");
					indexNum++;
				} 
				else if (str.equals("left")) {
					temp.add("left");
					indexNum++;
				}
			}
		}
		int getIndex = ThreadLocalRandom.current().nextInt(0, indexNum);
		String str = temp.get(getIndex);
		if (str.equals("up")) {
			this.movingUp = true;
		} 
		else if (str.equals("down")) {
			this.movingDown = true;
		} 
		else if (str.equals("right")) {
			this.isMovingRight = true;
		} 
		else if (str.equals("left")) {
			this.isMovingLeft = true;
		}
	}
}
