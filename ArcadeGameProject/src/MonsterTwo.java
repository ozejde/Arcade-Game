import java.awt.Color;
import java.awt.Graphics;

public class MonsterTwo extends Monster {
	private boolean isMovingRight;

	public MonsterTwo(int i, int j) {
		super(i, j);
		this.setSize(40);
		this.isMovingRight = true;
	}
	
	@Override
	public void drawMonster(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int)this.x1, (int)this.y1, this.size, this.size);
	}
	
	@Override
	public void monsterMove(){
		if (this.isMovingRight) {
			this.setX1(getX1() - .1);
			this.setX2(getX2() - .1);
			if (!this.checkMove()) {
				this.setX1(getX1() + .1);
				this.setX2(getX2() + .1);
				this.isMovingRight = false;
			}
		} else {
			this.setX1(getX1() + .1);
			this.setX2(getX2() + .1);
			if (!this.checkMove()) {
				this.setX1(getX1() - .1);
				this.setX2(getX2() - .1);
				this.isMovingRight = true;
			}
		}
	}

}
