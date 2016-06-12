import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Dao {

  public List<Hotel> getData() {
    ArrayList<Hotel> data = new ArrayList<Hotel>();
    FileReader fileReader = null;
    BufferedReader bufferedReader = null;    
    try {    	
//      File f = new File("../res/hoteldb.csv");
//      System.out.println(f.toString());
            
      fileReader = new FileReader("D:\\workspace\\MyHotel\\res\\hoteldb.csv");      
      bufferedReader = new BufferedReader(fileReader);
      bufferedReader.readLine();
      String line;
      while( (line = bufferedReader.readLine()) != null ){
    	  String[] att = line.split(",");
    	  data.add(new Hotel(att[0],Integer.parseInt(att[1]),att[2],Integer.parseInt(att[3])));
      }            
    } catch (Exception ex) {
            
    }
    if (bufferedReader != null) {
      try {
        bufferedReader.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return data;
  }
} 
