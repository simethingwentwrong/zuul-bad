/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room fuera, antesala, biblioteca, salaDeRezos, capillaLibro, salaDeTorturas, salaInvocaciones;
      
        // create the rooms
        fuera = new Room("Te encuentras frente a la entrada del templo tu mision a sido dada invoca a cthulhu./n Haces un reconocimiento del templo es grande imponente pero tu sabes la verdad es un templo a los falsos dioses que vinieron y usurparon a los verdaderos dioses Yosh�gothot desearia destruirlo /n Pero no es momento de lamentar cosas que van a cambiar ademas el tiempo corre en tu contra");
        antesala = new Room("Ves una imponente sala, llena de estatuas de los Antig�os aquellos que desterraron a los verdaderos dioses. /n Solo verlo te arde la sangre, un monton de antorchas iluminan la habitacion, aun asi, apenas se ve, las sombras te protegen aunque no parece haber nadie");
        biblioteca = new Room("La habitacion en la que has entrado parece una biblioteca. Se respira olor a magia antigua y libros viejos aqui no esta lo que buscas. /n Ves un bibliotecario de espaldas a ti si te descubren el juego termina");
        salaDeRezos = new Room("Ante ti aparece una gran sala, llena de altares hay dos puertas semi escondidas. ");
        capillaLibro = new Room("Al fin lo encontraste ahi  esta delante tuyo. /n Bien cojes el libro ahora hayque conseguir entrar en la sala de invocaciones");
        salaDeTorturas = new Room("Es una sala oscura y tetrica avanzas un poco pero no ves nada, notas una presencia de tras de ti te giras y ves una sombra te golpea quedas inconsciente /n GAME OVER");
        salaInvocaciones = new Room("Esta es la sala en la que Cthulhu sera invocadoesta es la sala en la que nuestro gran Primigenio, se despertara de su profundo sue�o /n una luz te deslumbra /n que pasa aqui esto no es normal /n voces en coro te rodean y cantan al unisono /n la sangre de un seguidor inpio es necesaria para la invocacion de nuestro se�or, muerte al infiel larga vida a los antiguos /n GAME OVER");
        
        // initialise room exits
        fuera.setExits(salaDeRezos, biblioteca, null, antesala);
        antesala.setExits(null, fuera, null, salaInvocaciones);
        biblioteca.setExits(null, null, null, fuera);
        salaDeRezos.setExits(capillaLibro, salaDeTorturas, fuera, null);
        capillaLibro.setExits(null, null, salaDeRezos, null);
        salaDeTorturas.setExits(null, null, null, null);
        salaInvocaciones.setExits(null, null, null, null);
        //arriba, derecha, abjo, izquierda
        currentRoom = fuera;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bien venido a Cthulhu adventure");
        System.out.println("Es otro juego mas de rol, pero de nuestro dios Cthulhu");
        System.out.println("Teclea 'help' si necesitas ayuda.");
        System.out.println();
        System.out.println( currentRoom.getDescription());
        System.out.print("Exits: ");
        if(currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if(currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if(currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if(currentRoom.westExit != null) {
            System.out.print("west ");
        }
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("no se a que te refieres");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the temple.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println( currentRoom.getDescription());
            System.out.print("Exits: ");
            if(currentRoom.northExit != null) {
                System.out.print("north ");
            }
            if(currentRoom.eastExit != null) {
                System.out.print("east ");
            }
            if(currentRoom.southExit != null) {
                System.out.print("south ");
            }
            if(currentRoom.westExit != null) {
                System.out.print("west ");
            }
            System.out.println();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
