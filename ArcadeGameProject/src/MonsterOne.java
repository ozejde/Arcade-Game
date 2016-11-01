import java.awt.Color;
import java.awt.Graphics;

public class MonsterOne extends Monster {

	public MonsterOne(int i, int j) {
		super(i, j);
		this.setSize(40);
		this.setOffset(0);
	}
	@Override
	public void drawMonster(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int)this.x1, (int)this.y1, this.size, this.size);
	}

}
