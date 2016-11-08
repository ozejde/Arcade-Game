import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GameSetupListener implements ActionListener{
	private Main main;
	private JFrame frame;

	public GameSetupListener(Main main, JFrame frame) {
		this.main = main;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton buttonPressed = (JButton) e.getSource();
		String title = buttonPressed.getText();
		
		if(title.equals("Start Game")){
			this.main.gameSetup();
		}
		
		if(title.equals("Instructions")){
			this.main.instuctSetup();
		}
		
		if(title.equals("Back")){
			this.main.startSetup();
		}
		
		this.frame.dispose();
	}

}
