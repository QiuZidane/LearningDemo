package application;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class Main extends Application {
    public static final ObservableList<String> names = FXCollections.observableArrayList();  
    public static final ObservableList<String> data = FXCollections.observableArrayList(); 
	@Override
	public void start(Stage primaryStage) {
		// try {
		// BorderPane root = new BorderPane();
		// Scene scene = new Scene(root,400,400);
		// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// primaryStage.setScene(scene);
		// primaryStage.show();
		// } catch(Exception e) {
		// e.printStackTrace();
		// }
		primaryStage.setTitle("List View Sample");  
		  
        final ListView<String> listView = new ListView<>(data);  
        listView.setPrefSize(200, 250);  
        listView.setEditable(true);  
  
        names.addAll("Adam", "Alex", "Alfred", "Albert", "Brenda", "Connie",  
                "Derek", "Donny", "Lynne", "Myrtle", "Rose", "Rudolph", "Tony",  
                "Trudy", "Williams", "Zach");  
  
        for (int i = 0; i < 10000; i++) {  
            data.add(i+" : anony 232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623243652436542356232436524365423562324365243654235623");  
        }  
  
        listView.setItems(data);  
        listView.setCellFactory(ComboBoxListCell.forListView(names));  
  
        StackPane root = new StackPane();  
        root.getChildren().add(listView);  
  
        primaryStage.setScene(new Scene(root, 1200, 550));  
        primaryStage.show(); 
	}

	public static void main(String[] args) {
		launch(args);
	}
}
