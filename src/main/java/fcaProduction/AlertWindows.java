package fcaProduction;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class AlertWindows {
	
	public static void errorDialog(String windowTitle, String message, String kontrolka) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(windowTitle);
		alert.setHeaderText("Wyst¹pi³ b³¹d w polu: " +kontrolka);
		alert.setContentText(message);
		
		alert.showAndWait();
	}
	
	public static void infoDialog(String windowTitle, String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(windowTitle);
		alert.setContentText(message);
		
		alert.showAndWait();
	}
	
	public static void dialogWindowReturnedRowsCounter(String windowTitle, String message, int counter) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(windowTitle);
		alert.setContentText(message +" "+counter);
		
		alert.showAndWait();
	}
	

}