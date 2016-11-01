import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 
 * A black, circular graphic that can destroy BrickWall, dropped when B pressed
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 */
public class Bomb {
	private int x;
	private int y;
	private ArrayList<Tile> tiles;

	/**
	 * 
	 * Sets the coordinates of Bomb
	 *
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 */
	public Bomb(int x, int y, ArrayList<Tile> tiles) {
		this.x = x;
		this.y = y;
		this.tiles = tiles;
		Timer timer = new Timer();
		timer.schedule(new Task(), 5000);
	}

	class Task extends TimerTask {

		@Override
		public void run() {
			Bomb.this.explode();
			System.out.println("Bomb has exploded");
		}
	}

	/**
	 * 
	 * Draws image of Bomb
	 * 
	 * @param graphics
	 *            Graphics that Bomb is painted onto
	 * 
	 */
	public void drawCharacter(Graphics graphics) {
		graphics.setColor(Color.BLACK);
		Graphics2D gCast = (Graphics2D) graphics;
		Ellipse2D.Double bomb = new Ellipse2D.Double(this.x, this.y, 20, 20);
		gCast.fill(bomb);
	}

	public void explode(){
		//remove bomb from screen
		//find tile that bomb is in
		// use tile coordinates to find 4 tiles/characters surrounding it
		//determine if objects are destructible, if so remove them from screen.
		
		//remove bomb from arraylist of bombs
		//remove graphic of bomb from screen
		
		
		Tile bombTile = null;
		ArrayList<Tile> surroundingTiles = null;
		
		
		for (Tile tile: tiles){
			if (tile.getX1()<=this.x&&tile.getX2()>=this.x&&tile.getY1()<=this.y&&tile.getY2()>=this.y){
				bombTile = tile;
				surroundingTiles.add(bombTile);
			}
		}
		
		int ux = bombTile.getX1() + 24;
		int uy = bombTile.getY1() - 24;
		int lx = bombTile.getX2() + 24;
		int ly =  bombTile.getY2() + 24;
		int rx =  bombTile.getX1() - 24;
		int ry =  bombTile.getY1() + 24;
		int dx =  bombTile.getX1() + 24;
		int dy =  bombTile.getY2() + 24;
		
		Tile tileUp = null;
		Tile tileRight = null;
		Tile tileLeft = null;
		Tile tileDown = null;
		
		for(Tile tile: tiles){
			if (tile.getX1()<=ux&&tile.getX2()>=ux&&tile.getY1()<=uy&&tile.getY2()>=uy){
				tileUp = tile;
				surroundingTiles.add(tileUp);
			}
			else if (tile.getX1()<=rx&&tile.getX2()>=rx&&tile.getY1()<=ry&&tile.getY2()>=ry){
				tileRight = tile;
				surroundingTiles.add(tileRight);
			}
			else if (tile.getX1()<=lx&&tile.getX2()>=lx&&tile.getY1()<=ly&&tile.getY2()>=ly){
				tileLeft = tile;
				surroundingTiles.add(tileLeft	);
			}
			else if (tile.getX1()<=dx&&tile.getX2()>=dx&&tile.getY1()<=dy&&tile.getY2()>=dy){
				tileDown = tile;
				surroundingTiles.add(tileDown);
			}
		}
		
		for(Tile tile : surroundingTiles){
			if(tile.isDestructible()){
				//remove tile and replace with ground tile
			}
		}
		
		//if hero is in surroundingTiles, hero.death
		
		//if monster is in surroundingTiles, remove monster
		
		//if bomb is in surroundingTiles, explode bomb
	}

}
