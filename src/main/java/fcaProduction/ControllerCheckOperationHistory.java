package fcaProduction;

//import java.awt.Desktop;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.ss.formula.ptg.Ptg;

import javafx.stage.FileChooser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
//import javafx.beans.value.ChangeListener;
//import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

public class ControllerCheckOperationHistory {

//	@SuppressWarnings("exports")
//	@FXML
//	public StackPane jakisStackPane;

	@FXML
	public DatePicker operationDateFrom;
	@FXML
	public DatePicker operationDateTo;
	@FXML
	public ComboBox<String> comboBoxOperatorName;
	@FXML
	public TextField textFieldLotNumber;
	@FXML
	public ComboBox<String> comboBoxMachine;
	@FXML
	public TextField textFieldBoxCode;
	@FXML
	public ComboBox<String> comboBoxCircuitName;
	@FXML
	public Button buttonExportDataToExcelFile;

	
	@FXML
	public TableView<FxPropertyCheckOperationHistory> tabOperationHistory;
	@FXML
	public TableColumn<FxPropertyCheckOperationHistory, Integer> columnNameDatabaseKey;
	@FXML
	public TableColumn<FxPropertyCheckOperationHistory, String> columnNameBoxCode;
	@FXML
	public TableColumn<FxPropertyCheckOperationHistory, String> columnNameDate;
	@FXML
	public TableColumn<FxPropertyCheckOperationHistory, String> columnNameTime;
	@FXML
	public TableColumn<FxPropertyCheckOperationHistory, String> columnNameOperator;
	@FXML
	public TableColumn<FxPropertyCheckOperationHistory, String> columnNameAmount;
	@FXML
	public TableColumn<FxPropertyCheckOperationHistory, String> columnNameLot;
	@FXML
	public TableColumn<FxPropertyCheckOperationHistory, String> columnNameCircuit;
	@FXML
	public TableColumn<FxPropertyCheckOperationHistory, String> columnNameMachine;

	public ControllerCheckOperationHistory() {
		// TODO Auto-generated constructor stu
	}

	@FXML
	public void initialize() {

		// ustawianie daty datePicker
		operationDateFrom.setValue(LocalDate.now());
		operationDateTo.setValue(LocalDate.now());

		// dodanie danych do comboBox worker list
		ObservableList<String> workerListForComboBox = Methods.addDataToComboboxWorkerNameDistinct("WORKER_ID_TABLE",
				"NAME_SURNAME", Paths.dbFca);
		comboBoxOperatorName.setItems(workerListForComboBox);

		// ustawienie danych combobox machine
		ObservableList<String> machineListForComboBox = Methods.addDataToCombobox("MACHINE_ID_TABLE", "BARCODE_TEXT",
				Paths.dbFca);
		comboBoxMachine.setItems(machineListForComboBox);

		// ustawienie danych combobox circuit
		ObservableList<String> circuitListForComboBox = Methods.addDataToCombobox("CIRCUIT_NAMES_TABLE",
				"CIRCUIT_NAME", Paths.dbFca);
		comboBoxCircuitName.setItems(circuitListForComboBox);

	}

	public void actionButtonCheckOperationHistory() {
		// czyszczenie tableview
//		tabOperationHistory.toString()
		tabOperationHistory.getItems().clear();
		fillTableViewWithDataFromDatabaseWithGivenQuery(prepareQueryForDb());

		System.out.println(prepareQueryForDb());
		;

	}

	public void functionExportDataToExcelFile() {
		System.out.println("functionExportDataToExcelFile");
		exportFromTableViewToExcelFile();

	}

	public String[] sliceStringWith_separtor(String stringToSlice) {
		String[] slicedStringArray = stringToSlice.split("_");
		return slicedStringArray;

	}

	public String prepareQueryForDb() {
		// przygotwuje zapytnie do bazy na podstawie pobranych z GUI informacji
		// sprawdza ktore dane sa podane przez uzytkownia i dopasowauje do tego query
		// niektore dane sa niedopasowane do wartosci w db i tutaj uzywam StringBuilder
		String fullQuery = "";
		String sqlQuery = "SELECT * FROM LOG WHERE KEY > 0 ";
		StringBuilder sqlStringBuilder = new StringBuilder();
		sqlStringBuilder.append(sqlQuery);

		// pobieranie OPERATOR NAME Z COMBOBOX
		String operatorNameFromGUI = comboBoxOperatorName.getValue();
		if (operatorNameFromGUI != null) {
			String operatorNameForQuery = operatorNameFromGUI;
			sqlStringBuilder.append(" AND OPERATOR = ");
			sqlStringBuilder.append("'").append(operatorNameForQuery).append("'");

		}

		// pobieranie MACHINE NAME Z COMBOBOX
		String machineFromGUI = comboBoxMachine.getValue(); // zwraca warotsoc inna niz sa w db trzeba zrobic slice
		if (machineFromGUI != null) {
			String machineForQuery1 = sliceStringWith_separtor(machineFromGUI)[1];
			String machineForQuery2 = sliceStringWith_separtor(machineFromGUI)[2];
			String machineForQuery = new StringBuilder().append(machineForQuery1).append("_").append(machineForQuery2)
					.toString();
			sqlStringBuilder.append(" AND MACHINE = ");
			sqlStringBuilder.append("'").append(machineForQuery).append("'");
		}

		// pobieranie circuit NAME Z COMBOBOX
		String circuitNameFromGUI = comboBoxCircuitName.getValue();
		if (circuitNameFromGUI != null) {
			String circuitNameForQuery = sliceStringWith_separtor(circuitNameFromGUI)[0];
			sqlStringBuilder.append(" AND SUB_PNO = ");
			sqlStringBuilder.append("'").append(circuitNameForQuery).append("'");
		}

		String boxCodeFromGUI = textFieldBoxCode.getText();
		if (boxCodeFromGUI.length() > 1) {
			String boxCodeForQuery = boxCodeFromGUI;
			sqlStringBuilder.append(" AND BOX_CODE = ");
			sqlStringBuilder.append("'").append(boxCodeForQuery).append("'");
		}

		String lotNumberFromGUI = textFieldLotNumber.getText();
		if (lotNumberFromGUI.length() == 8) {
			String lotNumberForQuery = lotNumberFromGUI;
			sqlStringBuilder.append(" AND LOT = ");
			sqlStringBuilder.append("'").append(lotNumberForQuery).append("'");
		}

		String operationDateFromDateFromGUI = operationDateFrom.getValue().toString();
		String operationDateToDateFromGUI = operationDateTo.getValue().toString();
		sqlStringBuilder.append(" AND DATE BETWEEN '");
		sqlStringBuilder.append(operationDateFromDateFromGUI);
		sqlStringBuilder.append("'");
		sqlStringBuilder.append(" AND '");
		sqlStringBuilder.append(operationDateToDateFromGUI);
		sqlStringBuilder.append("'");

		fullQuery = sqlStringBuilder.toString();

		return fullQuery;
	}

