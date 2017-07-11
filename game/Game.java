package game;

import java.util.Random;
import java.util.Vector;

import gui.GameWindow;
import items.Item;

public class Game {
	
	private GameWindow window;
	private Parser parser;
	private Player player;
	private int ticksUsedThisTurn;

	public static void main(String[] args) {
		Game game = new Game();
		game.Play();
	}
	
	public Game() {
		window = new GameWindow(this);
		window.setVisible(true);
        parser = new Parser();
        player = new Player();
        ticksUsedThisTurn = 0;
    }
	
	private void Play() {
		window.SetText("Welcome to Adventure!" + "\n" + "Adventure is a new, incredibly boring adventure game." + "\n" + "Type 'help' if you need help.");
		Room startRoom = window.CreateSafeRoom(5, 5);
		player.SetRoom(startRoom);
		window.SetCell((int)player.GetRoom().GetLocation().x, (int)player.GetRoom().GetLocation().y, (int)player.GetLocation().x, (int)player.GetLocation().y, "Player", "player");
    }
	
	public void CommandGiven(String string){
		window.DisplayCommand(string);
		Command command = parser.getCommand(string);
		processCommand(command);
	}
	
	private void processCommand(Command command) {
        if(command.isUnknown()) {
        	window.DisplayText("I don't know what you mean...");
        	return;
        }

        String commandWord = command.getCommandWord();
        
        switch(commandWord){
        case "help":
        	PrintHelp();
        	break;
        case "move":
        	Move(command);
        	break;
        case "quit":
        	Quit(command);
        	break;
        case "look":
        	Look();
        	break;
        case "inventory":
        	Inventory();
        	break;
		case "pickup":
			CheckItemToPickUpLocation(command);
	    	break;
		case "drop":
			DropItem(command);
			break;
		case "use":
			UseItem(command);
			break;
		case "status":
			Status();
			break;
		case "attack":
			Attack();
			break;
		}
        Update();
    }
	
	private void Update(){
		if(ticksUsedThisTurn != 0){
			player.GetRoom().UpdateRoom(ticksUsedThisTurn);
			ticksUsedThisTurn = 0;
		}
	}
	
	private void Attack(){
		
	}
	
	private void Status(){
		window.DisplayText("You take some time to look at your body and come the these conclosions:\n");
		window.DisplayText("Health: " + player.GetHealth());
	}
	
	private void UseItem(Command command){
		String item = "";
		if(command.hasSecondWord()) {
			item = command.getSecondWord();
        }
		if(command.hasThirdWord()){
			item += " " + command.getThirdWord();
		}
		if(item == ""){
			window.DisplayText("What item do you want to use?");
			return;
		}
		if(player.GetInventory().Contains(item)){
			Item itemToUse = player.GetInventory().GetItemByName(item);
			String itemUseText = itemToUse.Use(player);
			window.DisplayText(itemUseText);
			ticksUsedThisTurn += 1;
		}else{
			window.DisplayText("You cant use what you dont have!");
		}
	}
	
	private void CheckItemToPickUpLocation(Command command){
		Vector2 location = player.GetLocation();
		String item = "";
		if(command.hasSecondWord()) {
			item = command.getSecondWord();
        }
		if(command.hasThirdWord()){
			item += " " + command.getThirdWord();
		}
		if(item == ""){
			window.DisplayText("What item do you want to pickup?");
			return;
		}
		if(CheckIfItemInCell((int)location.x + 1, (int)location.y - 1, item)){
			PickUpItem((int)location.x + 1, (int)location.y - 1);
			return;
		}
		if(CheckIfItemInCell((int)location.x, (int)location.y - 1, item)){
			PickUpItem((int)location.x, (int)location.y - 1);
			return;
		}
		if(CheckIfItemInCell((int)location.x - 1, (int)location.y - 1, item)){
			PickUpItem((int)location.x - 1, (int)location.y - 1);
			return;
		}
		
		if(CheckIfItemInCell((int)location.x + 1, (int)location.y, item)){
			PickUpItem((int)location.x + 1, (int)location.y);
			return;
		}
		if(CheckIfItemInCell((int)location.x - 1, (int)location.y, item)){
			PickUpItem((int)location.x - 1, (int)location.y);
			return;
		}
		
		if(CheckIfItemInCell((int)location.x + 1, (int)location.y + 1, item)){
			PickUpItem((int)location.x + 1, (int)location.y + 1);
			return;
		}
		if(CheckIfItemInCell((int)location.x, (int)location.y + 1, item)){
			PickUpItem((int)location.x, (int)location.y + 1);
			return;
		}
		if(CheckIfItemInCell((int)location.x - 1, (int)location.y + 1, item)){
			PickUpItem((int)location.x - 1, (int)location.y + 1);
			return;
		}
		window.DisplayText("The is no " + item + " within reach!");
	}
	
	private boolean CheckIfItemInCell(int x, int y, String item){
		return player.GetRoom().GetCellContendsObjectName(x, y).equalsIgnoreCase(item);
	}
	
