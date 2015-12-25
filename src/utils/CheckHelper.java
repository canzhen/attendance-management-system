package utils;

import java.util.ArrayList;
import java.util.List;

import pic.entity.FaceEntity;
import db.entity.StudentInfo;
import db.util.DBHelper;

/**
 * 老师点名，学生签到的内部工具类
 * @author 周灿桢
 *
 */
public class CheckHelper {
	
	public static boolean checkIn ( List<StudentInfo> studentsInfo,String sno ){
		StudentInfo info;
		for (int i = 0; i < studentsInfo.size(); i++){
			info = studentsInfo.get(i);
			if (info.getSno().equals(sno)){
				info.setChecked(true);//设置该同学已签到
				return true;
			}
		}
		return false;//在列表里找不到此人，则返回签到失败
	}
	
	/**
	 * 在数据库内的缺勤次数上加一
	 * @return
	 */
	public void addAbsenceNum(String sno,String cno){
		DBHelper.addAbsenceNum(sno, cno);
	}
}
