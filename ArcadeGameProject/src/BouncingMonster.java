import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

public class BouncingMonster extends Monster {
	private double velocityX;
	private double velocityY;
	private double max = .1;
	private double min = -.1;

	public BouncingMonster(int i, int j) {
		super(i, j);
		this.setSize(38);
		this.setOffset(0);
		this.velocityX = ThreadLocalRandom.current().nextDouble(this.min, this.max + .1);
		this.velocityY = ThreadLocalRandom.current().nextDouble(this.min, this.max + .1);

	}

	@Override
	public void drawMonster(Graphics g) {
		g.setColor(Color.PINK);
		g.fillRect((int) this.x1, (int) this.y1, this.size, this.size);
	}

	@Override
	public void monsterMove() {
		this.setY1(getY1() + this.velocityY);
		this.setY2(getY2() + this.velocityY);
		this.setX1(getX1() + this.velocityX);
		this.setX2(getX2() + this.velocityX);
		if (!this.checkMove()) {
			this.setY1(getY1() - this.velocityY);
			this.setY2(getY2() - this.velocityY);
			this.setX1(getX1() - this.velocityX);
			this.setX2(getX2() - this.velocityX);
			if (this.getX1() < 50 || this.getX2() > 760) {
				this.velocityX = -this.velocityX;
			} else{
				this.velocityY = -this.velocityY;
			}
		}
	}
}
