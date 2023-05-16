package nathan.gioGuess;
import javafx.geometry.Point3D;
import javafx.scene.shape.*;
import java.lang.Math;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;  
public class Country extends Object{
  private double longitude;
  private double latitude;
  private String  name;
  private static HashMap<String,Double[]> Countrys = new HashMap<String, Double[]>();
  private static ArrayList<String> names = new ArrayList<>();
  static{

    Scanner in = null;
   try {
     in = new Scanner(new File("src/main/java/nathan/geoGuess/countries.csv"));
   } catch(Exception e) {
     throw new RuntimeException("counrtries file not found");
   }
   //name = in.nextLine();
while(in.hasNextLine()){ 


  double x = in.nextDouble();
  //System.out.println(x+" ");
  double y = in.nextDouble();
  //System.out.println(y+" ");
  String n = in.nextLine().trim();
  names.add(n);
  n=n.trim();
  //System.out.println(n+"");
  

  Double[] loc = new Double[2];
  loc[0] = x;
  loc[1] = y;
  //System.out.println(Arrays.toString(loc)+"");
  Countrys.put(n,loc);  
  } 




  }

  public HashMap<String,Double[]> getData()
    {return Countrys;} 

  public ArrayList<String> list(){
     return names;
     }

  public static double distance(Country a1, Country b1) {
    // System.out.println("***"+Countrys.get(a1.getName())+"");
    // System.out.println(Countrys.get(b1.getName())+"");
    boolean pop = true;
    Double[] c1 = new Double[2];
    Double[] d1 = new Double[2];

    Double[] temp = Countrys.get(a1.getName());
    Double[] temp2 = Countrys.get(b1.getName());
    c1[0] = temp[0];
    c1[1] = temp[1];
    d1[0] = temp2[0];
    d1[1] = temp2[1];


    if(pop == true){
    double lat1 = c1[0];
    double lat2 = d1[0];
    double lon1 = c1[1];
    double lon2 = d1[1];
    final int R = 6371;
    double latDistance = Math.toRadians(lat2 - lat1);
    double lonDistance = Math.toRadians(lon2 - lon1);
    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
    double distance = R * c * 1000; 
    distance = distance/1000l;
    double height = 0 - 0;

    distance = Math.pow(distance, 2) + Math.pow(height, 2);

    return Math.sqrt(distance);
    }
    else{return 0.0;}
}
 


  public Country(String cname){
   //name = countryname;
   //Scanner in = new Scanner(Country.class.getResourceAsStream("countries.csv"));
    name = cname;

  
  }
  public String getName(){
    return name;
  }
  
  public static Point3D llarToWorld( Double lat, Double lon,Double alt, Double rad){
   
    int f  = 0;                             // # flattening
    double  ls = Math.atan((1 - f)* Math.tan(lat));      // # lambda

    double  x = rad * Math.cos(ls) * Math.cos(lon) + alt * Math.cos(lat) * Math.cos(lon);
    double y = rad * Math.cos(ls) * Math.sin(lon) + alt * Math.cos(lat) * Math.sin(lon);
    double  z = rad * Math.sin(ls) + alt * Math.sin(lat);
    Point3D p = new Point3D(x, y, z);
    return p;
  }






  public static double xangleDis(Country a1){
    Double[] temp = Countrys.get(a1.getName());
    double xangle = temp[0];
    return xangle;
  }
  public static double yangleDis(Country a1){
    Double[] temp = Countrys.get(a1.getName());
    double yangle = temp[1];
    return yangle;
  }
  
  }
 
 


  
  
  
  
  

  
