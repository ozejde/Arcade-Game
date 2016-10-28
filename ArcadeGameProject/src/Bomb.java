import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

/**
 * 
 * A black, circular graphic that can destroy BrickWall
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 */
public class Bomb {
	private int x;
	private int y;

	/**
	 * 
	 * Sets the coordinates of Bomb
	 *
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 */
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * 
	 * Draws image of Bomb
	 * 
	 * @param graphics
	 * Graphics that Bomb is painted onto
	 * 
	 */
	public void drawCharacter(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		Graphics2D gCast = (Graphics2D) graphics;
		Ellipse2D.Double bomb = new Ellipse2D.Double(this.x, this.y, 20, 20);
		gCast.fill(bomb);
	}

}
