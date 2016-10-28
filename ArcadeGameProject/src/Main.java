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

	public static void main(String[] args) {
		JFrame frame = new JFrame("BomberMan Test");

		DrawPanel panel = new DrawPanel();

		frame.add(panel);
		frame.setSize(834, 688);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);

	}

}
