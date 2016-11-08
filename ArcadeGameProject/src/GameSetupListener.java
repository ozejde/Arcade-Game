import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		frame.dispose();
		this.main.gameSetup();
	}

}
