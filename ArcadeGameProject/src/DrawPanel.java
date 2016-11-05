import java.awt.Graphics;
import java.util.ArrayList;

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
	private GameKeyListener keyLis;
	protected Hero hero;
	private int level = 1;
	protected ArrayList<Monster> m1 = new ArrayList<>();
	private static final ArrayList<Monster> overallMonsters = new ArrayList<>();
	static {
		overallMonsters.add(new MonsterOne(116, 170));
		overallMonsters.add(new MonsterOne(597, 160));
		overallMonsters.add(new MonsterOne(116, 454));
		overallMonsters.add(new MonsterTwo(116, 215));
		overallMonsters.add(new MonsterTwo(597, 215));
		overallMonsters.add(new MonsterTwo(116, 503));
	}

	/**
	 * 
	 * Creates a DrawPanel with repaint timer
	 *
	 */
	public DrawPanel() {

		this.addMonsters();
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
						// System.out.println(DrawPanel.this.hero.monsters.size());
						if (DrawPanel.this.hero.monsters.size() == 0) {
							System.out.println("No more monsters");
							DrawPanel.this.levelUp();
						}
						Thread.sleep(1);
						for (Monster m : DrawPanel.this.m1) {
							m.monsterMove();
						}
						if (DrawPanel.this.hero.checkMonster()) {
							DrawPanel.this.hero.subtractLife();
							DrawPanel.this.hero.reset();
							for (Monster m : DrawPanel.this.m1) {
								m.reset();
							}

							if (DrawPanel.this.hero.getLives() <= 0) {
								throw new InterruptedException("Game Over");
							}
						}
						for (Tile t : DrawPanel.this.layer.tiles) {
							if (t.getPowerUp() && DrawPanel.this.hero.checkIfInTile(t)) {
								if (t.getPowerTileType().equals("Detonate")) {
									t.setPowerUp(false);
									t.createNewGroundTile();
//									DrawPanel.this.hero.addRange();
								}
								if (t.getPowerTileType().equals("IncreaseRange")) {
									t.createNewGroundTile();
									t.setPowerUp(false);
									DrawPanel.this.hero.addRange();
									System.out.println("Hero Bomb Range " + DrawPanel.this.hero.range);
								}
								if (t.getPowerTileType().equals("MoreBombs")) {
									t.setPowerUp(false);
									t.createNewGroundTile();
									DrawPanel.this.hero.addBombCount();
									System.out.println("Hero Bomb Count: " + DrawPanel.this.hero.bombCount);
								}
							}
							DrawPanel.this.repaint();
						}
					}
				} catch (InterruptedException exception) {
					JOptionPane.showMessageDialog(null, exception.getMessage());
				}
			}
		}).start();
	}

	private void addMonsters() {
		for (Monster m : overallMonsters) {
			if (!m1.contains(m)) {
				m1.add(m);
			}
		}
		for (Monster m : this.m1) {
			m.reset();
		}

	}

	/**
	 * 
	 * Switches to a higher level, or loops back to first level if last level is
	 * reached
	 *
	 */
	public void levelUp() {
		this.addMonsters();
		String fileName = "";
		if (this.level > 2) {
			this.level = 1;
			fileName = "Level" + this.level + ".txt";
			this.layer.removeKeyListener(this.keyLis);
			this.layer = TileLayer.FromFile(fileName, this.hero, this.m1);
			this.layer.setKeyLis(this.keyLis);
			this.layer.createTiles();
			this.layer.hero.reset();

		} else {
			this.level = this.level + 1;
			this.layer.removeKeyListener(this.keyLis);
			fileName = "Level" + this.level + ".txt";
			this.layer = TileLayer.FromFile(fileName, this.hero, this.m1);
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
		this.addMonsters();
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