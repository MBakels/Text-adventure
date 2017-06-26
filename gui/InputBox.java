package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class InputBox extends JTextField {
	
	private static final long serialVersionUID = 1L;

	public InputBox(GameWindow window) {
		setBounds(780, 715, 700, 55);
		addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				window.game.CommandGiven(getText());
				setText(null);
			}
		});
	}

}
