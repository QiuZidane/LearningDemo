package demo;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;

public class TextEditor_controller {
	
	@FXML
	private MenuItem openitem1;
	
	@FXML
	public void clickopen1() throws IOException{
		System.out.println("点击了openitem1");
	}
	

	
}
