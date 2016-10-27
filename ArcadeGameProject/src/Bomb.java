import java.awt.Color;
import java.awt.Graphics;

public class Bomb{
	private int x;
	private int y;
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void drawCharacter(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		graphics.fillRect(this.x, this.y, 30, 30);
	}

}
