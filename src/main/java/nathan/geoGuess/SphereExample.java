package nathan.gioGuess;

import java.util.ArrayList;
import java.io.*;
import java.lang.Thread;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.PhongMaterial;
import javafx.application.Application;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;
import javafx.scene.shape.Sphere;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.control.SelectionMode;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.scene.layout.StackPane;
import javax.xml.datatype.Duration;

 

public class SphereExample extends Application {
   private double totangY = 0;
   private double totangX = 0;
   private static final int WIDTH = 1400;
   private static final int HEIGHT = 800;
  
   private double anchorX, anchorY;

   private double anchorAngleX = 0;
   private double anchorAngleY = 0;

   private final DoubleProperty angleX = new SimpleDoubleProperty(0);
   private final DoubleProperty angleY = new SimpleDoubleProperty(0);
   

  @Override
  public void start(Stage primaryStage) {
    System.out.println(totangX);
    System.out.println(totangX);
    
    //String s = test.getName();
    //console.log(s);
    //System.out.println(s+"");
    Sphere sphere = new Sphere(50);
   
    Country test = new Country(""); 
    ArrayList<String> cnamelist= test.list();
    int rannum = (int)(Math.random()* cnamelist.size())+1;
    String mycname = cnamelist.get(rannum);
    Country secret = new Country(mycname);
    System.out.println(secret.getName()+"");

    TableView<FileData> table = new TableView<FileData>();
    final ObservableList<FileData> data = FXCollections.observableArrayList(
         
        //  new FileData("file2", "yo", "30 MB", "01/11/2019"),
        //  new FileData("file3", "yo", "50 MB", "12/04/2017"),
        //  new FileData("file4", "yo", "75 MB", "25/09/2018")
      );

      TableColumn fileNameCol = new TableColumn("Country Name");
      fileNameCol.setCellValueFactory(new PropertyValueFactory<>("fileName"));
      TableColumn distance1 = new TableColumn("distance");
      distance1.setCellValueFactory(new PropertyValueFactory("distance"));
      

      ObservableList<String> list = FXCollections.observableArrayList();
      table.setItems(data);
      table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      table.getColumns().addAll(fileNameCol,distance1);
   

    PhongMaterial material = new PhongMaterial();
    material.setDiffuseMap(new Image(getClass().getResourceAsStream("earth.jpg")));
    sphere.setMaterial(material);
 
    TextField textField1= new TextField("");
    Label label1 = new Label("Name: ");
    Button button1 = new Button();
    SmartGroup group = new SmartGroup();
    SmartGroup group2 = new SmartGroup();
    group.getChildren().add(sphere);

    


    textField1.setLayoutX(WIDTH/2);
    textField1.setLayoutY(HEIGHT/2);
    label1.setLayoutX(WIDTH/2 -50);
    label1.setLayoutY(HEIGHT/2);

    

   
    final VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.setPadding(new Insets(0, 0, 0, 10));
    vbox.getChildren().addAll(table);
    HBox box = new HBox(5);
    box.setPadding(new Insets(HEIGHT/4, 5 , 5, WIDTH/2 -100));
    box.getChildren().addAll(label1, textField1, vbox);
    Camera camera = new PerspectiveCamera();
    Scene scene = new Scene(group, WIDTH, HEIGHT);
    Scene scene2 = new Scene(box, WIDTH, HEIGHT, Color.BEIGE);

    
    scene.setFill(Color.SILVER);
    scene.setCamera(camera);
 
    
    group.translateXProperty().set(WIDTH / 2);
    group.translateYProperty().set(HEIGHT / 2);
    group.translateZProperty().set(-1100);
   
  
 
    
    primaryStage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
      switch (event.getCode()) {
        case W:
          group.translateZProperty().set(group.getTranslateZ() + 100);
          break;
        case S:
          group.translateZProperty().set(group.getTranslateZ() - 100);
          break;
        case ESCAPE:
           primaryStage.setScene(scene);
           break;
        case R:
          group.resetXAng();
          group.resetYAng();
          
        case Q:
          group.rotateByY(-5.0);
          break;
        case E:
          group.rotateByY(5.0);
          break;
        case A:
          group.rotateByX(-5.0);
          break;
        case D:
          group.rotateByX(-5.0);
          break;
        case P:
          primaryStage.setScene(scene2);
          group.resetYAng();
          group.resetXAng();
          break;
        case ENTER:
          //primaryStage.setScene(scene);
           String guess = textField1.getText().trim();
           boolean correct = guess.equals(secret.getName().trim());
          if(test.getData().containsKey(guess) ) {
             if(correct == true){
                 textField1.setText("You Win!");
              }
              if(correct == false){
            Country guess1 = new Country(guess);
            double dist1= test.distance(secret,guess1);
            System.out.println(dist1+"");

            
            textField1.setText("Nope");
           
           delay(2000, () -> textField1.setText(""));
           //primaryStage.setScene(scene);
          // delay(2000, () ->  group.rotateByY(Country.yangleDis(guess1)) );
           //group.rotateByX(Country.xangleDis(guess1));
            
            

            FileData myg = new FileData(guess,(int)dist1+"km");
            data.add(myg);
            }
            }
            
            
           
            if((test.getData().containsKey(guess) == false)){
            textField1.setText("not a country");
            delay(2000, () -> textField1.setText(""));

           }

          break;
        
      }
    });
 
    primaryStage.setTitle("Globe Trotter");
    primaryStage.setScene(scene);
    //primaryStage.addScene(scene2);
    primaryStage.show();



    
  }
 
 
    public static void main(String[] args) {
      // Country test = new Country(); 
        
        launch(args);
      }
 
  class SmartGroup extends Group {
 
    Rotate r;
    Transform t = new Rotate();
 
    void rotateByX(double ang) {
      totangX += ang;
      r = new Rotate(ang, Rotate.Y_AXIS);
      t = t.createConcatenation(r);
      this.getTransforms().clear();
      this.getTransforms().addAll(t);
    }
 
    void rotateByY(double ang) {
      totangY += ang;
      r = new Rotate(ang, Rotate.X_AXIS);
      t = t.createConcatenation(r);
      this.getTransforms().clear();
      this.getTransforms().addAll(t);
    }
    void resetXAng(){
      double angX = -1* (totangX%360);
      if(totangX == 0){
        angX= 0;
      }
      r = new Rotate(angX, Rotate.Y_AXIS);
      t = t.createConcatenation(r);
      totangX = 0;
      this.getTransforms().clear();
      this.getTransforms().addAll(t);
    }
    void resetYAng(){
      double angY = -1*(totangY%360);
       if(totangY == 0){
        angY= 0;
      }
      r = new Rotate(angY, Rotate.X_AXIS);
      t = t.createConcatenation(r);
      totangY = 0;
      this.getTransforms().clear();
      this.getTransforms().addAll(t);
    }

  }

  public static void delay(long millis, Runnable continuation) {
      Task<Void> sleeper = new Task<Void>() {
          @Override
          protected Void call() throws Exception {
              try { Thread.sleep(millis); }
              catch (InterruptedException e) { }
              return null;
          }
      };
      sleeper.setOnSucceeded(event -> continuation.run());
      new Thread(sleeper).start();
    }
 
  



 



}