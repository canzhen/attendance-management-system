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
import db.entity.Sc;
import db.entity.ScHome;
import db.entity.Tc;
import db.entity.TcHome;

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
	
	
	
	public static ArrayList<Course> checkHasClasses(List<String> classesno){
		int week;
		String day,time;
		Date date = new Date();//获取当前日期
		int current_week = DateCalculator.getCurrentWeek(new Date(115,10,1),date);
		String current_day = DateCalculator.getCurrentDay(date);
		String current_time = DateCalculator.getCurrentTime(date);
		//记录当天的课程
		ArrayList<Course> courses = new ArrayList<Course>();
		CourseHome courseHome = new CourseHome();
		Transaction tran = courseHome.createTransaction();
		Course course;
		
				
		for (int i = 0; i < classesno.size(); i++){
			//获取学生每一个课的具体信息
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
				courses.add(course);
			}
		}
		
		tran.commit();
		return courses;
	}
}
