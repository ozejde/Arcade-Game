abstract class Character{

	private static final int SIZE = 48;
	private boolean distructable = false;
	private boolean passable = false;


	public Character() {
		super();
	}

	public boolean isDistructable() {
		return this.distructable;
	}

	public void setDistructable(boolean distructable) {
		this.distructable = distructable;
	}

	public boolean isPassable() {
		return this.passable;
	}

	public void setPassable(boolean passable) {
		this.passable = passable;
	}

}
