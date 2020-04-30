package fcaProduction;



import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.io.File;
import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;


public class ControllerMainView {
	
//	@FXML
//	public StackPane mainStackPane;
	
	@FXML
	public TreeView<String> treeViewMenu;
	
	@FXML
	public StackPane stackPaneExchengableContent;
	
	public ControllerMainView() {
		// konstruktor
	}

	@SuppressWarnings("unchecked")
	@FXML
	public void initialize() {
		TreeItem<String> gswFCA = new TreeItem<String>("GSW FCA");
		gswFCA.setExpanded(true);
//		System.out.println("dupa kontroler");
		
		/*	- FCA production results
		 * 		- find according lot/date/machine/ circuit / operator
		 * 		- process sheet list // z mozliwoscia reprint
		 * 		- prepare process sheet
		 * 	- workers id card management
		 * 		- add remove card (worker)
		 *  - cos z gotowym producktem management	
		 * 
		 * 
		 * 
		 * 
		 */
		
		TreeItem<String> wiresForFca = new TreeItem<String>("Wires for FCA");
		TreeItem<String> prepareNewProcessSheet = new TreeItem<String>("Operation reporting");
		TreeItem<String> processSheetList = new TreeItem<String>("Process sheets list");
		TreeItem<String> operationHistory = new TreeItem<String>("Check operation history");
		
		wiresForFca.getChildren().addAll(prepareNewProcessSheet, processSheetList, operationHistory);
		wiresForFca.setExpanded(true);
		
		gswFCA.getChildren().addAll(wiresForFca);
		treeViewMenu.setRoot(gswFCA);
		
		
		
		
		//ustawianie listenera dla treeview
		treeViewMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,
					TreeItem<String> newValue) {
			
				// . skopiowane ze stacka nawet nie wiem o co kaman
				// https://stackoverflow.com/questions/13857041/tree-item-select-event-in-javafx2#13860457
				TreeItem<String> selectedItem = newValue;
				// System.out.println("Selected Text : " + selectedItem.getValue());
				
				
				if (selectedItem.getValue() == "Operation reporting") {
					try {
						System.out.println("Operation reporting");
						setCenter(Paths.reportOperation);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}	

				
				if (selectedItem.getValue() == "Process sheets list") {
					try {
						System.out.println("Process sheets list");
						setCenter(Paths.processSheetsList);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				if (selectedItem.getValue() == "Check operation history") {
					try {
						System.out.println("Check operation history");
						setCenter(Paths.checkOperationHistory);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
			
		});
	}
	

	
	public void setCenter(String fxmlPath) throws IOException {
//		FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
//		Pane root = FXMLLoader.load(getClass().getResource(fxmlPath));
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource(fxmlPath));
		StackPane stackPane = null;
		try {
			stackPane = loader.load();
//			stackPane = ((FXMLLoader) root).load();
		} catch (IOException e) {
			System.out.println("NIE WCZYTANO STACKPANE  w maincontrolerze");
		
			e.printStackTrace();
		}
		stackPaneExchengableContent.getChildren().clear();
		stackPaneExchengableContent.getChildren().add(stackPane);
	

	}
}


