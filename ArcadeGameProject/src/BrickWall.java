import java.awt.image.BufferedImage;

/**
 * 
 * Wall object that can be removed by Bomb
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 */
public class BrickWall extends Tile {

	/**
	 * 
	 * Creates BrickWall object
	 *
	 * @param x1
	 *            x-coordinate at 
	 * @param y1
	 *            y-coordinate at
	 * @param x2
	 *            x-coordinate at 
	 * @param y2
	 *            y-coordinate at
	 * @param tileSheet
	 *            File being read from
	 * @param x
	 * 
	 * @param y
	 * 
	 */

	public BrickWall(int x1, int y1, int x2, int y2, BufferedImage tileSheet, int x, int y) {
		super(x1, y1, x2, y2, tileSheet, x, y);
		super.setDistructable(true);
		super.setPassable(false);
	}

}
