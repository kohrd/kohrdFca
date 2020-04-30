package fcaProduction;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Methods {

	public static ObservableList<String> addDataToCombobox(String tableName, String columnName, String dbPathLocal) {

		final ObservableList<String> options = FXCollections.observableArrayList();
		String query = "SELECT " + columnName + " FROM " + tableName + " ORDER BY " + columnName;

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPathLocal);
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				String modelName = resultSet.getString(columnName);
				options.add(modelName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return options;
	}

	public static ObservableList<String> addDataToComboboxWorkerNameDistinct(String tableName, String columnName,
			String dbPathLocal) {

		final ObservableList<String> options = FXCollections.observableArrayList();
		String query = "SELECT  DISTINCT " + columnName + " FROM " + tableName + " WHERE VALID='1'  ORDER BY "
				+ columnName;

		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPathLocal);
			Statement stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);
			while (resultSet.next()) {
				String modelName = resultSet.getString(columnName);
				options.add(modelName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return options;

	}

	public static void exportToExcelFile(ArrayList<Integer> rowsIdFromTableView, String dbPath, File file) {
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		} catch (SQLException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("inventory");

		HSSFRow wierszNaglowek = sheet.createRow((short) 0);
		wierszNaglowek.createCell((short) 0).setCellValue("KEY");
		wierszNaglowek.createCell((short) 1).setCellValue("BOX_CODE");
		wierszNaglowek.createCell((short) 2).setCellValue("DATE");
		wierszNaglowek.createCell((short) 3).setCellValue("TIME");
		wierszNaglowek.createCell((short) 4).setCellValue("OPERATOR");
		wierszNaglowek.createCell((short) 5).setCellValue("AMOUNT");
		wierszNaglowek.createCell((short) 6).setCellValue("LOT");
		wierszNaglowek.createCell((short) 7).setCellValue("CIRCUIT");
		wierszNaglowek.createCell((short) 8).setCellValue("MACHINE");

		String querString = "SELECT * FROM LOG WHERE KEY = ?";

		int rowCounter = 1;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			for (Integer id : rowsIdFromTableView) {
				pstmt = connection.prepareStatement(querString);
				pstmt.setInt(1, id);
				rs = pstmt.executeQuery();

				while (rs.next()) {
					int keyFromDb = rs.getInt("KEY");
					String boxCodeFromDb = rs.getString("BOX_CODE");
					String dateFromDb = rs.getString("DATE");
					String timeFromDb = rs.getString("TIME");
					String operatorFromDb = rs.getString("OPERATOR");
					String amountFromDb = rs.getString("AMOUNT");
					String lotFromDb = rs.getString("LOT");
					String sub_pnoFromDb = rs.getString("SUB_PNO");
					String machineFromDb = rs.getString("MACHINE");

					HSSFRow wierszExcel = sheet.createRow((short) rowCounter);

					wierszExcel.createCell((short) 0).setCellValue(keyFromDb);
					wierszExcel.createCell((short) 1).setCellValue(boxCodeFromDb);
					wierszExcel.createCell((short) 2).setCellValue(dateFromDb);
					wierszExcel.createCell((short) 3).setCellValue(timeFromDb);
					wierszExcel.createCell((short) 4).setCellValue(operatorFromDb);
					wierszExcel.createCell((short) 5).setCellValue(amountFromDb);
					wierszExcel.createCell((short) 6).setCellValue(lotFromDb);
					wierszExcel.createCell((short) 7).setCellValue(sub_pnoFromDb);
					wierszExcel.createCell((short) 8).setCellValue(machineFromDb);

					rowCounter = rowCounter + 1;

				}
			}
			rs.close();
			pstmt.close();
			connection.close();

			try {
				wb.write(file);
				wb.close();
//				wb.notify();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static ArrayList<String> getCurrentDateAndTime() {
		ArrayList<String> dateList = new ArrayList<String>();
		String dateStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		String timeStamp = new SimpleDateFormat("HH_mm_ss").format(Calendar.getInstance().getTime());
		dateList.add(0, dateStamp);
		dateList.add(1, timeStamp);
		return dateList;
	}

}
