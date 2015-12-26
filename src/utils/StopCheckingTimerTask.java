package utils;

import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.Transaction;

import db.entity.Sc;
import db.entity.ScHome;
import db.entity.ScId;
import db.util.DBHelper;

public class StopCheckingTimerTask extends TimerTask{
	private String tno = "";
	
	public StopCheckingTimerTask(String tno){
		this.tno = tno;
	}
	
	
	public void execute(){
		//Values.start_check_time.remove(tno);//在Values里去掉给该老师的点名时间
		//Values.studentsInfo_for_each_class.remove(tno);//在Values里去掉该节课的学生信息
	}
	
	@Override
	public void run() {
		execute();
	}
}
