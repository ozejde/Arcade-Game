import java.awt.Color;
import java.awt.Graphics;

/**
 * 
 * Class for MonsterTwo objects, or monsters who move horizontally.
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 * 
 */
public class MonsterTwo extends Monster {
	private boolean isMovingRight;
//	protected double speed = .1;
	protected double speed = 1.5;
	
	/**
	 * 
	 * Constructs a new MonsterTwo object.
	 * 
	 * @param j
	 * 
	 * @param i
	 *
	 */
	public MonsterTwo(int i, int j) {
		super(i, j);
		this.setSize(40);
		this.isMovingRight = true;
	}
	
	/**
	 * 
	 * Draws the MonsterTwo object.
	 * 
	 * @param g
	 *
	 */
	@Override
	public void drawMonster(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int)this.x1, (int)this.y1, this.size, this.size);
	}
	
	/**
	 * 
	 * Moves the MonsterTwo object.
	 *
	 */
	@Override
	public void monsterMove(){
		if (this.isMovingRight) {
			this.setX1(getX1() - this.speed);
			this.setX2(getX2() - this.speed);
			if (!this.checkMove()) {
				this.setX1(getX1() + this.speed);
				this.setX2(getX2() + this.speed);
				this.isMovingRight = false;
			}
		} else {
			this.setX1(getX1() + this.speed);
			this.setX2(getX2() + this.speed);
			if (!this.checkMove()) {
				this.setX1(getX1() - this.speed);
				this.setX2(getX2() - this.speed);
				this.isMovingRight = true;
			}
		}
	}

}
