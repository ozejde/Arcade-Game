import java.awt.Graphics;

/**
 * 
 * Abstract class for Monster objects
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 * 
 */
public abstract class Monster extends Character {

	/**
	 * 
	 * Super constructor which all types of Monsters use.
	 * 
	 * @param j
	 * 
	 * @param i
	 *
	 */
	public Monster(int i, int j) {
		super(i, j);
		this.setdestructible(true);
	}


	public void drawMonster(Graphics g) {
		//Method to be overridden in subclasses
	}


	public void monsterMove() {
		//Method to be overridden in subclasses
	}

}
