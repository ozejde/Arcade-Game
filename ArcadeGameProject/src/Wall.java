import java.awt.image.BufferedImage;
/**
 * 
 * Solid Tile unable to pass through by moving objects, and cannot be destroyed by Bomb 
 *
 * @author ejdeoz.
 *         Created Oct 27, 2016.
 */
public class Wall extends Tile {

	/**
	 * 
	 * Creates a Wall object
	 *
	 * @param x1
	 *            x-coordinate at upper left-hand corner
	 * @param y1
	 *            y-coordinate at upper left-hand corner
	 * @param x2
	 *            x-coordinate at lower right-hand corner
	 * @param y2
	 *            y-coordinate at lower right-hand corner
	 * @param tileSheet
	 *            File being read from
	 * @param x
	 *            x-coordinate of index of GroundTile image
	 * @param y
	 *            y-coordinate of index of GroundTile image
	 */
	public Wall(int x1, int y1, int x2, int y2, BufferedImage tileSheet, int x, int y) {
		super(x1, y1, x2, y2, tileSheet, x, y);
		super.setDistructable(false);
		super.setPassable(false);
	}

}
