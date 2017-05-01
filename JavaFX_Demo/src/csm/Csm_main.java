package csm;

import java.io.File;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Csm_main extends Application {

	public static void main(String[] args) {
		Application.launch(Csm_main.class, args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		
		// 次次节点
		Parent root = FXMLLoader.load(this.getClass().getResource("CsmMain_ui.fxml"));
		
		// 次节点
		Scene scene = new Scene(root);
		
		// 顶节点
		Stage stage = new Stage();
		stage.initStyle(StageStyle.DECORATED);
		stage.setScene(scene);
		stage.setTitle("Csm");
		stage.show();
		
		
		
		
	}

}
