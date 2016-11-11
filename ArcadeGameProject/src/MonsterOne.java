import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * Class for MonsterOne objects, or monsters who move vertically.
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 * 
 */
public class MonsterOne extends Monster {
	protected boolean movingUp;

	/**
	 * 
	 * Constructs a new MonsterOne object.
	 * 
	 * @param j
	 * 
	 * @param i
	 *
	 */
	public MonsterOne(int i, int j) {
		super(i, j);
		this.setSize(40);
		this.setOffset(0);
		this.movingUp = true;
	}
	
	/**
	 * 
	 * Draws the MonsterOne object.
	 * 
	 * @param g
	 *
	 */
	@Override
	public void drawMonster(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)this.x1, (int)this.y1, this.size, this.size);
	}

	/**
	 * 
	 * Moves the MonsterOne object.
	 *
	 */
	@Override
	public void monsterMove() {
		if (this.movingUp) {
			this.setY1(getY1() - .1);
			this.setY2(getY2() - .1);
			if (!this.checkMove()) {
				this.setY1(getY1() + .1);
				this.setY2(getY2() + .1);
				this.movingUp = false;
			}
		} else {
			this.setY1(getY1() + .1);
			this.setY2(getY2() + .1);
			if (!this.checkMove()) {
				this.setY1(getY1() - .1);
				this.setY2(getY2() - .1);
				this.movingUp = true;
			}
		}
	}
}
