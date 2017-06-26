package gui;

import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class Console extends JScrollPane {
	
	private static final long serialVersionUID = 1L;
	private JTextPane textBox;

	public Console() {
		textBox = new JTextPane();
		getViewport().add(textBox);
		setBounds(780, 10, 700, 700);
		textBox.setEditable(false);
		textBox.setBackground(Color.white);
		
		 DefaultCaret caret = (DefaultCaret)textBox.getCaret();
		 caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
	}
	
	public void DeleteText() {
		textBox.setText(null);
	}
	
	public void DisplayText(String text, Color color) {		
		SimpleAttributeSet style = new SimpleAttributeSet();
		StyleConstants.setForeground(style, color);
		StyleConstants.setFontSize(style, 13);
        
        try {
			textBox.getDocument().insertString(textBox.getDocument().getLength(), text, style);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	public void DisplayCommand(String command){
		DisplayText("\n\n", Color.BLACK);
		DisplayText("> " + command, Color.BLUE);
		DisplayText("\n\n", Color.black);
	}
	
}
