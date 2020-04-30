package fcaProduction;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FxPropertyCheckOperationHistory {

	
	public SimpleIntegerProperty databaseKey = new SimpleIntegerProperty();
	public SimpleStringProperty boxCode = new SimpleStringProperty();
	public SimpleStringProperty date = new SimpleStringProperty();
	public SimpleStringProperty time = new SimpleStringProperty();
	public SimpleStringProperty operatorName = new SimpleStringProperty();
	public SimpleStringProperty amount = new SimpleStringProperty();
	public SimpleStringProperty lot = new SimpleStringProperty();
	public SimpleStringProperty circuitName = new SimpleStringProperty();
	public SimpleStringProperty machine = new SimpleStringProperty();
	
	public  Integer  getDatabaseKey() {
		return databaseKey.get();
	}
	public String getBoxCode() {
		return boxCode.get();
	}
	public String getDate() {
		return date.get();
	}
	public String getTime() {
		return time.get();
	}
	public String getOperatorName() {
		return operatorName.get();
	}
	public String getAmount() {
		return amount.get();
	}
	public String getLot() {
		return lot.get();
	}
	public String getCircuitName() {
		return circuitName.get();
	}
	public  String getMachine() {
		return machine.get();
	}
	
	
	@Override
	public String toString() {
		return "FxPropertyCheckOperationHistory [databaseKey=" + databaseKey + ", boxCode=" + boxCode + ", date=" + date
				+ ", time=" + time + ", operatorName=" + operatorName + ", amount=" + amount + ", lot=" + lot
				+ ", circuitName=" + circuitName + ", machine=" + machine + "]";
	}
	
	
	
}
