import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

	private boolean[] keys = new boolean[120];
	public boolean up = false;
	public boolean down = false;
	public  boolean left = false;
	public boolean right = false;
	private Hero hero;

	public GameKeyListener(Hero hero) {
		this.hero = hero;
	}

	public void update() {
		this.up = this.keys[KeyEvent.VK_UP];
		this.down = this.keys[KeyEvent.VK_DOWN];
		this.left = this.keys[KeyEvent.VK_LEFT];
		this.right = this.keys[KeyEvent.VK_RIGHT];
//		this.space = this.keys[KeyEvent.VK_SPACE];
//		this.enter = this.keys[KeyEvent.VK_ENTER];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		case KeyEvent.VK_UP:
			this.hero.move("up");
			break;
		case KeyEvent.VK_DOWN:
			this.hero.move("down");
			break;
		case KeyEvent.VK_LEFT:
			this.hero.move("left");
			break;
		case KeyEvent.VK_RIGHT:
			this.hero.move("right");
			break;
		case KeyEvent.VK_B:
			System.out.println("B pressed");
			this.hero.dropBomb();
		}
		update();

	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false;
		update();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
