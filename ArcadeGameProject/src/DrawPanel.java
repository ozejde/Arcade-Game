import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements Runnable 
{
	protected TileLayer layer;
	private GameKeyListener keyLis;
	private Hero hero;
	public DrawPanel(){
		this.hero = new Hero(408, 350);
		this.layer = TileLayer.FromFile("LevelOne.txt", this.hero);
		this.keyLis = new GameKeyListener(this.hero);
		this.layer.setKeyLis(this.keyLis);
		this.addKeyListener(this.keyLis);
		this.setFocusable(true);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.layer.createTiles(g);
		this.layer.paintComponent(g);
	}
	@Override
	public void run() {
		
	}
}