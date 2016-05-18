
/**
  * Enumeration class Option - write a description of the enum class here
  * 
  * @author (your name here)
  * @version (version number or date here)
  */
 public enum Option
 {
   GO("go"), QUIT("quit"), HELP("help"), LOOK("look"), EAT("eat"), BACK("back"), TAKE("take"), DROP("drop"), ITEMS("items"), UNKNOWN("UNKNOWN");
     
     private String nombreC;
     
     /**
      * Constructor - initialise the command words.
      */
     private Option(String nombreC)
     {
         this.nombreC = nombreC;
     }
     
    public String getNombreC()
     {
         return nombreC;
    }
 }