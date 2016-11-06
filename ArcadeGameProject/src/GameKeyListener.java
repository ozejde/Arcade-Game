import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * 
 * KeyListener subclass that handles keyboard input
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 */
public class GameKeyListener implements KeyListener {

	private boolean[] keys = new boolean[120];
	public boolean up = false;
	public boolean down = false;
	public boolean left = false;
	public boolean right = false;
	public boolean u = false;
	public boolean d = false;
	public boolean x = false;
	public boolean z = false;
	private Hero hero;
	private DrawPanel drawPanel;

	/**
	 * 
	 * Takes in parameters and sets them as fields
	 *
	 * @param hero
	 *            Hero of the game
	 * @param drawPanel
	 *            DrawPanel of the game
	 */
	public GameKeyListener(Hero hero, DrawPanel drawPanel) {
		this.hero = hero;
		this.drawPanel = drawPanel;
	}

	/**
	 * 
	 * Outputs whether U is pressed or not
	 *
	 * @return Boolean if U is pressed
	 */
	public boolean getU() {
		return u;
	}

	/**
	 * 
	 * Outputs whether D is pressed or not
	 *
	 * @return Boolean if D is pressed
	 */
	public boolean getD() {
		return d;
	}

	/**
	 * 
	 * updates the Boolean pressed statuses of all keys
	 *
	 */
	public void update() {
		this.up = this.keys[KeyEvent.VK_UP];
		this.down = this.keys[KeyEvent.VK_DOWN];
		this.left = this.keys[KeyEvent.VK_LEFT];
		this.right = this.keys[KeyEvent.VK_RIGHT];
//		this.u = this.keys[KeyEvent.VK_U];
//		this.d = this.keys[KeyEvent.VK_D];
		this.z = this.keys[KeyEvent.VK_Z];
	}

	/**
	 * Handles the event in which a certain key is pressed
	 */
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
			this.hero.dropBomb();
			break;
		case KeyEvent.VK_U:
			this.u = true;
			break;
		case KeyEvent.VK_D:
			this.d = true;
			break;
		case KeyEvent.VK_Z:
			for(int i = 0; i < this.hero.bombs.size(); i++){
				this.hero.blowUpBomb(i);
			}
			break;
		}
		update();

	}

	/**
	 * Changes key Boolean to false to represent the button not being pressed
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		this.keys[e.getKeyCode()] = false;
		update();
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
