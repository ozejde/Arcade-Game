import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Hero extends Character implements Runnable{
	private GameKeyListener keyLis;

	public Hero(int x1, int x2, int y1, int y2, Graphics g, BufferedImage tileSheet, int x, int y, GameKeyListener keyLis) {
		super(x1, y1, x2, y2, g, tileSheet, x, y);
		this.keyLis = keyLis;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub.
		
	}
}
