package fcaProduction;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class MainClass extends Application{

	@Override
	public void start( Stage stage) throws Exception {
		Pane root = FXMLLoader.load(getClass().getResource( Paths.mainFxml));
		Scene scene = new Scene(root);
		stage.setScene(scene);
//		setUserAgentStylesheet(STYLESHEET_CASPIAN);
		stage.setTitle("Hello Kyungshin");
		stage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}
}