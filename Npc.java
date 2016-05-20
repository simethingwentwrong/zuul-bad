
/**
 * Write a description of class Npc here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Npc
{
    // instance variables - replace the example below with your own
    private Room roomAct;
    /**
     * Constructor for objects of class Npc
     */
    public Npc(Room inicial)
    {
        // initialise instance variables
        roomAct = inicial;
    }

    /**
     * An example of a method - replace this comment with your own
     * 
     * @param  y   a sample parameter for a method
     * @return     the sum of x and y 
     */
      public Room getRoomAct()
     {
         return roomAct;
     }

}
