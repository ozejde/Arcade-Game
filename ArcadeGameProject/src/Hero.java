import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Hero extends Character {
	private int x1, x2, y1, y2;
	private ArrayList<Bomb> bombs;
	private ArrayList<Tile> tiles;

	public Hero(int i, int j) {
		super();
		this.x1 = i - 18;
		this.y1 = j - 18;
		this.x2 = i + 18;
		this.y2 = j + 18;
		this.bombs = new ArrayList<>();
	}

	public void drawCharacter(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(this.x1, this.y1, 36, 36);
		if (this.bombs != null) {
			for (Bomb bomb : this.bombs) {
				bomb.drawCharacter(g);
			}
		}
	}

	public int getX1() {
		return this.x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getX2() {
		return this.x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY1() {
		return this.y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getY2() {
		return this.y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public void move(String direction) {

		switch (direction) {
		case "up":
			this.setY1(getY1() - 1);
			this.setY2(getY2() - 1);
			System.out.println("Moved up");
			if (!this.checkMove()) {
				this.setY1(getY1() + 1);
				this.setY2(getY2() + 1);
				System.out.println("false");
			}
			break;
		case "down":
			this.setY1(getY1() + 1);
			this.setY2(getY2() + 1);
			System.out.println("Moved down");
			if (!this.checkMove()) {
				this.setY1(getY1() - 1);
				this.setY2(getY2() - 1);
				System.out.println("false");
			}
			break;
		case "left":
			this.setX1(getX1() - 1);
			this.setX2(getX2() - 1);
			System.out.println("Moved left");
			if (!this.checkMove()) {
				this.setX1(getX1() + 1);
				this.setX2(getX2() + 1);
				System.out.println("false");
			}
			break;
		case "right":
			this.setX1(getX1() + 1);
			this.setX2(getX2() + 1);
			System.out.println("Moved right");
			if (!this.checkMove()) {
				this.setX1(getX1() - 1);
				this.setX2(getX2() - 1);
				System.out.println("false");
			}
			break;
		}
	}

	private boolean checkMove() {
		for (Tile tile : this.tiles) {

			if (this.x1 >= tile.getX1() && this.x1 <= tile.getX2()
					&& (this.y1 >= tile.getY1() && this.y1 <= tile.getY2())) {
				if (!tile.isPassable()) {
					System.out.println("first if " + tile.isPassable());
					return false;
				}
			}else if (this.x1 >= tile.getX1() && this.x1 <= tile.getX2()
					&& (this.y1 + 36 >= tile.getY1() && this.y1 + 36 <= tile.getY2())) {
				if (!tile.isPassable()) {
					System.out.println("second if " + tile.isPassable());
					return false;
				}
			}else if (this.x2 >= tile.getX1() && this.x2 <= tile.getX2() && this.y2 >= tile.getY1()
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

	public void dropBomb() {
		this.bombs.add(new Bomb((this.x1+12),this.y1+12));

	}

	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}

}
