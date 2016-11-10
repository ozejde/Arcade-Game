import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

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
	protected TileLayer layer;
	protected GameKeyListener keyLis;
	protected Hero hero;
	private int level = 1;
	JLabel label = new JLabel();
	private Main main;
	private JFrame frame;
	ArrayList<String> powerups;
	protected JLabel detonateLabel;
	protected JPanel powerupPanel;
	protected JLabel increaseLabel;
	protected JLabel moreLabel;
	protected JLabel addLabel;
	private JLabel levelLabel;

	/**
	 * 
	 * Creates a DrawPanel with repaint timer
	 *
	 */
	public DrawPanel(Main main, JFrame frame) {
		this.main = main;
		this.frame = frame;
		this.detonateLabel = new JLabel();
		this.increaseLabel = new JLabel();
		this.moreLabel = new JLabel();
		this.addLabel = new JLabel();
		this.powerups = new ArrayList<>();
		this.hero = new Hero(408, 350,this);
		this.layer = TileLayer.FromFile("Level1.txt", this.hero);
		this.levelLabel = new JLabel("<html><font color='white'>Level 1</font><html>");
		this.levelLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
		this.keyLis = new GameKeyListener(this.hero, this);
		this.setLayout(new BorderLayout());
		this.layer.setKeyLis(this.keyLis);
		this.addKeyListener(this.keyLis);
		this.setFocusable(true);
		this.layer.createTiles();
		this.label.setText("<html><font color='white'>Lives: " + this.hero.getLives()+"</font><html>");

		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 4));
		labelPanel.setOpaque(false);
		this.add(labelPanel, BorderLayout.NORTH);

		JButton backButton = new JButton("Back to Menu");
		backButton.addActionListener(new GameSetupListener(this.main, this.frame));
		labelPanel.add(backButton);

		labelPanel.add(this.levelLabel);
		JLabel label = this.label;
		label.setText("Lives: " + this.hero.getLives());
		label.setFont(new Font("Sans Serif", Font.BOLD, 30));
		labelPanel.add(label);

		JButton instrButton = new JButton("New Game");
		instrButton.addActionListener(new GameSetupListener(this.main, this.frame));
		labelPanel.add(instrButton);
		
		
		this.powerupPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 40));
		this.powerupPanel.setOpaque(false);
		this.add(this.powerupPanel, BorderLayout.PAGE_END);
		
		this.moreLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
		this.detonateLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
		this.increaseLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
		this.addLabel.setFont(new Font("Sans Serif", Font.BOLD, 20));
		
		this.powerupPanel.add(this.moreLabel);
		this.powerupPanel.add(this.detonateLabel);
		this.powerupPanel.add(this.increaseLabel);
		this.powerupPanel.add(this.addLabel);
		
		
		
		

		this.setFocusable(true);

		new Thread(new Runnable() {
			int livesAdded = 0;

			@Override
			public void run() {

				// Periodically asks Java to repaint this component
				try {
					boolean isPaused = false;
					while (true) {
						isPaused = DrawPanel.this.getPaused();
						while (!isPaused) {
							isPaused = DrawPanel.this.keyLis.getPaused();
//							Thread.sleep(20);
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
								DrawPanel.this.resetPowerups();
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
										if(!DrawPanel.this.powerups.contains("Detonatable Bombs")){
											DrawPanel.this.powerups.add("Detonatable Bombs");
											DrawPanel.this.detonateLabel.setText("<html><font color='white'>"+"Detonatable Bombs"+"</font><html>");
										}
									}
									if (t.getPowerTileType().equals("IncreaseRange")) {
										t.createNewGroundTile();
										t.setPowerUp(false);
										DrawPanel.this.hero.addRange();
										if(!DrawPanel.this.powerups.contains("Increased Range")){
											DrawPanel.this.powerups.add("Increased Range");
											DrawPanel.this.increaseLabel.setText("<html><font color='white'>"+"Increased Range"+"</font><html>");
										}
									}
									if (t.getPowerTileType().equals("MoreBombs")) {
										t.setPowerUp(false);
										t.createNewGroundTile();
										DrawPanel.this.hero.addBombCount();
										if(!DrawPanel.this.powerups.contains("Multiple Bombs")){
											DrawPanel.this.powerups.add("Multiple Bombs");
											DrawPanel.this.moreLabel.setText("<html><font color='white'>"+"Multiple Bombs"+"</font><html>");
										}
									}
									
									if (t.getPowerTileType().equals("AddLife")) {
										t.setPowerUp(false);
										t.createNewGroundTile();
										DrawPanel.this.hero.addLife();
										this.livesAdded++;
										DrawPanel.this.addLabel.setText("<html><font color='white'>"+"Total Lives Added: "+this.livesAdded+"</font><html>");
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
						System.out.println();

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
		if (this.level > 5) {
			this.level = 1;
		} else {
			this.level = this.level + 1;
		}
		fileName = "Level" + this.level + ".txt";
		this.layer.removeKeyListener(this.keyLis);
		this.layer = TileLayer.FromFile(fileName, this.hero);
		this.layer.setKeyLis(this.keyLis);
		this.layer.createTiles();
		this.layer.hero.reset();
		this.resetPowerups();
		this.levelLabel.setText("<html><font color='white'>Level "+this.level+"</font><html>");
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
			this.level = 6;
		}
		else {
			this.level = this.level - 1;
		}
		this.layer.removeKeyListener(this.keyLis);
		fileName = "Level" + this.level + ".txt";
		this.layer = TileLayer.FromFile(fileName, this.hero);
		this.layer.setKeyLis(this.keyLis);
		this.layer.createTiles();
		this.layer.hero.reset();
		System.out.println("Text Set");
		this.resetPowerups();
		this.levelLabel.setText("<html><font color='white'>Level "+this.level+"</font><html>");
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
	
	public void resetPowerups(){
		this.addLabel.setText("");
		this.moreLabel.setText("");
		this.detonateLabel.setText("");
		this.increaseLabel.setText("");
		this.powerups.clear();
	}
	
	
}