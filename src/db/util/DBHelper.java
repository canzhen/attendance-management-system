package db.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Transaction;

import utils.DateCalculator;
import utils.Values;
import db.entity.Course;
import db.entity.CourseHome;
import db.entity.CourseInfo;
import db.entity.Sc;
import db.entity.ScHome;
import db.entity.ScId;
import db.entity.Tc;
import db.entity.TcHome;
import db.entity.TcId;

public class DBHelper {
	/**
	 * 获得当前老师或者学生上的所有的课程的编号
	 * @param identity 当前身份是老师还是学生
	 * @param id 当前编号
	 * @return 课程编号 String的列表List
	 */
	public static List<String> getCoursesno(String identity,String id){
		List result = new ArrayList();
		List<String> coursenoList = new ArrayList<String>();
		Transaction tran = null;
		
		if (identity.equals("student")){
			ScHome schome = new ScHome();
			tran = schome.createTransaction();
			result = (List<Sc>)schome.findByNo("sno",id);
		}else if (identity.equals("teacher")){
			TcHome tchome = new TcHome();
			tran = tchome.createTransaction();
			result = (List<Tc>)tchome.findByNo("tno", id);
		}
		tran.commit();
		
		if (identity.equals("student")){
			for (int i = 0; i < result.size(); i++){
				coursenoList.add(((Sc) result.get(i)).getId().getCno());
			}
		}else if (identity.equals("teacher")){
			for (int i = 0; i < result.size(); i++){
				coursenoList.add(((Tc) result.get(i)).getId().getCno());
			}
		}
		//去重
		HashSet<String> hs = new HashSet<String>(coursenoList);
		coursenoList = new ArrayList<String>(hs);
		
		return coursenoList;
	}
	
	
	/**
	 * 查询相应课程id的课程列表内是否有当前时间的课程，并返回
	 * @param classesno 课程的id列表
	 * @return 返回当前时间的课程
	 */
	public static ArrayList<CourseInfo> checkHasClasses(String identity,String id,List<String> classesno){
		int week;
		String day,time;
		Date date = new Date();//获取当前日期
		int current_week = DateCalculator.getCurrentWeek(new Date(115,10,1),date);
		String current_day = DateCalculator.getCurrentDay(date);
		String current_time = DateCalculator.getCurrentTime(date);
		//记录当天的课程
		ArrayList<CourseInfo> courses = new ArrayList<CourseInfo>();
		CourseHome courseHome = new CourseHome();
		Transaction tran = courseHome.createTransaction();
		Course course;
		CourseInfo courseInfo;
		
				
		for (int i = 0; i < classesno.size(); i++){
			//获取每一个课的具体信息
			course = courseHome.findById(classesno.get(i));
			week = course.getCweek();
			day = course.getCday()+"";
			time = course.getCtime()+"";
			/*
			 * 和当前时间进行比对，若符合，则在count上加一，
			 * 最后判断count是否大于1，大于1则返回错误并且报告错误信息
			 * 0表示1-16周，1表示1-8周，2表示9-16周
			 */
			if (current_week<Values.BeginEndWeek[0] || current_week>Values.BeginEndWeek[1] ) return null;
			if(((week==1 && current_week>=Values.weekTypeOne[0] && current_week<=Values.weekTypeOne[1]) 
					||( week==2 && current_week>=Values.weekTypeTwo[0] && current_week<=Values.weekTypeTwo[1]))//符合周
					&& day.contains(current_day)//符合天
					&& time.contains(current_time)){//符合时间
				courseInfo = new CourseInfo();
				setCourseTime(courseInfo,week,day,time,
						current_week,current_day,current_time);//设置时间信息
				setCourseInfo(courseInfo,course,identity,id);//设置除了时间信息之外的其他信息
				courses.add(courseInfo);
			}
		}
		
		tran.commit();
		return courses;
	}
	
	
	/**
	 * 通过课程类来设置课程的具体信息，如点名时间、最大缺席数等。
	 * 老师和学生的课程信息不一样，学生还要加上已缺席数。
	 * @param courseInfo 要设置的课程信息类
	 * @param course 课程类
	 * @param identity 身份，老师还是学生
	 * @param id 如果是老师则为教工号，如果是学生则为学号
	 */
	private static void setCourseInfo(CourseInfo courseInfo,Course course,String identity,String id){
		courseInfo.setCno(course.getCno());//设置课程编号
		courseInfo.setCname(course.getCname());//设置课程名字
		
		if (identity.equals("student")){
			
		}
	}
	
	
	/**
	 * 设置课程的具体时间信息
	 * @param week 上课的周数
	 * @param day 上课的天
	 * @param time 上课的具体时间
	 */
	private static void setCourseTime(CourseInfo courseInfo,int week,String day,String time,
			int current_week,String current_day,String current_time){
		
		String result="",weekInfo="上课时间为", dayInfo="星期",timeInfo="第";
		/*
		 * 处理week字符
		 */
		switch (week){
		case 0: weekInfo += "1-16周";break;
		case 1: weekInfo += "1-8周";break;
		case 2: weekInfo += "9-16周";break;
		}
		
		/*
		 * 处理day字符
		 */
		for (int i = 0; i < day.length(); i++){
			if ( i != (day.length()-1) )
				dayInfo += (i+"和星期");
			else dayInfo += i;
		}
		
		/*
		 * 处理time字符
		 */
		for (int i = 0; i < time.length(); i++){
			if ( i != (time.length()-1) )
				timeInfo += (i+"节和第");
			else timeInfo += (i+"节");
		}
		//拼接星期、天、时间，形成时间信息
		courseInfo.setTime(weekInfo+"的"+dayInfo+"的"+timeInfo);
	}
	
	
	/**
	 * 学生缺席，在相应sc表内的缺席数加一
	 * @param sno 学生学号
	 * @param cno 学生所上的课程的课程号
	 */
	public static void addAbsenceNum(String sno,String cno){
		
		//查询以sno和cno为主键的sc信息
		ScId scid = new ScId();
		scid.setSno(sno);
		scid.setCno(cno);
		
		ScHome schome = new ScHome();
		Transaction tran = schome.createTransaction();
		Sc result = schome.findById(scid);
		
		result.setAbsenceNum(result.getAbsenceNum()+1);//在缺席数量上加一
		schome.merge(result);//更新数据库信息
		tran.commit();//commit，结束
	}
	
	
	/**
	 * 用于老师修改点名时长
	 * @param tno 老师的教工号
	 * @param checktime 新的点名时长
	 * @param type 要修改的tc表column的名字
	 * @param value 要修改的tc表column的值
	 */
	public static void editTcValue(String tno,String cno,String type,int value){
		TcId tcid = new TcId();
		tcid.setTno(tno);
		tcid.setCno(cno);
		TcHome tchome = new TcHome();
		Transaction tran = tchome.createTransaction();
		Tc result = tchome.findById(tcid);
		if (type.toLowerCase().equals("checktime"))
			result.setCheckTime(value);
		else if (type.toLowerCase().equals("maxabsence"))
			result.setMaxAbsence(value);
		tchome.merge(result);//更新数据库信息
		tran.commit();
	}
	
}
