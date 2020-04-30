package fcaProduction;

import java.time.LocalDate;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ControllerProcessSheetsList {

//	public SimpleIntegerProperty id = new SimpleIntegerProperty();
//	public SimpleStringProperty date = new SimpleStringProperty();
//	public SimpleStringProperty time = new SimpleStringProperty();
//	public SimpleStringProperty lotNumber = new SimpleStringProperty();
//	public SimpleIntegerProperty amountInBox = new SimpleIntegerProperty();
//	public SimpleIntegerProperty boxesAmount = new SimpleIntegerProperty();
//	public SimpleStringProperty spcCode = new SimpleStringProperty();
//	public SimpleStringProperty alterCode = new SimpleStringProperty();
//	public SimpleStringProperty wireName = new SimpleStringProperty();
//	public SimpleStringProperty processSheetStatus = new SimpleStringProperty();
//	public SimpleIntegerProperty amountInLot = new SimpleIntegerProperty();
//	public SimpleStringProperty boxCode = new SimpleStringProperty();

	@FXML
	public DatePicker processSheetsListDateFrom;
	@FXML
	public DatePicker processSheetListDateTo;
	@FXML
	public TextField textFielProcessSheetsListLotNumber;
	@FXML
	public ComboBox<String> comboBoxProcessSheetsListCircuitName;
	@FXML
	public ComboBox<Integer> comboBoxProcessSheetsListStatus;
	@FXML
	public Button btnCheckProcessSheetsList;
	@FXML
	public Button buttonExportDataToExcelFile;

	@FXML
	public TableView<FxPropertyCheckProcessSheetsList> tabProcessSheetsList;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, Integer> id;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, String> date;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, String> time;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, String> lotNumber;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, Integer> amountInBox;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, Integer> boxesAmount;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, String> spcCode;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, String> alterCode;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, String> wireName;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, Integer> processSheetStatus;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, Integer> amountInLot;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, String> boxCode;
	@FXML
	public TableColumn<FxPropertyCheckProcessSheetsList, Integer> currentAmountInBox;

	public ControllerProcessSheetsList() {

	}

	@FXML
	public void initialize() {
		processSheetsListDateFrom.setValue(LocalDate.now());
		processSheetListDateTo.setValue(LocalDate.now());
		
		
		comboBoxProcessSheetsListStatus.getItems().addAll(0, 1, 2);

		// ustawienie danych combobox circuit
		ObservableList<String> circuitListForComboBox = Methods.addDataToCombobox("CIRCUIT_NAMES_TABLE", "CIRCUIT_NAME",
				Paths.dbFca);
		comboBoxProcessSheetsListCircuitName.setItems(circuitListForComboBox);

	}

	public void btnCheckProcessSheetsList() {
		System.out.println("btnCheckProcessSheetsList");
		System.out.println("textFielProcessSheetsListLotNumber :" + textFielProcessSheetsListLotNumber.getText());
		System.out.println("comboBoxProcessSheetsListCircuitName: " + comboBoxProcessSheetsListCircuitName.getValue());
		System.out.println("comboBoxProcessSheetsListStatus: " + comboBoxProcessSheetsListStatus.getValue());
		;

	}

	public void functionExportProcessSheetsListToExcelFile() {
		System.out.println("functionExportProcessSheetsListToExcelFile");
	}
}
