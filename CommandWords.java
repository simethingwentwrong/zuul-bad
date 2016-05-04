import java.util.HashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    private HashMap<String,Option> comandos;
    
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
         comandos = new HashMap<>();
         comandos.put("andare",Option.GO);
         comandos.put("cessare",Option.QUIT);
         comandos.put("assistenza",Option.HELP);
         comandos.put("guardare",Option.LOOK);
         comandos.put("consumare",Option.EAT);
         comandos.put("indietro",Option.BACK);
         comandos.put("prendere",Option.TAKE);
         comandos.put("cadere",Option.DROP);
         comandos.put("elementi",Option.ITEMS);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {        
        return comandos.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){        
        System.out.println(comandos.keySet());
    }
    
     /**
      * Return the object Option associated with a word.
      * @param commandWord The word to look up (as a string).
      * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
      *  if it is not a valid command word
      */
     public Option getCommandWord(String commandWord)
     {
         Option devolver = Option.UNKNOWN;
         if (isCommand(commandWord))
         {
             devolver = comandos.get(commandWord);
         }
         return devolver;
     }
}