	public void fillTableViewWithDataFromDatabaseWithGivenQuery(String query) {
		// wype³ania table view danymi z bazy danych na podstawie zapytania przekazanego
		// jako parametr
		assert tabOperationHistory != null;
		int returnedRowsCounterForDialogWindow = 0;

		columnNameDatabaseKey
				.setCellValueFactory(new PropertyValueFactory<FxPropertyCheckOperationHistory, Integer>("databaseKey"));
		columnNameBoxCode
				.setCellValueFactory(new PropertyValueFactory<FxPropertyCheckOperationHistory, String>("boxCode"));
		columnNameDate.setCellValueFactory(new PropertyValueFactory<FxPropertyCheckOperationHistory, String>("date"));
		columnNameTime.setCellValueFactory(new PropertyValueFactory<FxPropertyCheckOperationHistory, String>("time"));
		columnNameOperator
				.setCellValueFactory(new PropertyValueFactory<FxPropertyCheckOperationHistory, String>("operatorName"));
		columnNameAmount
				.setCellValueFactory(new PropertyValueFactory<FxPropertyCheckOperationHistory, String>("amount"));
		columnNameLot.setCellValueFactory(new PropertyValueFactory<FxPropertyCheckOperationHistory, String>("lot"));
		columnNameCircuit
				.setCellValueFactory(new PropertyValueFactory<FxPropertyCheckOperationHistory, String>("circuitName"));
		columnNameMachine
				.setCellValueFactory(new PropertyValueFactory<FxPropertyCheckOperationHistory, String>("machine"));

		ObservableList<FxPropertyCheckOperationHistory> temporaryArrayListForDbDataForEachIteration = FXCollections
				.observableArrayList();
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + Paths.dbFca);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			PreparedStatement pstmt = connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				FxPropertyCheckOperationHistory fxPropertyCheckOperationHistoryTemp = new FxPropertyCheckOperationHistory();

				fxPropertyCheckOperationHistoryTemp.databaseKey.set(rs.getInt("KEY"));
				fxPropertyCheckOperationHistoryTemp.boxCode.set(rs.getString("BOX_CODE"));
				fxPropertyCheckOperationHistoryTemp.date.set(rs.getString("DATE"));
				fxPropertyCheckOperationHistoryTemp.time.set(rs.getString("TIME"));
				fxPropertyCheckOperationHistoryTemp.operatorName.set(rs.getString("OPERATOR"));
				fxPropertyCheckOperationHistoryTemp.amount.set(rs.getString("AMOUNT"));
				fxPropertyCheckOperationHistoryTemp.lot.set(rs.getString("LOT"));
				fxPropertyCheckOperationHistoryTemp.circuitName.set(rs.getString("SUB_PNO"));
				fxPropertyCheckOperationHistoryTemp.machine.set(rs.getString("MACHINE"));

				temporaryArrayListForDbDataForEachIteration.add(fxPropertyCheckOperationHistoryTemp);
				fxPropertyCheckOperationHistoryTemp = null;

				returnedRowsCounterForDialogWindow = returnedRowsCounterForDialogWindow + 1;

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		AlertWindows.dialogWindowReturnedRowsCounter("Result", "Returned number of rows",
				returnedRowsCounterForDialogWindow);
		tabOperationHistory.setItems(temporaryArrayListForDbDataForEachIteration);
	}

	public void exportFromTableViewToExcelFile() {
		ArrayList<Integer> tableViewItemIdArrayList = new ArrayList<>();
		ObservableList<FxPropertyCheckOperationHistory> dupa = tabOperationHistory.getItems(); // pobiera liste
																								// elementow w tableView
		// aktualnie wyœwietlanych
		for (FxPropertyCheckOperationHistory fxPropertyCheckOperationHistory : dupa) {
			int idFromTableView = fxPropertyCheckOperationHistory.getDatabaseKey();
			tableViewItemIdArrayList.add(idFromTableView);
		}

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Choose file location");
		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XLS files (*.xls)", "*.xls");
		fileChooser.getExtensionFilters().add(extFilter);
		ArrayList<String> dateList = Methods.getCurrentDateAndTime();
		String currentDate = dateList.get(0);
		String currentTime = dateList.get(1);
		fileChooser.setInitialFileName("fcaOperationLog_" + currentDate + "_" + currentTime + ".xls");
		File reportFile = fileChooser.showSaveDialog(null);

		if (reportFile != null) {
			try {

				Methods.exportToExcelFile(tableViewItemIdArrayList, Paths.dbFca, reportFile);
		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
