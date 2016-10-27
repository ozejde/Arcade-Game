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
		
		new Thread (new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						System.out.println("loop running");
						Thread.sleep(10);
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
		System.out.println("DrawPanel drawing");
		super.paintComponent(g);
		this.layer.createTiles(g);
		this.layer.paintComponent(g);
	}
	@Override
	public void run() {
		
	}
}