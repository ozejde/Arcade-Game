import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel 
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
		
		new Thread (new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						Thread.sleep(1);
						DrawPanel.this.repaint();
					
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		}).start();
		
		
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.layer.createTiles();
		this.layer.paintComponent(g);
		
	}
}