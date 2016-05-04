import java.util.Stack;
import java.util.ArrayList;
/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player
{
    // instance variables - replace the example below with your own
    private Room localizacionActual;
    private static final float pesoM = 3;
    private ArrayList<Objeto> items;
    private float pesoAc;
    private Stack<Room> back;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room inicio)
    {
        localizacionActual = inicio;
        items = new ArrayList<>();
        pesoAc = 0;
        back = new Stack<>();
    }

   /**
     * Devuelve la localizacion actual
     */
    public Room getLocalizacionActual()
    {
        return localizacionActual;
    }
    
   /**
     * Fija la localizacion del jugador
     */
    public void setLocalizacion(Room localizacion)
    {
        localizacionActual = localizacion;
    }
    
    /**
     * añade un objeto al jugador si es posible, devulve true si lo a añadido
     */
    public boolean cojerItem(Objeto item)
    {
        boolean cojido = false;
        if (item.getPeso()+pesoAc <= pesoM && !item.getFijo())
        {
            items.add(item);
            cojido = true;
        }
        else
        {
            System.out.println("No han podido cojer el objeto porque supera tu peso maximo o esta fijado al escenario");
        }
        return cojido;
    }
    
    /**
     * deja un objeto del jugador en el escenario, se quita del inventario del jugador
     */
    public Objeto dejarItem(String nombre)
    {
        Objeto oDevolver = null;
        int cont = 0;
        while(cont<items.size())
        {
            if(items.get(cont).getNomObj().equals(nombre))
            {
                oDevolver = items.get(cont);
                items.remove(cont);
                cont = items.size();
            }
            cont++;
        }
        return oDevolver;
    }
    
    /**
     * Devuelve un string con los items que tiene el jugador.
     */
    public String itemEncima()
    {
        String itemsE = "Items del jugador:\n";
        for(Objeto item : items)
        {
            itemsE = itemsE + item.toString();
        }
        return itemsE;
    }
    
    /**
     * Comprueba si existe el item y devuelve true si existe
     */
    public boolean existeItem(String nombre)
    {
        boolean existe = false;
        int cont = 0;
        while(cont<items.size())
        {
            if(items.get(cont).getNomObj().equals(nombre))
            {
                existe = true;
                cont = items.size();
            }
            cont++;
        }
        return existe;
    }
    
    public void volver()
    {
        if (!back.empty())
            {
                setLocalizacion(back.pop());
                System.out.print(localizacionActual.getLongDescription());
                System.out.println();
            }
            else
            {
                System.out.println("No hay localizacion anterior");
            }
    }
    
    public void addback()
    {
        back.push(localizacionActual);
    }
}


