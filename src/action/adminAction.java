package action;

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
			
		}
		return SUCCESS;
	}
}