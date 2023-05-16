package nathan.gioGuess;


import javafx.beans.property.SimpleStringProperty;

public class FileData {
   SimpleStringProperty fileName;
   SimpleStringProperty dist2;
   SimpleStringProperty size;
   SimpleStringProperty dateModified;
   SimpleStringProperty direction;
   FileData(String fileName, String dist2) {
      this.fileName = new SimpleStringProperty(fileName);
      this.dist2 = new SimpleStringProperty(dist2);
      
   }
   public String getFileName(){
      return fileName.get();
   }
   public void setFileName(String fname){
      fileName.set(fname);
   }

   public String getDistance(){
      return dist2.get();
   }
   public void setDistance(String fname){
      dist2.set(fname);
   }


}