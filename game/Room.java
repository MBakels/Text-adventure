package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

import enemys.Enemy;
import gui.Cell;
import items.Item;

public class Room extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int size;
	private Cell[][] cells;
	private int width;
	private int height;
	private int cellWidth;
	private int cellHeight;
	private Vector2 roomLocation;
	private Inventory roomInventory;
	private ItemSpawner itemSpawner;
	private List<Enemy> enemys;
	
	public Room(Vector2 location, Boolean spawnItems, Boolean spawnEnemys) {
		setSize(760, 760);
		setBackground(Color.white);
		setLayout(null);
		
		roomLocation = location;
		roomInventory = new Inventory(10, 20.00, "room");
		itemSpawner = new ItemSpawner();
		enemys = new ArrayList<Enemy>(5);
		
		size = 20;
		cells = new Cell[size][size];
		
		width = getWidth();
        height = getHeight();

        cellWidth = width / size;
        cellHeight = height / size;
		
		CreateGrid();
		
		if(spawnItems){
			spawnItems();
		}
		if(spawnEnemys){
			SpawnEnemys();
		}
	}
	
	public void UpdateRoom(int ticks){
		Iterator<Enemy> iterator = enemys.iterator();
		while(iterator.hasNext()) {
			Enemy enemy = iterator.next();
			if(!enemy.IsAlive()){
				Vector2 enemyLocation = enemy.GetLocation();
				SetCell((int)enemyLocation.x, (int)enemyLocation.y, "DeadEnemy", "dead enemy");
				
				return;
			}else{
				enemy.UpdateAI(ticks);
			}
	    }
	}
	
	private void CreateGrid(){
		for(int x = 0; x < size; x++){
			for(int y = 0; y < size; y++){
				cells[x][y] = new Cell(x * cellWidth, y * cellHeight, cellWidth, cellHeight);
				add(cells[x][y]);
			}
		}
	}
	
	private void SpawnEnemys(){
		Random ran = new Random();
		int randomAmountOfEnemys = ran.nextInt(3);
		for(int i = 0; i < randomAmountOfEnemys; i++){
			int xPos = ran.nextInt(size);
			int yPos = ran.nextInt(size);
			Enemy enemy = new Enemy(new Vector2(xPos, yPos));
			SetCell(xPos, yPos, "Enemy", "enemy");
			enemys.add(enemy);
		}
	}
	
	private void spawnItems(){
		Random ran = new Random();
		int randomAmountOfItems = ran.nextInt(5);
		for(int i = 0; i < randomAmountOfItems; i++){
			int xPos = ran.nextInt(size);
			int yPos = ran.nextInt(size);
			Item randomItem = itemSpawner.GetRandomItem();
			randomItem.SetItemInRoomLocation(xPos, yPos);
			SetCell(xPos, yPos, "Item", randomItem.GetItemName());
			roomInventory.AddItem(randomItem);
		}
	}
	
	public void SetCell(int x, int y, String currentObject, String objectName){
		cells[x][y].setCell(currentObject, objectName);
	}
	
	public String GetCellContendsObjectName(int x, int y){
		return cells[x][y].GetCellContendsObjectName();
	}
	
	public Vector2 GetLocation(){
		return roomLocation;
	}
	
	public int GetSize(){
		return size;
	}
	
	public Inventory GetInventory(){
		return roomInventory;
	}

}
