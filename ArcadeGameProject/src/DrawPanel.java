import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements Runnable 
{
	protected TileLayer layer;
	private GameKeyListener keyLis;
	
	public DrawPanel(GameKeyListener keyLis){
		this.layer = TileLayer.FromFile("LevelOne.txt");
		this.layer.setKeyLis(keyLis);
		this.keyLis = keyLis;
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