import java.awt.Graphics;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * Creates and draws the game objects
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 */
@SuppressWarnings("serial")
public class DrawPanel extends JPanel {
	protected TileLayer layer;
	protected GameKeyListener keyLis;
	protected Hero hero;
	private int level = 1;
	JLabel label = new JLabel();

	/**
	 * 
	 * Creates a DrawPanel with repaint timer
	 *
	 */
	public DrawPanel() {
		this.hero = new Hero(408, 350);
		this.layer = TileLayer.FromFile("Level1.txt", this.hero);
		this.keyLis = new GameKeyListener(this.hero, this);
		this.layer.setKeyLis(this.keyLis);
		this.addKeyListener(this.keyLis);
		this.setFocusable(true);
		this.layer.createTiles();
		this.label.setText("Lives: " + this.hero.getLives());

		new Thread(new Runnable() {
			@Override
			public void run() {
				// Periodically asks Java to repaint this component
				try {
					while (true) {
						DrawPanel.this.label.setText("Lives: " + DrawPanel.this.hero.getLives());
						if (DrawPanel.this.hero.monsters.size() == 0) {
							System.out.println("No more monsters");
							DrawPanel.this.levelUp();
						}
						Thread.sleep(1);
						for (Monster m : DrawPanel.this.layer.m) {
							m.monsterMove();
						}
						if (DrawPanel.this.hero.checkMonster()) {
							DrawPanel.this.hero.subtractLife();
							DrawPanel.this.hero.reset();
							for (Monster m : DrawPanel.this.layer.m) {
								m.reset();
							}

							if (DrawPanel.this.hero.getLives() <= 0) {
								throw new InterruptedException("Game Over");
							}
						}
						for (Tile t : DrawPanel.this.layer.tiles) {
							if (t.isBlownUp() && t.getPowerUp() && DrawPanel.this.hero.checkIfInTile(t)) {
								if (t.getPowerTileType().equals("Detonate")) {
									t.setPowerUp(false);
									t.createNewGroundTile();
									DrawPanel.this.hero.setDetonatable(true);
								}
								if (t.getPowerTileType().equals("IncreaseRange")) {
									t.createNewGroundTile();
									t.setPowerUp(false);
									DrawPanel.this.hero.addRange();
								}
								if (t.getPowerTileType().equals("MoreBombs")) {
									t.setPowerUp(false);
									t.createNewGroundTile();
									DrawPanel.this.hero.addBombCount();
								}
								if (t.getPowerTileType().equals("AddLife")) {
									t.setPowerUp(false);
									t.createNewGroundTile();
									DrawPanel.this.hero.addLife();
								}
							}
						}

						if (DrawPanel.this.keyLis.u) {
							DrawPanel.this.keyLis.u = false;
							DrawPanel.this.levelUp();
						}
						if (DrawPanel.this.keyLis.d) {
							DrawPanel.this.keyLis.d = false;
							DrawPanel.this.levelDown();
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
	 * Switches to a higher level, or loops back to first level if last level is
	 * reached
	 *
	 */
	public void levelUp() {
		String fileName = "";
		if (this.level > 2) {
			this.level = 1;
			fileName = "Level" + this.level + ".txt";
			this.layer.removeKeyListener(this.keyLis);
			this.layer = TileLayer.FromFile(fileName, this.hero);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
			this.layer.hero.reset();

		} else {
			this.level = this.level + 1;
			this.layer.removeKeyListener(this.keyLis);
			fileName = "Level" + this.level + ".txt";
			this.layer = TileLayer.FromFile(fileName, this.hero);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
			this.layer.hero.reset();
		}
	}

	/**
	 * 
	 * Switches to a lower level, or loops back to last level if first level is
	 * reached
	 *
	 */
	public void levelDown() {
		String fileName = "";
		if (this.level < 2) {
			this.level = 3;
			this.layer.removeKeyListener(this.keyLis);
			fileName = "Level" + this.level + ".txt";
			this.layer = TileLayer.FromFile(fileName, this.hero);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
			this.layer.hero.reset();
		}

		else {
			this.level = this.level - 1;
			this.layer.removeKeyListener(this.keyLis);
			fileName = "Level" + this.level + ".txt";
			this.layer = TileLayer.FromFile(fileName, this.hero);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
			this.layer.hero.reset();
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