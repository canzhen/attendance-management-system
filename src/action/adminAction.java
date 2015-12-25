package action;

import java.text.SimpleDateFormat;
import java.util.Date;

import utils.Values;

public class adminAction extends MyActionSupport{
	String date="";
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String setStartDate(){
		if ( !date.equals("") ){
			String[] dateInfo = new String[3];
			dateInfo = date.split("-");
			@SuppressWarnings("deprecation")
			Date dateEntity = new Date(new Integer(dateInfo[0]),new Integer(dateInfo[1]),new Integer(dateInfo[2]));
			Values.start_date = dateEntity;
		}
		return SUCCESS;
	}
}