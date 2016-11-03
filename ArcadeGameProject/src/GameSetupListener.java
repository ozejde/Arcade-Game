import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameSetupListener implements ActionListener{
	private Main main;

	public GameSetupListener(Main main) {
		this.main = main;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.main.gameSetup();
	}

}
