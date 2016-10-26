import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameKeyListener implements KeyListener {

	private boolean[] keys = new boolean[120];
	public boolean up, down, left, right, space, enter;
	
	public void update() {
		this.up = this.keys[KeyEvent.VK_UP] || this.keys[KeyEvent.VK_W];
		this.down = this.keys[KeyEvent.VK_DOWN] || this.keys[KeyEvent.VK_S];
		this.left = this.keys[KeyEvent.VK_LEFT] || this.keys[KeyEvent.VK_A];
		this.right = this.keys[KeyEvent.VK_RIGHT] || this.keys[KeyEvent.VK_D];
		this.space = this.keys[KeyEvent.VK_SPACE];
		this.enter = this.keys[KeyEvent.VK_ENTER];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.keys[e.getKeyCode()] = true;
		System.out.println(this.keys[e.getKeyCode()]);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false;
		System.out.println(this.keys[e.getKeyCode()]);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
