package db.util;

import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.Transaction;

import db.entity.Sc;
import db.entity.ScHome;
import db.entity.ScId;

public class StudentAbsenceDBHelperTimerTask extends TimerTask{
	private String sno = "", cno = "";
	
	public StudentAbsenceDBHelperTimerTask(String sno,String cno){
		this.sno = sno;
		this.cno = cno;
	}
	
	/**
	 * 学生该节课缺席，更新相应的数据库数据
	 */
	public void addNum(){
		
		//查询以sno和cno为主键的sc信息
		ScId scid = new ScId();
		scid.setSno(sno);
		scid.setCno(cno);
		Sc sc = new Sc();
		sc.setId(scid);
		
		ScHome schome = new ScHome();
		Transaction tran = schome.createTransaction();
		Sc result = schome.findById(scid);
		
		result.setAbsenceNum(result.getAbsenceNum()+1);//在缺席数量上加一
		schome.merge(result);//更新数据库信息
		tran.commit();//commit，结束
	}
	
	@Override
	public void run() {
		addNum();
	}
}
