package gui;

import java.awt.Color;

import javax.swing.JPanel;

import game.Room;
import game.Vector2;

public class Map extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private Room[][] rooms;
	private int roomGridSize;

	public Map() {
		setBounds(10, 10, 760, 760);
		setBackground(Color.white);
		setLayout(null);
		
		roomGridSize = 10;
		rooms = new Room[roomGridSize][roomGridSize];
	}
	
	public void SetCell(int roomX, int roomY, int cellX, int cellY, String currentObject, String objectName){
		rooms[roomX][roomY].SetCell(cellX, cellY, currentObject, objectName);
	}
	
	public void SetRoom(Room currentRoom, Room nextRoom){
		rooms[(int)currentRoom.GetLocation().x][(int)currentRoom.GetLocation().y].setVisible(false);
		rooms[(int)nextRoom.GetLocation().x][(int)nextRoom.GetLocation().y].setVisible(true);
	}
	
	public Room CreateSafeRoom(int xPos, int yPos){
		rooms[xPos][yPos] = new Room(new Vector2(xPos, yPos), false, false);
		add(rooms[xPos][yPos]);
		return rooms[xPos][yPos];
	}
	
	public Room CreateNewRoom(int xPos, int yPos){
		rooms[xPos][yPos] = new Room(new Vector2(xPos, yPos), true, true);
		add(rooms[xPos][yPos]);
		return rooms[xPos][yPos];
	}
	
	public Room[][] GetRoomsList(){
		return rooms;
	}

}
