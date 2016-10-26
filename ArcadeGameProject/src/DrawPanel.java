import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel implements Runnable 
{
	private TileLayer layer;
	
	public DrawPanel(){
		this.layer = TileLayer.FromFile("LevelOne.txt");
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.layer.paintComponent(g);
	}
	@Override
	public void run() {
		
	}
}