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
	
	/**
	 * 
	 * A method to draw a Monster to be overridden in subclasses 
	 * according to the type of monster.
	 * 
	 */
	public void drawMonster(Graphics g) {
		//Method to be overridden in subclasses
	}

	/**
	 * 
	 * A method to move a Monster to be overridden in subclasses 
	 * according to the type of movement of the monster.
	 * 
	 */
	public void monsterMove() {
		//Method to be overridden in subclasses
	}

}
