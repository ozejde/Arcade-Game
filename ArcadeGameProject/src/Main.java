import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
		JFrame frame = new JFrame("Start Game");
		frame.add(startButton);
		Main main = new Main();
		startButton.addActionListener(new GameSetupListener(main, frame));
		frame.setSize(834, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

	}
	
	public void gameSetup(){
		JFrame frame = new JFrame();
		DrawPanel panel = new DrawPanel();
		JLabel label = panel.label;
		label.setText("Lives: " + panel.hero.getLives());
		frame.setTitle("BomberMan Game");
		frame.add(label, BorderLayout.NORTH);
		frame.add(panel);
		frame.setSize(834, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
