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

public class ControllerReportOperation {

//	@SuppressWarnings("exports")
//	@FXML
//	public StackPane jakisStackPane;


	@FXML
	public TextField textFieldMachine; 
	@FXML
	public TextField textFieldOperatorName;
	@FXML
	public TextField textFieldBoxCode;
	@FXML
	public Button btnAcceptOperation;

	
	@FXML
	public TableView<FxPropertyCheckOperationHistory> tabReportOpertion;
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

	public ControllerReportOperation() {
		// TODO Auto-generated constructor stu
	}

	@FXML
	public void initialize() {


	

	}

	public void actionButtonAcceptOperation() {
		// czyszczenie tableview
//		tabOperationHistory.toString()
		System.out.println(prepareQueryForDb());
		tabReportOpertion.getItems().clear();
		fillTableViewWithDataFromDatabaseWithGivenQuery(prepareQueryForDb());
		
		// tworzymy nowy obiekt klasy zeby nadac mu pola setterami i dodac do bazy, na koniec obiekt bedzie niszczony
		objectClassReportOperation objectClassReportOperation = new objectClassReportOperation();
		
		//pobieramy date i czas do pola objectClassReportOperation
		objectClassReportOperation.setDate(Methods.getCurrentDateAndTime().get(0));
		objectClassReportOperation.setTime(Methods.getCurrentDateAndTime().get(1));
//		przydalaby sie walidacja tym bardziej ze kod na karcie zaczyna sie od Operator_
		objectClassReportOperation.setOperator(textFieldOperatorName.getText());   
		objectClassReportOperation.setAmount(0);
		objectClassReportOperation.setLot("TEMP_LOT"); // pobieranie z boxCode - nowa metoda
		objectClassReportOperation.setSubPno("TEMP SUBPNO"); // pobieranie z boxCode - nowa metoda
//		przydalaby sie walidacja tym bardziej ze kod na karcie zaczyna sie od Machine_
		objectClassReportOperation.setLot(textFieldMachine.getText());
		
		// dodanie do bazy
		objectClassReportOperation.addObjectClassReportOperationToDb(Paths.dbFca);
		System.out.println(objectClassReportOperation.toString());
		objectClassReportOperation = null;

	}
 
	public String[] sliceStringWith_separtor(String stringToSlice) {
		String[] slicedStringArray = stringToSlice.split("_");
		return slicedStringArray;

	}

	public String prepareQueryForDb() {
		String currentDate = Methods.getCurrentDateAndTime().get(0);
		// przygotwuje zapytnie do bazy na podstawie pobranych z GUI informacji
		// sprawdza ktore dane sa podane przez uzytkownia i dopasowauje do tego query
		// niektore dane sa niedopasowane do wartosci w db i tutaj uzywam StringBuilder
		System.out.println("current date "+ currentDate);
		String fullQuery = "";
		String sqlQuery = "SELECT * FROM LOG WHERE DATE = '";
		StringBuilder sqlStringBuilder = new StringBuilder();
		sqlStringBuilder.append(sqlQuery);
		sqlStringBuilder.append(currentDate);
		sqlStringBuilder.append("' ORDER BY TIME DESC");



		fullQuery = sqlStringBuilder.toString();
		System.out.println(fullQuery);

		return fullQuery;
	}

	public void fillTableViewWithDataFromDatabaseWithGivenQuery(String query) {
		// wype³ania table view danymi z bazy danych na podstawie zapytania przekazanego
		// jako parametr
		assert tabReportOpertion != null;
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
		
		tabReportOpertion.setItems(temporaryArrayListForDbDataForEachIteration);
	}

	
}
