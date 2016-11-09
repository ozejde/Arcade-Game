import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * 
 * Creates and draws the game objects
 *
 * @author ejdeoz, youngqom, petersmt. Created Oct 27, 2016.
 * @param <booleanIsPaused>
 */
@SuppressWarnings("serial")
public class DrawPanel<booleanIsPaused> extends JPanel {
	protected static boolean flag = true;
	protected TileLayer layer;
	protected GameKeyListener keyLis;
	protected Hero hero;
	private int level = 1;
	JLabel label = new JLabel();
	private Main main;
	private JFrame frame;

	/**
	 * 
	 * Creates a DrawPanel with repaint timer
	 *
	 */
	public DrawPanel(Main main, JFrame frame) {
		this.main = main;
		this.frame = frame;
		this.hero = new Hero(408, 350);
		this.layer = TileLayer.FromFile("Level1.txt", this.hero);
		this.keyLis = new GameKeyListener(this.hero, this);
		this.layer.setKeyLis(this.keyLis);
		this.addKeyListener(this.keyLis);
		this.setFocusable(true);
		this.layer.createTiles();
		this.label.setText("<html><font color='white'>Lives: " + this.hero.getLives()+"</font><html>");

		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
		labelPanel.setOpaque(false);
		this.add(labelPanel, BorderLayout.NORTH);

		JButton backButton = new JButton("Back to Menu");
		backButton.addActionListener(new GameSetupListener(this.main, this.frame));
		labelPanel.add(backButton);

		JLabel label = this.label;
		label.setText("Lives: " + this.hero.getLives());
		label.setFont(new Font("Sans Serif", Font.BOLD, 30));
		labelPanel.add(label);

		JButton instrButton = new JButton("New Game");
		instrButton.addActionListener(new GameSetupListener(this.main, this.frame));
		labelPanel.add(instrButton);

		this.setFocusable(true);

		new Thread(new Runnable() {

			@Override
			public void run() {

				// Periodically asks Java to repaint this component
				try {
					boolean isPaused = false;
					while (DrawPanel.this.hero.getLives() > 0) {
						isPaused = DrawPanel.this.getPaused();
//						System.out.println(isPaused);
						while (!isPaused) {
							isPaused = false;
							// Thread.sleep(30);
							DrawPanel.this.label.setText("<html><font color='white'>Lives: " + DrawPanel.this.hero.getLives()+"</font><html>");
							if (DrawPanel.this.hero.monsters.size() == 0) {
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
							}
							if (DrawPanel.this.hero.getLives() <= 0) {
								throw new InterruptedException("Game Over");
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

							isPaused = DrawPanel.this.keyLis.getPaused();
							DrawPanel.this.repaint();
						}
//						System.out.println("Is going through outer loop at all");

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
		if (this.level > 3) {
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
			this.level = 4;
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

	public void setFocusable() {
		this.setFocusable(true);
	}

	public boolean getPaused() {
		return this.keyLis.getPaused();
	}
}