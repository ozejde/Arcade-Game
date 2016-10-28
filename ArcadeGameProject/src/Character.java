/**
 * 
 * Abstract class for Character objects
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 */
abstract class Character {
	private static final int SIZE = 48;
	private boolean destructible = false;
	private boolean passable = false;

	/**
	 * 
	 * Constructs a Character class
	 *
	 */
	public Character() {
		super();
	}

	/**
	 * 
	 * Returns whether Character is destructible or not
	 *
	 * @return destructible field
	 */

	public boolean isDestructible() {
		return this.destructible;
	}
	
	/**
	 * 
	 *Sets destructible field to given boolean
	 *
	 * @param destructible
	 * Boolean to change destructible to
	 */
	public void setdestructible(boolean destructible) {
		this.destructible = destructible;
	}
	
	/**
	 * 
	 * Returns whether Character is passable or not
	 *
	 * @return passable field
	 */
	public boolean isPassable() {
		return this.passable;
	}

	/**
	 * 
	 *Sets passable field to given boolean
	 *
	 * @param passable
	 * Boolean to change passable to 
	 */
	public void setPassable(boolean passable) {
		this.passable = passable;
	}

}
