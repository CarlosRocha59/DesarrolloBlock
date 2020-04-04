package Service;



import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Carlos
 */

public class Archivos 
{ 
    public String leer(String direccion) throws FileNotFoundException, IOException
    {
      BufferedReader bf = new BufferedReader(new FileReader(direccion));
      String datos="";
      String bfRead="";
   
      while((bfRead=bf.readLine())!=null)
      {
       datos= datos + bfRead;
      }
      
      return datos;
  }
  
}
    


