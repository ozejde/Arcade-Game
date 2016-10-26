abstract class Character implements Runnable {

	private int ulhx = 0;
	private int ulhy = 0;
	private int lrhx = 0;
	private int lrhy = 0;
	private static final int SIZE = 48;
	private boolean distructable = false;
	private boolean passable = false;
	private int indexX;
	private int indexY;


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
