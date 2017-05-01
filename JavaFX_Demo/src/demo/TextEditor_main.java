package demo;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TextEditor_main extends Application {

	public static void main(String[] args) {
		Application.launch(TextEditor_main.class, args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		// 次次节点
		Parent root = FXMLLoader.load(this.getClass().getResource("TextEditor_ui.fxml"));
		
		// 次节点
		Scene scene = new Scene(root);
		
		// 顶节点
		Stage stage = new Stage();
		stage.initStyle(StageStyle.DECORATED);
		stage.setScene(scene);
		stage.setTitle("JAVAFX_TextEditorDemo");
		stage.show();
		
		
		
		
	}

}
