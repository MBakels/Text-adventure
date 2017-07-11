package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Cell extends JLabel {
	
	private static final long serialVersionUID = 1L;
	String CellContendsObjectName;

	public Cell(int x, int y, int cellWidth, int cellHeight) {
		super("X", SwingConstants.CENTER);
		setBounds(x, y, cellWidth, cellHeight);
		setOpaque(true);
		setBackground(Color.lightGray);
		setFont(new Font("Serif", Font.PLAIN, 20));
		CellContendsObjectName = "empty";
	}
	
	public void setCell(String currentObject, String objectName){
		switch(currentObject){
			case "Player":
				setBackground(Color.GREEN);
				this.setText("P");
				CellContendsObjectName = "player";
				break;
			case "Enemy":
				setBackground(Color.RED);
				this.setText("E");
				CellContendsObjectName = "enemy";
				break;
			case "DeadEnemy":
				setBackground(Color.ORANGE);
				this.setText("DE");
				CellContendsObjectName = "dead enemy";
				break;
			case "Item":
				setBackground(Color.YELLOW);
				this.setText("I");
				CellContendsObjectName = objectName;
				break;
			case "Empty":
				setBackground(Color.lightGray);
				this.setText("X");
				CellContendsObjectName = "empty";
				break;
		}
		
	}
	
	public String GetCellContendsObjectName(){
		return CellContendsObjectName;
	}

}
