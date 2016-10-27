import java.awt.image.BufferedImage;

public class BrickWall extends Tile {

	public BrickWall(int x1, int y1, int x2, int y2, BufferedImage tileSheet, int x, int y) {
		super(x1, y1, x2, y2, tileSheet, x, y);
		super.setDistructable(true);
		super.setPassable(false);
	}

}
