import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * The main class for your arcade game.
 * 
 * You can design your game any way you like, but make the game start
 * by running main here.
 * 
 * Also don't forget to write javadocs for your classes and functions!
 * 
 * @author Buffalo
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Write your cool arcade game here!");
		
		File file = new File("Level1");
		Scanner scan = null;
		try {
			
			System.out.println("before open");
			scan = new Scanner(file);
			String words = scan.nextLine();
			System.out.println(words);
			System.out.println("end of reading");
			scan.close();
		} catch (FileNotFoundException exception) {
			System.out.println("File not found. Try again.");
		}
		
	}

}
