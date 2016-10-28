import java.awt.image.BufferedImage;
/**
 * 
 * Green base tile for Hero and monsters to walk on 
 *
 * @author ejdeoz, youngqom, petersmt.
 *         Created Oct 27, 2016.
 */
public class GroundTile extends Tile {

	/**
	 * 
	 * Creates a GroundTile
	 *
	 *@param x1
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
	public GroundTile(int x1, int y1, int x2, int y2, BufferedImage tileSheet, int x, int y) {
		super(x1, y1, x2, y2, tileSheet, x, y);
		super.setDistructable(false);
		super.setPassable(true);
	}

}
