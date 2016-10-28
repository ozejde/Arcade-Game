import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DrawPanel extends JPanel 
{
	protected TileLayer layer;
	private GameKeyListener keyLis;
	private Hero hero;
	private int level = 1;
	
	
	public DrawPanel(){
		
		
		this.hero = new Hero(408, 350);
		this.layer = TileLayer.FromFile("Level1.txt", this.hero);
		this.keyLis = new GameKeyListener(this.hero, this);
		this.layer.setKeyLis(this.keyLis);
		this.addKeyListener(this.keyLis);
		this.setFocusable(true);
		this.layer.createTiles();


		
		new Thread (new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
//						checkLevelChange();
						Thread.sleep(1);
						DrawPanel.this.repaint();
						
					}
				} catch (InterruptedException exception) {
					// Stop when interrupted
				}
			}
		}).start();
	}
	
//	public void checkLevelChange(){
//		//System.out.println("checking lvl change");
//		if(this.keyLis.getU()){
//			System.out.println("draw panel agkn u key pressed");
//			levelUp();
//		}
//		else if(this.keyLis.getD()){
//			System.out.println("draw panel agkn d key pressed");
//			levelDown();
//		}
//	}
	
	public void levelUp(){
		String fileName = "";
		System.out.println("level up"+level);
		if(level > 2){
			level = 1;
			System.out.println("level up."+level);
			
			fileName = "Level"+level+".txt";
			
			this.layer.removeKeyListener(this.keyLis);
			this.layer = TileLayer.FromFile(fileName, this.hero);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
		}
		else{
			System.out.println("level up."+level);
			level = level + 1;
			System.out.println("level up."+level);
			this.layer.removeKeyListener(this.keyLis);
			fileName = "Level"+level+".txt";
			this.layer = TileLayer.FromFile(fileName, this.hero);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
		}
	}
	
	public void levelDown(){
		String fileName = "";
		System.out.println("before level down"+level);
		if(level < 2){
			System.out.println("before level down level < "+level);
			level = 3;
			this.layer.removeKeyListener(this.keyLis);
			fileName = "Level"+level+".txt";
			this.layer = TileLayer.FromFile(fileName, this.hero);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
		}
		
		else{
			level = level - 1;
			System.out.println("level down"+level);
			this.layer.removeKeyListener(this.keyLis);
			fileName = "Level"+level+".txt";
			this.layer = TileLayer.FromFile(fileName, this.hero);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
		}
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		this.layer.paintComponent(g);
		
	}
}