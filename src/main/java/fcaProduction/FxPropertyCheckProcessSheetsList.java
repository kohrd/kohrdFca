package fcaProduction;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FxPropertyCheckProcessSheetsList {
	
	
	public SimpleIntegerProperty id = new SimpleIntegerProperty();
	public SimpleStringProperty date = new SimpleStringProperty();
	public SimpleStringProperty time = new SimpleStringProperty();
	public SimpleStringProperty lotNumber = new SimpleStringProperty();
	public SimpleIntegerProperty amountInBox = new SimpleIntegerProperty();
	public SimpleIntegerProperty boxesAmount = new SimpleIntegerProperty();
	public SimpleStringProperty spcCode = new SimpleStringProperty();
	public SimpleStringProperty alterCode = new SimpleStringProperty();
	public SimpleStringProperty wireName = new SimpleStringProperty();
	public SimpleStringProperty processSheetStatus = new SimpleStringProperty();
	public SimpleIntegerProperty amountInLot = new SimpleIntegerProperty();
	public SimpleStringProperty boxCode = new SimpleStringProperty();
	
	
	
	public int getId() {
		return id.get();
	}

	public String getDate() {
		return date.get();
	}


	public String getTime() {
		return time.get();
	}

	public String getLotNumber() {
		return lotNumber.get();
	}

	public int getAmountInBox() {
		return amountInBox.get();
	}

	public int getBoxesAmount() {
		return boxesAmount.get();
	}

	public String getSpcCode() {
		return spcCode.get();
	}

	public String getAlterCode() {
		return alterCode.get();
	}

	public String getWireName() {
		return wireName.get();
	}

	public String getProcessSheetStatus() {
		return processSheetStatus.get();
	}

	public int getAmountInLot() {
		return amountInLot.get();
	}

	public String getBoxCode() {
		return boxCode.get();
	}

	@Override
	public String toString() {
		return "FxPropertyCheckProcessSheetsList [id=" + id + ", date=" + date + ", time=" + time + ", lotNumber="
				+ lotNumber + ", amountInBox=" + amountInBox + ", boxesAmount=" + boxesAmount + ", spcCode=" + spcCode
				+ ", alterCode=" + alterCode + ", wireName=" + wireName + ", processSheetStatus=" + processSheetStatus
				+ ", amountInLot=" + amountInLot + ", boxCode=" + boxCode + "]";
	}
	
	

}
