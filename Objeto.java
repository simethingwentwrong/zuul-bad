
/**
 * Write a description of class Objeto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Objeto
{
    // instance variables - replace the example below with your own
    private String nomObj;
    private float peso;
    private boolean esFijo;

    /**
     * Constructor for objects of class Objeto
     */
    public Objeto(String nombre, float peso, boolean fijo)
    {
        nomObj = nombre;
        this.peso = peso;
        esFijo = fijo;
    }
    
    /**
     * Devuelve el nombre del objeto
     */
    public String getNomObj()
    {
        return nomObj;
    }
    
    /**
     * Devuelve el peso del objeto
     */
    public float getPeso()
    {
        return peso;
    }
    
    /**
     * Devuelve un String con el nombre del objeto y su peso
     */
    public String toString()
    {
        String anclado = "No";
        if (esFijo)
        {
            anclado = "Si";
        }
        return "Nombre: " + nomObj + " Peso: " + peso + "Kg " + "Esta anclado al escenario: " + anclado + "\n" ;
    }
    
    public boolean getFijo()
    {
        return esFijo;
    }
}
