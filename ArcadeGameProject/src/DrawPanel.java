import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel
{
	protected TileLayer layer;
	protected GameKeyListener keyLis;
	
	public DrawPanel(){
		this.keyLis = keyLis;
		this.layer = TileLayer.FromFile("Level3.txt", this.keyLis);
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.layer.createTiles(g);
		this.layer.paintComponent(g);
	}
}