import java.util.ArrayList;
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
    private Option[] comandos;
    
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
         comandos = Option.values();
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {        
         boolean esCom = false;
         for(int i = 0;i<comandos.length;i++)
         {
             if (comandos[i].getNombreC().equals(aString))
             {
                 esCom = true;
             }
        }
         return esCom;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll(){        
          String comands = "";
         for(int i = 0;i<comandos.length;i++)
         {
             comands = comands + comandos[i].getNombreC()+" ";
         }
         System.out.println(comands);
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
              for(int i = 0;i<comandos.length;i++)
             {
                if (comandos[i].getNombreC().equals(commandWord))
                 {
                     devolver = comandos[i];
                 }
             }
         }
         return devolver;
     }
}
