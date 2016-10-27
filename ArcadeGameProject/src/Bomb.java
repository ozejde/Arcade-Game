import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class Bomb{
	private int x;
	private int y;
	
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void drawCharacter(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		Graphics2D gCast  = (Graphics2D) graphics;
		Ellipse2D.Double bomb = new Ellipse2D.Double(this.x, this.y, 20, 20);
		gCast.fill(bomb);
	}

}
