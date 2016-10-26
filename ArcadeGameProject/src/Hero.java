import java.awt.Color;
import java.awt.Graphics;

public class Hero extends Character{
	private GameKeyListener keyLis;
	private int x1, x2, y1, y2;


	public Hero(int i, int j, GameKeyListener keyLis) {
		super();
		this.x1 = i - 24;
		this.y1 = j - 24;
		this.x2 = i + 24;
		this.y2 = j + 24;
		this.keyLis = keyLis;
	}
	
	public void drawHero(Graphics g){
		g.setColor(Color.CYAN);
		g.fillRect(this.x1, this.y1, 48, 48);
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub.
		
	}

	public void move() {
		System.out.println("Hero is moving now!! YAY!!");
		if(this.keyLis.up){
			this.y1 -= 5;
			this.y2 -= 5;
		}
		if(this.keyLis.down){
			this.y1 += 5;
			this.y2 += 5;
		}
		
		if(this.keyLis.left){
			this.x1 -= 5;
			this.x2 -= 5;
		}
		
		if(this.keyLis.right){
			this.x1 += 5;
			this.x2 += 5;
		}
		
		
	}
}
