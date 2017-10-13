package worldofzuul;

/**
 * @author  Michael Kolling and David J. Barnes
 * @version 2006.03.30
 */
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    
          
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    private void createRooms()
    {
        
     Room outside, grydehutten, cafeteria, U55, basement, U1, fitness, iFitness, bib, hallway, TEK, hallway2;

        outside = new Room("outside the main entrance of the university");
        grydehutten = new Room("");
        cafeteria = new Room("in the cafeteria");
        U55 = new Room("in lecure room U55");
        basement = new Room("in the basement of the university");
        U1 = new Room ("in lecture room U1");
        hallway = new Room ("in the long hallway in front of room U55");
        bib = new Room (" in the university's library");
        fitness = new Room ("in front of the intrance to the gym");
        iFitness = new Room ("in the gym");
        TEK = new Room ("in front of the bronze stairs at TEK");
        hallway2 = new Room ("in the small hallway in front of lecture room U1");
       

        outside.setExit("TEK", TEK);
        hallway.setExit("U55", U55);
        hallway.setExit("cafeteria", cafeteria);
        outside.setExit("basement", basement);
        bib.setExit("hallway 2", hallway2);
        outside.setExit("aroundUni", fitness);
        hallway.setExit("bib", bib);
        outside.setExit("hallway", hallway);
        outside.setExit("", grydehutten);
        U1.setExit("hallway 2", hallway2);
        iFitness.setExit("",fitness);
        grydehutten.setExit("west", outside);
        cafeteria.setExit("", outside);
        U55.setExit("hallway", hallway);        
        hallway.setExit("basement", basement);
        basement.setExit("hallway", hallway);
        hallway2.setExit("bib", bib);
        hallway2.setExit("U1", U1);
        fitness.setExit("i fitness", iFitness);
        TEK.setExit("outside", outside);
        TEK.setExit("hallway", hallway);
        cafeteria.setExit("hallway", hallway);


        currentRoom = outside;
    }

    public void play() 
    {            
        printWelcome();

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        if(commandWord == CommandWord.UNKNOWN) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        if (commandWord == CommandWord.HELP) {
            printHelp();
        }
        else if (commandWord == CommandWord.GO) {
            goRoom(command);
        }
        else if (commandWord == CommandWord.QUIT) {
            wantToQuit = quit(command);
        }
        return wantToQuit;
    }

    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    

    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;
        }
    }
}
