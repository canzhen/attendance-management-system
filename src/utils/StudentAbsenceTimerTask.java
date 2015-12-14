package utils;

import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.Transaction;

import db.entity.Sc;
import db.entity.ScHome;
import db.entity.ScId;
import db.util.DBHelper;

public class StudentAbsenceTimerTask extends TimerTask{
	private String sno = "", cno = "";
	
	public StudentAbsenceTimerTask(String sno,String cno){
		this.sno = sno;
		this.cno = cno;
	}
	
	
	public void execute(){
		DBHelper.addAbsenceNum(sno, cno);
	}
	
	@Override
	public void run() {
		execute();
	}
}
