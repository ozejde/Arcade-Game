import java.awt.Color;
import java.awt.Graphics;

public class Hero extends Character {
	private int x1, x2, y1, y2;

	public Hero(int i, int j) {
		super();
		this.x1 = i - 24;
		this.y1 = j - 24;
		this.x2 = i + 24;
		this.y2 = j + 24;
	}

	public void drawCharacter(Graphics g) {
		System.out.println("We're printing Hero");
		g.setColor(Color.CYAN);
		g.fillRect(this.x1, this.y1, 48, 48);
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
		System.out.println("Hero is moving now!! YAY!!");

		switch (direction) {
		case "up":
			this.setY1(getY1() + 10);
			System.out.println("Y1 is " + this.getY1());
			break;
		case "down":
			this.setY1(getY1() - 10);
			System.out.println("Y1 is " + this.getY1());
			break;
		case "left":
			this.setX1(getX1() - 10);
			System.out.println("X1 is " + this.getX1());
			break;
		case "right":
			this.setX1(getX1() + 10);
			System.out.println("X1 is " + this.getX1());
			break;
	}
}

	@Override
	public void run() {
		// TODO Auto-generated method stub.
		
	}
}