	private void PickUpItem(int x, int y){
		String itemName = player.GetRoom().GetCellContendsObjectName(x, y);
		Inventory roomInv = player.GetRoom().GetInventory();
		Inventory playerInv = player.GetInventory();
		if(roomInv.Contains(itemName)){
			Item itemToPickup;
			for(int i = 0; i < roomInv.GetSize(); i++){
				itemToPickup = roomInv.GetItemByIndex(i);
				if(Vector2.CompareVectors(itemToPickup.GetItemInRoomLocation(), new Vector2(x, y))){
					boolean itemHasBeenPickedUp = playerInv.AddItem(itemToPickup);
					if(itemHasBeenPickedUp){
						roomInv.RemoveItem(itemToPickup);
						player.GetRoom().SetCell(x, y, "Empty", "empty");
						window.DisplayText("You picked up a " + itemName);
						ticksUsedThisTurn += 1;
						return;
					}
					window.DisplayText("Your backpack is full or weighs to much.");
					return;
				}
			}
		}
	}
	
	private void DropItem(Command command){
		String item = "";
		if(command.hasSecondWord()) {
			item = command.getSecondWord();
        }
		if(command.hasThirdWord()){
			item += " " + command.getThirdWord();
		}
		if(item == ""){
			window.DisplayText("What item do you want to drop?");
			return;
		}
		if(player.GetInventory().Contains(item)){
			Item itemToDrop = player.GetInventory().GetItemByName(item);
			Vector2 itemRandomLocation = GetRandomCellAroundPlayer();
			window.DisplayText("You dropped a " + item);
			player.GetRoom().GetInventory().AddItem(itemToDrop);
			player.GetInventory().RemoveItem(itemToDrop);
			player.GetRoom().SetCell((int)itemRandomLocation.x, (int)itemRandomLocation.y, "Item", item);
			itemToDrop.SetItemInRoomLocation((int)itemRandomLocation.x, (int)itemRandomLocation.y);
			ticksUsedThisTurn += 1;
		}else{
			window.DisplayText("This item is not in your inventory!");
		}
	}
	
	private Vector2 GetRandomCellAroundPlayer(){
		Random random = new Random();
		Vector2 randomCell = player.GetLocation().copy();
		int x;
		int y;
		do{
			x = random.nextInt(3) - 1;
			y = random.nextInt(3) - 1;
		}while(x == 0 && y == 0);
		randomCell.add(new Vector2(x, y));
		return randomCell;
	}
	
	private void Look(){
		if(player.GetRoom().GetInventory() == null){
			window.DisplayText("You look around but you see nothing besides trees.");
		}else{
			window.DisplayText("You look around the room.\n");
			window.DisplayText(player.GetRoom().GetInventory().ShowContends());
		}
		ticksUsedThisTurn += 1;
	}
	
	private void PrintHelp() {
		window.DisplayText(
		"You are lost. You are alone. You wander" + 
		"\n" + "around in the forest." + 
		"\n\n" + 
		"Your command words are:" + 
		"\n" + 
		parser.showCommands());
    }
	
	private void Move(Command command) {
		boolean movedSuccessfully = false;
		
        if(!command.hasSecondWord()) {
            window.DisplayText("Move where?");
            return;
        }

        String direction = command.getSecondWord();
        int steps = 1;
        
        if(command.hasThirdWord()){
	        try{
	        	steps = Integer.parseInt(command.getThirdWord());
	        }catch(NumberFormatException e){
	        	window.DisplayText("Not a valid amount of steps.");
	        	return;
	        }
        }
        
        if(steps > 10){
        	window.DisplayText("You cant do more than 10 steps!");
        	return;
        }
        
        if(steps <= 0){
        	steps = 1;
        }
        
        switch(direction){
        case "up":
        	movedSuccessfully = MovePlayer(new Vector2(0, -steps), steps);
        	break;
        case "down":
        	movedSuccessfully = MovePlayer(new Vector2(0, steps), steps);
        	break;
        case "left":
        	movedSuccessfully = MovePlayer(new Vector2(-steps, 0), steps);
        	break;
        case "right":
        	movedSuccessfully = MovePlayer(new Vector2(steps, 0), steps);
        	break;
        default:
        	window.DisplayText("Please choose from up, down, left and right.");
        	return;
        }
        
        if(movedSuccessfully){
        	window.DisplayText("Moving " + direction + " " + steps + " step(s).");
        }
    }
	
	
	
