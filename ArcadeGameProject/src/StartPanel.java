import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class StartPanel extends JPanel{

	public StartPanel() {
		this.setLayout(new BorderLayout());
		this.add(new JButton("Start Game"), BorderLayout.LINE_START);
		this.add(new JButton("Instructions"), BorderLayout.LINE_END);
	}

}
