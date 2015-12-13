package utils;

import org.hibernate.Transaction;

import dbentity.Sc;
import dbentity.ScHome;
import dbentity.ScId;

public class Helper {
	/**
	 * 学生该节课缺席，更新相应的数据库数据
	 * @param sno 学生学号
	 * @param cno 课程号
	 */
	public void studentAbsence(String sno,String cno){
		//查询以sno和cno为主键的sc信息
		ScId scid = new ScId();
//		scid.setSno(sno);
//		scid.setCno(cno);
		scid.setSno("13301085");
		scid.setCno("cs001");
		Sc sc = new Sc();
		sc.setId(scid);
		
		ScHome schome = new ScHome();
		Transaction tran = schome.createTransaction();
		Sc result = schome.findById(scid);
		
		result.setAbsenceNum(result.getAbsenceNum()+1);//在缺席数量上加一
		schome.merge(result);//更新数据库信息
		tran.commit();//commit，结束
	}
}