	private boolean MovePlayer(Vector2 direction, int steps){
		Vector2 movingTo = player.GetLocation().copy().add(direction);
		Vector2 roomLocation = player.GetRoom().GetLocation().copy();
		
		if(movingTo.x > 0 && movingTo.x < (player.GetRoom().GetSize() - 1) && movingTo.y > 0 && movingTo.y < (player.GetRoom().GetSize() - 1)){
			if(!CheckIfCellIsNotObstructed(player.GetRoom(), movingTo)){
				return false;
			}
		}
		
		if(movingTo.x < 0){
			roomLocation.x --;
			int x = (int)movingTo.x + player.GetRoom().GetSize();
			Room nextRoom = CreateNextRoomIfNoneExistent(roomLocation);
			if(CheckIfCellIsNotObstructed(nextRoom, new Vector2(x, player.GetLocation().y))){
				window.SetCell((int)player.GetRoom().GetLocation().x, (int)player.GetRoom().GetLocation().y, (int)player.GetLocation().x, (int)player.GetLocation().y, "Empty", "empty");
				MovePlayerToNextRoom(roomLocation, new Vector2(x, player.GetLocation().y));
				ticksUsedThisTurn += x + 1;
				return true;
			}
			return false;
		}else if(movingTo.x > (player.GetRoom().GetSize() - 1)){
			roomLocation.x ++;
			int x = (int)movingTo.x - player.GetRoom().GetSize();
			Room nextRoom = CreateNextRoomIfNoneExistent(roomLocation);
			if(CheckIfCellIsNotObstructed(nextRoom, new Vector2(x, player.GetLocation().y))){
				window.SetCell((int)player.GetRoom().GetLocation().x, (int)player.GetRoom().GetLocation().y, (int)player.GetLocation().x, (int)player.GetLocation().y, "Empty", "empty");
				MovePlayerToNextRoom(roomLocation, new Vector2(x, player.GetLocation().y));
				ticksUsedThisTurn += x + 1;
				return true;
			}
			return false;
		}else if(movingTo.y < 0){
			roomLocation.y --;
			int y = (int)movingTo.y + player.GetRoom().GetSize();
			Room nextRoom = CreateNextRoomIfNoneExistent(roomLocation);
			if(CheckIfCellIsNotObstructed(nextRoom, new Vector2(player.GetLocation().x, y))){
				window.SetCell((int)player.GetRoom().GetLocation().x, (int)player.GetRoom().GetLocation().y, (int)player.GetLocation().x, (int)player.GetLocation().y, "Empty", "empty");
				MovePlayerToNextRoom(roomLocation, new Vector2(player.GetLocation().x, y));
				ticksUsedThisTurn += y + 1;
				return true;
			}
			return false;
		}else if(movingTo.y > (player.GetRoom().GetSize() - 1)){
			roomLocation.y ++;
			int y = (int)movingTo.y - player.GetRoom().GetSize();
			Room nextRoom = CreateNextRoomIfNoneExistent(roomLocation);
			if(CheckIfCellIsNotObstructed(nextRoom, new Vector2(player.GetLocation().x, y))){
				window.SetCell((int)player.GetRoom().GetLocation().x, (int)player.GetRoom().GetLocation().y, (int)player.GetLocation().x, (int)player.GetLocation().y, "Empty", "empty");
				MovePlayerToNextRoom(roomLocation, new Vector2(player.GetLocation().x, y));
				ticksUsedThisTurn += y + 1;
				return true;
			}
			return false;
		}else{
			window.SetCell((int)player.GetRoom().GetLocation().x, (int)player.GetRoom().GetLocation().y, (int)player.GetLocation().x, (int)player.GetLocation().y, "Empty", "empty");
			player.SetLocation(movingTo);
			window.SetCell((int)player.GetRoom().GetLocation().x, (int)player.GetRoom().GetLocation().y, (int)player.GetLocation().x, (int)player.GetLocation().y, "Player", "player");
			ticksUsedThisTurn += steps;
			return true;
		}
	}
	
	private boolean CheckIfCellIsNotObstructed(Room room, Vector2 destination){
		Vector2 movingTo = destination.copy();
		if(room.GetCellContendsObjectName((int)movingTo.x, (int)movingTo.y) != "empty"){
			window.DisplayText("Something is blocking your path!");
			return false;
		}
		return true;
	}
	
	private Room CreateNextRoomIfNoneExistent(Vector2 nextRoomLocation){
		Room[][] rooms = window.GetRoomsList();
		if(rooms[(int)nextRoomLocation.x][(int)nextRoomLocation.y] == null){
			window.CreateNewRoom((int)nextRoomLocation.x, (int)nextRoomLocation.y);
		}
		return rooms[(int)nextRoomLocation.x][(int)nextRoomLocation.y];
	}
	
	private void MovePlayerToNextRoom(Vector2 nextRoomLocation, Vector2 newPlayerLocation){
		Room[][] rooms = window.GetRoomsList();
		window.SetMapToRoom(player.GetRoom(), rooms[(int)nextRoomLocation.x][(int)nextRoomLocation.y]);
		player.SetRoom(rooms[(int)nextRoomLocation.x][(int)nextRoomLocation.y]);
		player.SetLocation(newPlayerLocation);
		window.SetCell((int)player.GetRoom().GetLocation().x, (int)player.GetRoom().GetLocation().y, (int)player.GetLocation().x, (int)player.GetLocation().y, "Player", "player");
	}
	
	private void Inventory(){
		window.DisplayText(player.GetInventory().ShowContends());
		ticksUsedThisTurn += 1;
	}
	
	private void Quit(Command command) {
        if(command.hasSecondWord()) {
        	window.DisplayText("Quit what?");
        }else{
        	System.out.println("Thank you for playing.  Good bye.");
        	window.setVisible(false);
            window.dispose();
            System.exit(0);
        }
    }

}
