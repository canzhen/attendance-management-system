package utils;

import java.util.ArrayList;
import java.util.List;

import pic.entity.FaceEntity;
import db.entity.StudentInfo;

/**
 * 老师点名，学生签到的内部工具类
 * @author 周灿桢
 *
 */
public class CheckHelper {
	public List<FaceEntity> checkFaceList = null;
	public static CheckHelper myCheckHelper= null;

	private CheckHelper(){
		checkFaceList = new ArrayList<FaceEntity>();
	}
	
	public static CheckHelper getCheckHelper(){
		if (myCheckHelper == null)
			myCheckHelper = new CheckHelper();
		return myCheckHelper;
	}
	
	/**
	 * 学生签到，返回是否签到成功
	 * @param index 是第几个学生 
	 * @param studentsInfo 该课的学生列表
	 * @return 是否签到成功
	 */
	public  boolean checkIn(int index,List<StudentInfo> studentsInfo){
		String sno = "";
		if (checkFaceList != null){
			sno = checkFaceList.get(index).getSno();
			StudentInfo sinfo;
			for (int i = 0; i < studentsInfo.size(); i++){
				sinfo = studentsInfo.get(i);
				if (sinfo.equals(sno)){
					sinfo.setChecked(true);//签到
					return true;
				}
			}
		}
		return false;//列表里没找到该同学，则签到失败
	}
	/**
	 * 往脸点名列表里加入脸的信息
	 * @param face
	 */
	public void addFace(FaceEntity face){
		checkFaceList.add(face);
	}
	
	public List<FaceEntity> getCheckFaceList() {
		return checkFaceList;
	}
	public void setCheckFaceList(List<FaceEntity> checkFaceList) {
		this.checkFaceList = checkFaceList;
	}
}
