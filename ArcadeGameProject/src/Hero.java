import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Hero extends Character {
	private int x1, x2, y1, y2;
	private ArrayList<Bomb> bombs;
	

	public Hero(int i, int j) {
		super();
		this.x1 = i - 24;
		this.y1 = j - 24;
		this.x2 = i + 24;
		this.y2 = j + 24;
		this.bombs = new ArrayList<>();
	}

	public void drawCharacter(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(this.x1, this.y1, 48, 48);
		if (this.bombs!=null){
			for(Bomb bomb:bombs){
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
			break;
		case "down":
			this.setY1(getY1() + 1);
			break;
		case "left":
			this.setX1(getX1() - 1);
			break;
		case "right":
			this.setX1(getX1() + 1);
			break;
	}
}

	@Override
	public void run() {
		// TODO Auto-generated method stub.
		
	}

	public void dropBomb() {
		this.bombs.add(new Bomb((this.x1+12),this.y1+12));
	}
}
