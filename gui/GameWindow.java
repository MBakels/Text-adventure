package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import game.Game;
import game.Room;

public class GameWindow extends JFrame{
	
	protected Game game;
	private static final long serialVersionUID = 1L;
	public Console console;
	private InputBox inputBox;
	private Map map;

	public GameWindow(Game game) {
		super("Text adventure");
		this.game = game;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(1495, 810);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2 - 100);
		
		getContentPane().setLayout(null);
		
		map = new Map();
		getContentPane().add(map);
		
		console = new Console();
		getContentPane().add(console);
		
		inputBox = new InputBox(this);
		getContentPane().add(inputBox);
		
		addWindowListener( new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
				inputBox.requestFocus();
			}
		});
	}
	
	private void ReDraw(){
		revalidate();
		repaint();
	}
	
	public void SetText(String text) {
		console.DeleteText();
		console.DisplayText(text, Color.BLACK);
	}
	
	public void DisplayText(String text) {
		console.DisplayText(text, Color.BLACK);
	}
	
	public void DisplayCommand(String command){
		console.DisplayCommand(command);
	}
	
	public void SetMapToRoom(Room currentRoom, Room nextRoom){
		map.SetRoom(currentRoom, nextRoom);
		ReDraw();
	}
	
	public Room CreateSafeRoom(int xPos, int yPos){
		Room room = map.CreateSafeRoom(xPos, yPos);
		ReDraw();
		return room;
	}
	
	public Room CreateNewRoom(int xPos, int yPos){
		Room room = map.CreateNewRoom(xPos, yPos);
		ReDraw();
		return room;
	}
	
	public void SetCell(int roomX, int roomY, int cellX, int cellY, String currentObject, String objectName){
		map.SetCell(roomX, roomY, cellX, cellY, currentObject, objectName);
	}
	
	public Room[][] GetRoomsList(){
		return map.GetRoomsList();
	}

}
