
/**
 * Write a description of class Objeto here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Objeto
{
   private String objeto;
   private float pesoObjeto;
   
   public Objeto (String objeto, float pesoObjeto)
   {
       this.objeto= objeto;
       this.pesoObjeto = pesoObjeto;
   }
   
   public String getObjeto()
   {
     return objeto;  
   }
   
   public float getPesoObjeto()
   {
       return pesoObjeto;
   }
   
   public String toString()
   {
       return "Objeto de la sala :" + getObjeto() +"\n" + " Peso: " + getPesoObjeto() + "Kg";
    }
}
