import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameSetupListener implements ActionListener {
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

		if (title.equals("Start Game")||title.equals("New Game")) {
			this.main.gameSetup();
			this.frame.dispose();
		}

		if (title.equals("Instructions") || title.equals("Back to Menu")) {
			int n = 0;
			if (this.frame.getTitle().equals("BomberMan Game")) {

				n = //JOptionPane.showConfirmDialog(frame,
						//"Warning: Progress will be lost. Do you still want to continue?", "Warning",
						//JOptionPane.YES_NO_OPTION);
						JOptionPane.showConfirmDialog(null,
				                "Warning: Progress will be lost.",
				                "Warning",
				                JOptionPane.DEFAULT_OPTION,
				                JOptionPane.PLAIN_MESSAGE);
				System.out.println(n);
			} else {
				n = 0;
			}

			if (n == 0 && title.equals("Instructions")) {
				this.main.instructSetup();
				this.frame.dispose();
			} else if (n == 0 && title.equals("Back to Menu")) {
				this.main.startSetup();
				this.frame.dispose();
			} else {
				return;
			}
		}

	}

}
