import javax.swing.JButton;
import javax.swing.JFrame;

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
	
	JFrame frame;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Start Screen");
		JButton startButton = new JButton("Start Game");
		frame.add(startButton);
		Main main = new Main();
		startButton.addActionListener(new GameSetupListener(main));
		frame.setSize(834, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

	}
	
	public void gameSetup(){
		DrawPanel panel = new DrawPanel();
		this.frame = new JFrame("BomberMan Game");
		this.frame.add(panel);
		this.frame.setSize(834, 688);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		this.frame.setResizable(false);

	}

}
