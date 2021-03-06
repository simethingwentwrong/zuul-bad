
import java.util.*;
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
    private Stack<Room> last;
    private Player player;
    private ArrayList<Npc> acolitos ;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        
        parser = new Parser();
        last = new Stack<>();
        acolitos = new ArrayList<Npc>();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room fuera, antesala, biblioteca, salaDeRezos, capillaLibro, salaDeTorturas, salaInvocaciones;
      
        // create the rooms
        fuera = new Room("Te encuentras frente a la entrada del templo tu mision a sido dada invoca a cthulhu.\nHaces un reconocimiento del templo es grande imponente pero tu sabes la verdad es un templo a los falsos dioses que vinieron y usurparon a los verdaderos dioses \nYosh�gothot desearia destruirlo Pero no es momento de lamentar cosas que van a cambiar ademas el tiempo corre en tu contra");
        antesala = new Room("Ves una imponente sala, llena de estatuas de los Antig�os aquellos que desterraron a los verdaderos dioses. \nSolo verlo te arde la sangre, un monton de antorchas iluminan la habitacion, aun asi, apenas se ve, las sombras te protegen aunque no parece haber nadie");
        biblioteca = new Room("La habitacion en la que has entrado parece una biblioteca. \nSe respira olor a magia antigua y libros viejos aqui no esta lo que buscas. \nVes un bibliotecario de espaldas a ti si te descubren el juego termina");
        salaDeRezos = new Room("Ante ti aparece una gran sala, llena de altares hay dos puertas semi escondidas. ");
        capillaLibro = new Room("Al fin lo encontraste ahi  esta delante tuyo el NECRONOMICON. \nBien que empieze el ritual este templo y todo ser que en el habite sera el sacrificio para despertar a nuestro dios \n�a �a Fgthan CTHULHU");
        salaDeTorturas = new Room("Es una sala oscura y tetrica avanzas un poco pero no ves nada, ves una luz y un hombre");
        salaInvocaciones = new Room("Estas en la sala de invocaciones ");
        
        // initialise room exits
        fuera.setExits(salaDeRezos, biblioteca, null, antesala, null, null);
        antesala.setExits(null, fuera, null, salaInvocaciones, null, null);
        biblioteca.setExits(null, null, null, fuera, null,null);
        salaDeRezos.setExits(capillaLibro, salaDeTorturas, fuera, null, null,null);
        capillaLibro.setExits(null, null, salaDeRezos, null, salaDeTorturas, null);
        salaDeTorturas.setExits(null, null, null, null, null, capillaLibro);
        salaInvocaciones.setExits(null, null, null, null, null, null);
        capillaLibro.addObjeto("Necronomicon", 0.8F, false);
        antesala.addObjeto("espada sagrada", 4F, false);
        salaDeRezos.addObjeto("pu�al", 0.8F, false);
        biblioteca.addObjeto("Libro Magico", 4F, false);
        acolitos.add(new Npc(biblioteca));
        acolitos.add(new Npc(salaDeTorturas));
        //arriba, derecha, abjo, izquierda
        currentRoom = fuera;  // start game outside
        player = new Player(currentRoom);
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
        printLocationInfo();
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

        Option commandWord = command.getCommandWord();
        if (commandWord.ordinal() ==2) {
            printHelp();
        }
        else if (commandWord.ordinal() ==0) {
            goRoom(command);
            if (currentRoom.getDescription().equals("Estas en la sala de invocaciones ")){
                winCondition();
            }
            if (currentRoom.getDescription().equals("La habitacion en la que has entrado parece una biblioteca. \nSe respira olor a magia antigua y libros viejos aqui no esta lo que buscas. \nVes un bibliotecario de espaldas a ti si te descubren el juego termina")){
                apu�alar();
            }
            if (currentRoom.getDescription().equals("Es una sala oscura y tetrica avanzas un poco pero no ves nada, ves una luz y un hombre ")){
                preso();
            }
        }
        else if (commandWord.ordinal() ==1) {
            wantToQuit = quit(command);
        }
        else if (commandWord.ordinal() ==3){
            System.out.print(currentRoom.getLongDescription());
        }
        else if (commandWord.ordinal() ==4){
            System.out.print("You have eaten now and you are not hungry any more");
        }
        else if (commandWord.ordinal() ==5){
             if (!last.empty())
             {
                 currentRoom = last.pop();
                  printLocationInfo();
             }
             else
             {
                System.out.println("No hay  inguna sala antes que esta");
             }
        }
        else if (commandWord.ordinal() ==8) {
            System.out.println(player.itemEncima());
        }
        else if (commandWord.ordinal() ==6){
            cojerItem(command);           
        }
        else if (commandWord.ordinal() ==7) {
            dejarItem(command);
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
        parser.getCommands().showAll();
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
        Room room = currentRoom.getExit(direction);
        if (room == null) {
            System.out.println("There is no door!");
        }
        else {
            last.push(currentRoom);
            player.setLocalizacion(room);
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
    
    private void printLocationInfo(){
        System.out.print(currentRoom.getLongDescription());
        System.out.println();
        
        
    }
    
     /**
     * coje el item de la sala y se lo da al jugador si puede
     */
    private void cojerItem(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Cojer que?");
            return;
        }
        String nomObj = command.getSecondWord();
        if(player.getLocalizacionActual().existeItem(nomObj))
        {
            Objeto item = player.getLocalizacionActual().cojerItem(nomObj);
            if (!player.cojerItem(item))
            {
                player.getLocalizacionActual().moveItem(item);
            }
        }
        else
        {
            System.out.println("No esta ese item en esta sala");
        }
    }
    
    /**
     * Coje el item del jugador y lo deja en la sala
     */
    private void dejarItem(Command command)
    {
        if(!command.hasSecondWord()) {
            System.out.println("Dejar que?");
            return;
        }
        String nomObj = command.getSecondWord();
        if(player.existeItem(nomObj))
        {
            player.getLocalizacionActual().moveItem(player.dejarItem(nomObj));
        }
        else
        {
            System.out.println("El jugador no tiene ese item");
        }
    }
    
    /**
     * condicion de victoria
     */
    public void winCondition()
    {
        if(player.getLocalizacionActual().getDescription().equals("Estas en la sala de invocaciones")  && player.tieneLibro()){
            System.out.println("Perfecto ya es nuestro ahora solo queda invocarlo, �ah �ah Fgthang Cthulhu despierta de este largo sue�o y arrasa este mundo de impios. n/ HAS MUERTO INVOCANDO A TU DIOS");
        }
        else {
            System.out.println("Todavia no puedes hacer la invocacion te estas adelantando");
        }
    }
    
    /**
     * condicion de victoria
     */
    public void apu�alar()
    {
        if(player.getLocalizacionActual().getDescription().equals("La habitacion en la que has entrado parece una biblioteca. \nSe respira olor a magia antigua y libros viejos aqui no esta lo que buscas. \nVes un bibliotecario de espaldas a ti si te descubren el juego termina")  && player.tienePu�al()){
            System.out.println("Te hacercas siilosamente por la espalda y le clabas e pu�al esta muero ya no dara problemas");
            int cont = 0;
            boolean matado = false;
            while(cont < acolitos.size() && !matado)
            {
                if(acolitos.get(cont).getRoomAct().equals("La habitacion en la que has entrado parece una biblioteca. \nSe respira olor a magia antigua y libros viejos aqui no esta lo que buscas. \nVes un bibliotecario de espaldas a ti si te descubren el juego termina")){
                  matado = true;
                  acolitos.remove(cont);
                }
                cont++;
            }
        }
        else{
            System.out.println("te ha descubierto has muerto");
        }
    }
    
    public void preso()
    {
        System.out.println("Ahora estas atrapado con migo aqui, lo siento se termino tu juego puedes salir de el cuando te de la gana");
    }
}
