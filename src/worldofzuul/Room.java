package worldofzuul;

import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;


/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Room 
{
    private String description;
    private HashMap<String, Room> exits;
    private Monster monsterInRoom;
    private Character jeff = new Character();
    private Step[][] stepList;
    private int currentPosition1 = 0;
    private int currentPosition2 = 0;
    private Step currentPosition;
    private Character player;
    
    public Room(String description) 
    {
        this.description = description;
        this.exits = new HashMap<String, Room>();
//        this.monsterInRoom = new Monster(100,1);
        this.stepList = new Step[3][3];
    	this.player = jeff;
        this.currentPosition = stepList[0][0];
        buildStepList(3, 3);

    }
    
    public Room(String description, int length, int depth) 
    {
    	
        this.description = description;
        this.exits = new HashMap<String, Room>();
//        this.monsterInRoom = new Monster(100,1);
    	this.player = jeff;
        this.stepList = new Step[length][depth];
        this.currentPosition = stepList[0][0];
        buildStepList(length, depth);
    }
    
    public Room(String description, Monster monster) 
    {
        this.description = description;
        this.exits = new HashMap<String, Room>();
        this.monsterInRoom = monster;
    	this.player = jeff;
        this.stepList = new Step[3][3];
        this.currentPosition = stepList[0][0];
        buildStepList(3, 3);

    }
    
    public void setMonster(int hp, int damage) 
    {
    	monsterInRoom = new Monster(hp, damage);
    }
    
    public Monster getMonster() {
        return monsterInRoom;
    }

    
    public Character getPlayer() {
        return jeff;
    }
    
    public Character getCharacter() {
        return this.player;
    }

    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    public String getShortDescription()
    {
        return description;
    }

    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }
    
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    public Step startingPosition() {
    	return this.stepList[0][0];
    }
    
    public void setCurrentPosition(Step step, String direction) {
    	if(direction.equals("east")) {
        	this.currentPosition = this.stepList[1][0];
        	System.out.println("Currently at 1, 0");
    	}
    	
    	if(direction.equals("north")) {
        	this.currentPosition = this.stepList[2][1];
        	System.out.println("Currently at 2, 1");
    	}
    	
    	if(direction.equals("west")) {
        	this.currentPosition = this.stepList[1][2];
        	System.out.println("Currently at 1, 2");
    	}
    	
    	if(direction.equals("south")) {
        	this.currentPosition = this.stepList[0][1];
        	System.out.println("Currently at 0, 1");
    	}
    }
    
    public void buildStepList(int length, int depth) {
    	for(int i = 0; i < length; i++) {
    		this.stepList[0][i] = new Step(new Character("Erik", 1000, "There is no cowlevel"));
    		this.stepList[1][i] = new Step(new Monster(50, 10));
    		this.stepList[2][i] = new Step(new Item("Potion", 2));
    	}
    }
    
    public Step move(String move) {
    	if(move.equals("up")) {
    		if(currentPosition1 - 1 >= 0) {
    			currentPosition1 = currentPosition1 - 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    	    	System.out.print("\r" + "\r" + "\r" + "\r");
    			System.out.println("Currently at " + currentPosition1 + ", " + currentPosition2);
    			return currentPosition;
    		} else { 
    			System.out.println("Out of bounds, try again"); 
    			return null;
    			}
    	} 
    	if(move.equals("down")) {
    		if(currentPosition1 + 1 <= 2) {
    			currentPosition1 = currentPosition1 + 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    	    	System.out.print("\r" + "\r" + "\r" + "\r");
    			System.out.println("Currently at " + currentPosition1 + ", " + currentPosition2);
    			return currentPosition;
    		} else { 
    			System.out.println("Out of bounds, try again"); 
    			return null;	
    		}
    	} 
    	if(move.equals("left")) {
    		if(currentPosition2 - 1 >= 0) {
    			currentPosition2 = currentPosition2 - 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    	    	System.out.print("\r" + "\r" + "\r" + "\r");
    			System.out.println("Currently at " + currentPosition1 + ", " + currentPosition2);
    			return currentPosition;
    		} else { 
    			System.out.println("Out of bounds, try again"); 
    			return null;
    			}
    	} 
    	if(move.equals("right")) {
    		if(currentPosition2 + 1 <= 2) {
    			currentPosition2 = currentPosition2 + 1;
    			this.currentPosition = stepList[currentPosition1][currentPosition2];
    	    	System.out.print("\r" + "\r" + "\r" + "\r");
    			System.out.println("Currently at " + currentPosition1 + ", " + currentPosition2);
    			return currentPosition;
    		} else { 
    			System.out.println("Out of bounds, try again"); 
    			return null;
    			}
    	} else {
    		System.out.println("Bad move");
    		return null;
    	}
    }
    
    public boolean checkIfRoomTraversalIsOkay(String direction) {
    	if(direction.equals("east")) {
    		if(this.currentPosition != this.stepList[1][2]) {
    			System.out.println("You must be next to the door, at 1, 2.");
    			return false;
    		} 
    	}
    	if(direction.equals("west")) {
    		if(this.currentPosition != this.stepList[1][0]) {
    			System.out.println("You must be next to the door, at 1, 0.");
    			return false;
    		} 
    	}
    	
    	if(direction.equals("north")) {
    		if(this.currentPosition != this.stepList[0][1]) {
    			System.out.println("You must be next to the door, at 0, 1.");
    			return false;
    		} 
    	}
    	
    	if(direction.equals("south")) {
    		if(this.currentPosition != this.stepList[2][1]) {
    			System.out.println("You must be next to the door, at 2, 1.");
    			return false;
    		} 
    	}
    	
    	return true;
    }
    
    public void printStep(Step step) {
    	System.out.println("On this step, there is: ");
    	if(step.getInformation() != null) {
    		System.out.println(step.getInformation());
    	} else { System.out.println("No information"); }
    	if(step.getCharacter() != null) {
    		System.out.println("Character: " +step.getCharacter().getName());
    	} 
    	if(step.getItem() != null) {
    		System.out.println("A  " +step.getItem().getName());
    	} 
    	if(step.getMonster() != null) {
    		System.out.println("Monster: " + step.getMonster().getHp() + " hp");
    	} 
    	printRoom();
    	   	
    }
    
    public void printRoom() {
    	for(int i = 0; i < 3; i++) {
    		for(int j = 0; j < 3; j++) {
    			if(this.stepList[i][j] == this.currentPosition) {
    				System.out.print(" X ");
    			} else { 
    				System.out.print(" O ");
    			}
    		}
    		System.out.println();
    	}
    }
    
    public Step currentPosition() {
    	return this.stepList[currentPosition1][currentPosition2];
    }
}

