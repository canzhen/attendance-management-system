package utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import db.entity.StudentInfo;

public class Values {
	/**
	 * 默认上课时间
	 */
	public static int[] BeginEndWeek={1,16};
	/**
	 * 第一种上课时间
	 */
	public static int[] weekTypeOne={1,8};
	/**
	 * 第二种上课时间
	 */
	public static int[] weekTypeTwo={9,16};
	/**
	 * 管理员的id号（存在teacher表里）
	 */
	public String manager_id = "00000000";
	
	public static int webX=800;
	
	public static int webY=400;
	/**
	 * 教师上传图片的路径
	 */
	public static String save_pic_path = "D:\\传真是爱学习的传真\\专业课-javaEE\\作业\\大作业\\AttendanceManagement\\WebContent\\teacher\\";
	
	public static String save_studentsInfo_path = "C:\\studentsInfoSerializable\\";
	
	public static String save_startDate_path = "C:\\startDateSerializable\\";
	
	public static int index = 1;
	
	public static Date start_date = new Date(115,9,1);//默认开学日期为2015年9月1日
	
	/**
	 * 记录每节课老师的开始点名时间
	 */
	//public static HashMap start_check_time = new HashMap<String,Date>();
	/**
	 * 记录每节课的学生信息
	 */
//<<<<<<< HEAD
//	public static HashMap studentsInfo_for_each_class = new HashMap<String,ArrayList<StudentInfo>>();
//=======
//	//public static HashMap studentsInfo_for_each_class = new HashMap<String,ArrayList<StudentInfo>>();
//>>>>>>> 92a36c551259c41e33c65c096b1e52ee6e30d36c

}
