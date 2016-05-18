
import java.util.*;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private HashMap<String,Room> exits;
    private ArrayList<Objeto> objetos;
    private float pesoAc;
    private static final float pesoM = 3;
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        exits = new HashMap<String,Room>();
        this.description = description;
        objetos = new ArrayList<>();
    }

    /**
     * Fija el objeto de la sala
     */
    public void addObjeto(String objeto, float peso, boolean puede)
    {
        objetos.add(new Objeto(objeto,peso,puede));
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southeast, Room northwest) 
    {
        if(north != null){
            exits.put("northExit",north);
        }
        if(east != null){
            exits.put("eastExit",east);
        }
        if(south != null){
            exits.put("southExit",south);
        }
        if(west != null){
            exits.put("westExit",west);
        }
        if(southeast != null){
            exits.put("southeastExit",southeast);
        }
        if(northwest != null){
            exits.put("northwestExit",northwest);
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    public Room getExit ( String direction){
        return exits.get(direction);
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString(){
        String exits = "exits: ";
        Iterator it = this.exits.keySet().iterator();
        while(it.hasNext()){
            exits = exits + (it.next()) + " ";
        }
        return exits;
    }

    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String ListadoObjetos = "";
        for(Objeto objeto:objetos)
        {
            ListadoObjetos = ListadoObjetos + objeto.toString();
        }
        return "Tu estas " + description + "\nObjetos de la sala: " + ListadoObjetos + "\n" + getExitString();
    }

    /**
     * Comprueba si existe el item y devuelve true si existe
     */
    public boolean existeItem(String nombre)
    {
        boolean existe = false;
        int cont = 0;
        while(cont<objetos.size())
        {
            if(objetos.get(cont).getNomObj().equals(nombre))
            {
                existe = true;
                cont = objetos.size();
            }
            cont++;
        }
        return existe;
    }

    /**
     * añade un objeto al jugador si es posible, devulve true si lo a añadido
     */
    public Objeto cojerItem(String nObjeto)
    {
        Objeto oDevolver = null;
        int cont = 0;
        while(cont<objetos.size())
        {
            if(objetos.get(cont).getNomObj().equals(nObjeto))
            {
                oDevolver = objetos.get(cont);
                objetos.remove(cont);
            }
            cont++;
        }
        return oDevolver;
    }
    
    /**
     * Añade n objeto ya existente a la sala
     */
    public void moveItem(Objeto item)
    {
        objetos.add(item);
    }
}

