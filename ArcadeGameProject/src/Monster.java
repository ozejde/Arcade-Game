import java.awt.Graphics;

public abstract class Monster extends Character {
	protected boolean movingUp;

	public Monster(int i, int j) {
		super(i, j);
		this.movingUp = true;
		this.setdestructible(true);
	}

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

	public void drawMonster(Graphics g) {
		// TODO Auto-generated method stub.

	}

}
