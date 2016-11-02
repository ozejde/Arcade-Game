import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * Creates and draws the game objects
 *
 * @author  ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 */
@SuppressWarnings("serial")
public class DrawPanel extends JPanel {
	protected TileLayer layer;
	private GameKeyListener keyLis;
	protected Hero hero;
	private int level = 1;
	protected ArrayList<Monster> m1 = new ArrayList<>();

	/**
	 * 
	 * Creates a DrawPanel with repaint timer
	 *
	 */
	public DrawPanel() {
		
		this.m1.add(new MonsterOne(116, 170));
		this.m1.add(new MonsterOne(597, 160));
		this.m1.add(new MonsterOne(116, 454));
		this.hero = new Hero(408, 350, this.m1);
		this.layer = TileLayer.FromFile("Level1.txt", this.hero, this.m1);
		this.keyLis = new GameKeyListener(this.hero, this);
		this.layer.setKeyLis(this.keyLis);
		this.addKeyListener(this.keyLis);
		this.setFocusable(true);
		this.layer.createTiles();

		new Thread(new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						// checkLevelChange();
						Thread.sleep(1);
						for (Monster m : DrawPanel.this.m1) {
							m.monsterMove();
						}
						if(DrawPanel.this.hero.checkMonster()){
							DrawPanel.this.hero.subtractLife();
							DrawPanel.this.hero.reset();
							for (Monster m : DrawPanel.this.m1) {
								m.reset();
							}
							if(DrawPanel.this.hero.monsters.isEmpty()){
								DrawPanel.this.levelUp();
							}
							if(DrawPanel.this.hero.getLives() <= 0){
								throw new InterruptedException("Game Over");
							}
						}
						DrawPanel.this.repaint();

					}
				} catch (InterruptedException exception) {
						JOptionPane.showMessageDialog(null, exception.getMessage());
				}
			}
		}).start();
	}

	/**
	 * 
	 * Switches to a higher level, or loops back to first level if last level is reached
	 *
	 */
	public void levelUp() {
		String fileName = "";
		System.out.println("level up" + this.level);
		if (this.level > 2) {
			this.level = 1;
			System.out.println("level up." + this.level);
			fileName = "Level" + this.level + ".txt";
			this.layer.removeKeyListener(this.keyLis);
			this.layer = TileLayer.FromFile(fileName, this.hero, this.m1);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
			this.layer.hero.reset();
			for (Monster m : DrawPanel.this.m1) {
				m.reset();
			}
		} else {
			System.out.println("level up." + this.level);
			this.level = this.level + 1;
			System.out.println("level up." + this.level);
			this.layer.removeKeyListener(this.keyLis);
			fileName = "Level" + this.level + ".txt";
			this.layer = TileLayer.FromFile(fileName, this.hero, this.m1);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
			this.layer.hero.reset();
			for (Monster m : DrawPanel.this.m1) {
				m.reset();
			}
		}
	}

	/**
	 * 
	 * Switches to a lower level, or loops back to last level if first level is reached
	 *
	 */
	public void levelDown() {
		String fileName = "";
		if (this.level < 2) {
			this.level = 3;
			this.layer.removeKeyListener(this.keyLis);
			fileName = "Level" + this.level + ".txt";
			this.layer = TileLayer.FromFile(fileName, this.hero, this.m1);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
			this.layer.hero.reset();
			for (Monster m : DrawPanel.this.m1) {
				m.reset();
			}
		}

		else {
			this.level = this.level - 1;
			this.layer.removeKeyListener(this.keyLis);
			fileName = "Level" + this.level + ".txt";
			this.layer = TileLayer.FromFile(fileName, this.hero, this.m1);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
			this.layer.hero.reset();
			for (Monster m : DrawPanel.this.m1) {
				m.reset();
			}
		}
	}

	/**
	 * Paints the objects onto the screen
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.layer.paintComponent(g);

	}
}