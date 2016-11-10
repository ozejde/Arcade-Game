import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start by running
 * main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo
 *
 */

public class Main {

	public static void main(String[] args) {
		JButton startButton = new JButton("Start Game");
		JButton instrButton = new JButton("Instructions");

		JPanel p = new JPanel();
		p.add(startButton);
		p.add(instrButton);

		JFrame frame = new JFrame("Start Game");
		frame.add(p, BorderLayout.SOUTH);
		frame.add(new JLabel(new ImageIcon("titlepic.png")), BorderLayout.NORTH);

		Main main = new Main();
		startButton.addActionListener(new GameSetupListener(main, frame));
		instrButton.addActionListener(new GameSetupListener(main, frame));
		frame.setSize(1000, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

	}

	public void startSetup() {
		JButton startButton = new JButton("Start Game");
		JButton instrButton = new JButton("Instructions");

		JPanel p = new JPanel();
		p.add(startButton);
		p.add(instrButton);

		JFrame frame = new JFrame("Start Game");
		frame.add(p, BorderLayout.SOUTH);
		frame.add(new JLabel(new ImageIcon("titlepic.png")), BorderLayout.NORTH);

		Main main = new Main();
		startButton.addActionListener(new GameSetupListener(main, frame));
		instrButton.addActionListener(new GameSetupListener(main, frame));
		frame.setSize(834, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

	}

	public void instructSetup() {
		JFrame frame = new JFrame();
		frame.setTitle("Bomber Man Instructions");
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

		JButton backButton = new JButton("Back to Menu");
		JButton startButton = new JButton("Start Game");
		backButton.addActionListener(new GameSetupListener(this, frame));
		startButton.addActionListener(new GameSetupListener(this, frame));
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(backButton);
		buttonPanel.add(startButton);
		frame.add(buttonPanel, BorderLayout.SOUTH);

		JLabel titleLabel = new JLabel("Instructions");
		titleLabel.setFont(new Font("Sans Serif", Font.PLAIN, 40));
		panel.add(titleLabel);

		JLabel manualLabel = new JLabel(
				"<html>Controls" + " <br> • Press the arrow keys to move" + "<br> • Press B to drop bombs"
						+ "<br> • Press U to play the next level, and D to play the previous level"
						+ "<br> • Press Z to detonate controllable bombs (Power-up ability) " + "<br> "
						+ "<br> General Rules" + "<br> • You cannot pass through brick or concrete walls"
						+ "<br> • Bombs will kill monsters, destroy brick walls, and damage you"
						+ "<br> • Some brick walls contain powerups beneath them, only accessable when the wall is bombed"
						+ "<br> • The Bomb's reach is one tile in each cardinal direction (North, South, East, West)"
						+ "<br> • If you are within range of the bomb, you will be damaged and lose one life"
						+ "<br> • Only one bomb can be on the screen at one time, except if a powerup is being used"
						+ "<br> • If multiple bombs are on the screen, bombs will explode if in the range of another exploding bomb"
						+ "<br> • Coming into contact with a monster will cause you to lose one life"
						+ "<br> • Your remaining number of lives can be seen in the upper left corner of the screen"
						+ "<br> • You can only gain lives through certain powerups" + "<br>" + "<br>Power-Ups"
						+ "<br> • Purple tiles add a life" + "<br> • Red tiles make the bombs detonatable"
						+ "<br> • Orange tiles increase the bomb's blast distance by one tile in all directions"
						+ "<br> • Blue allows you to drop more than one bomb at a time"
						+ "<br> • Death removes all power-ups " 
						+ "<br> • Leveling up removes all power ups<html>"
		                + "<br>"
		                 + "<br> Monsters"
		                 + "<br> •"
		                 + "<br> • <html>");

		manualLabel.setFont(new Font("Sans Serif", Font.PLAIN, 20));
		panel.add(manualLabel);

		frame.add(panel);
		frame.setSize(834, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

	public void gameSetup() {
		JFrame frame = new JFrame();
		DrawPanel panel = new DrawPanel(this, frame);
		JPanel labelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 100, 0));
		frame.add(panel, BorderLayout.CENTER);

		// JButton backButton = new JButton("Back to Menu");
		// backButton.addActionListener(new GameSetupListener(this, frame));
		// labelPanel.add(backButton);
		//
		// JLabel label = panel.label;
		// label.setText("Lives: " + panel.hero.getLives());
		// label.setFont(new Font("Sans Serif", Font.BOLD, 30));
		// labelPanel.add(label);
		// frame.add(labelPanel, BorderLayout.NORTH);
		//
		// JButton instrButton = new JButton("Instructions");
		// instrButton.addActionListener(new GameSetupListener(this, frame));
		// labelPanel.add(instrButton);

		frame.setTitle("BomberMan Game");

		frame.setSize(834, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
