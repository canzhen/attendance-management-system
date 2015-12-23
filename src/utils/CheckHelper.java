package utils;

import java.util.List;

import db.entity.StudentInfo;

/**
 * 老师点名，学生签到的内部工具类
 * @author 周灿桢
 *
 */
public class CheckHelper {
	/**
	 * 学生签到，返回是否签到成功
	 * @param sno 学生学号
	 * @param studentsInfo 该课的学生列表
	 * @return 是否签到成功
	 */
	public static boolean checkIn(String sno,List<StudentInfo> studentsInfo){
		
		for (int i = 0; i < studentsInfo.size(); i++){
			if (studentsInfo.get(i).getSno().equals(sno)){
				studentsInfo.remove(i);//到了，则从列表里剔除，最后剩下的就是没到的
				return true;//签到成功
			}
		}
		return false;//列表里没有这个学生，则签到失败
	}
}
