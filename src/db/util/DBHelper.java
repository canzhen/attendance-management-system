package db.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Transaction;
import utils.DateCalculator;
import utils.Values;
import db.entity.*;

public class DBHelper {
	/**
	 * 获得当前老师或者学生上的所有的课程的编号
	 * @param identity 当前身份是老师还是学生
	 * @param id 当前编号
	 * @return 课程编号 String的列表List
	 */
	@SuppressWarnings("unchecked")
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
	 *  根据身份和id和课程编号返回所有的课程信息，可以是当前时间的，
	 *  也可以是该同学或老师的所有时间段的课程
	 * @param identity 身份
	 * @param id 编号
	 * @param classesno 课程的id列表
	 * @param ifReturnAll 是否返回全部，还是只是返回当前课程
	 * @return 返回当前时间的课程
	 */
	public static ArrayList<CourseInfo> getAllCoursesInfo(
			String identity,String id,List<String> classesno,boolean ifReturnAll){
		int week = 0;
		String day="",time="";
		Date date = new Date();//获取当前日期
		int current_week = DateCalculator.getCurrentWeek(new Date(115,10,1),date);
		String current_day = DateCalculator.getCurrentDay(date);
		String current_time = DateCalculator.getCurrentTime(date);
		//记录当天的课程
		ArrayList<CourseInfo> courses = new ArrayList<CourseInfo>();
		CourseHome courseHome = new CourseHome();
		
		Course course;
		CourseInfo courseInfo;
		
		
		Tc courseDetails;
		for (int i = 0; i < classesno.size(); i++){
			courseDetails = new Tc();
			/*
			 * 找出tc表，得到每一个课的具体信息
			 */
			Transaction tran = courseHome.createTransaction();
			course = courseHome.findById(classesno.get(i));
			tran.commit();
			String cno = course.getCno();
			if (identity.equals("student")){
				/*
				 * 根据sno和cno获得tno
				 */
				ScId scid = new ScId();
				scid.setCno(course.getCno());
				scid.setSno(id);
				ScHome schome = new ScHome();
				Transaction tran1 = schome.createTransaction();
				Sc sc = schome.findById(scid);
				tran1.commit();
				String tno = sc.getTno();
				courseDetails = getCourseDetails(tno,cno);
				
			}else if (identity.equals("teacher")){
				courseDetails = getCourseDetails(id,cno);
			}
			
			week = courseDetails.getCweek();
			day = courseDetails.getCday()+"";
			time = courseDetails.getCday()+"";
			
			/*
			 * 和当前时间进行比对，若符合，则在count上加一，
			 * 最后判断count是否大于1，大于1则返回错误并且报告错误信息
			 * 0表示1-16周，1表示1-8周，2表示9-16周
			 */
			if (current_week<Values.BeginEndWeek[0] || current_week>Values.BeginEndWeek[1] )
				return courses;
			courseInfo = new CourseInfo();
			
			if (ifReturnAll){//如果是所有课程都要返回，则返回所有
				setCourseTime(courseInfo,week,day,time);//设置时间信息
				setCourseInfo(courseInfo,course,courseDetails,identity,id);//设置除了时间信息之外的其他信息
				courses.add(courseInfo);
			}else {//否则，进行判断之后再返回
				if(((week==0)||(week==1 && current_week>=Values.weekTypeOne[0] && current_week<=Values.weekTypeOne[1]) 
						||( week==2 && current_week>=Values.weekTypeTwo[0] && current_week<=Values.weekTypeTwo[1]))//符合周
						&& day.contains(current_day)//符合天
						&& time.contains(current_time)){//符合时间
							setCourseTime(courseInfo,week,day,time);//设置时间信息
							setCourseInfo(courseInfo,course,courseDetails,identity,id);//设置除了时间信息之外的其他信息
							courses.add(courseInfo);
					}
			}
		}
		return courses;
	}
	
	
	/**
	 * 根据tno和cno查询课程的具体信息
	 * @param tno 教师编号
	 * @param cno 课程编号
	 * @return 课程的具体信息
	 */
	private static Tc getCourseDetails(String tno,String cno){

		TcHome tchome = new TcHome();
		TcId tcid = new TcId();
		tcid.setTno(tno);
		tcid.setCno(cno);
		Transaction tran1 = tchome.createTransaction();
		Tc courseDetails = tchome.findById(tcid);
		tran1.commit();
		
		return courseDetails;
	}
	
	
	/**
	 * 通过课程类来设置课程的具体信息，如点名时间、最大缺席数等。
	 * 老师和学生的课程信息不一样，学生还要加上已缺席数。
	 * @param courseInfo 要设置的课程信息类
	 * @param course 课程类
	 * @param identity 身份，老师还是学生
	 * @param id 如果是老师则为教工号，如果是学生则为学号
	 */
	private static void setCourseInfo(CourseInfo courseInfo,
			Course course,Tc courseDetails,String identity,String id){
		
		courseInfo.setCno(courseDetails.getId().getCno());//设置课程编号
		courseInfo.setCname(course.getCname());//设置课程名字
		courseInfo.setCheckTime(courseDetails.getCheckTime());
		courseInfo.setMaxAbsence(courseDetails.getMaxAbsence());
		if (identity.equals("student")){
			String cno = course.getCno(),sno = id;
			ScHome schome = new ScHome();
			Transaction tran = schome.createTransaction();
			ScId scid = new ScId();
			scid.setCno(cno);
			scid.setSno(sno);
			Sc result = schome.findById(scid);
			tran.commit();
			courseInfo.setAbsenceNum(result.getAbsenceNum());
		}
	}
	
	
	/**
	 * 设置课程的具体时间信息
	 * @param week 上课的周数
	 * @param day 上课的天
	 * @param time 上课的具体时间
	 */
	private static void setCourseTime(CourseInfo courseInfo,int week,String day,String time){
		
		String result="",weekInfo="", dayInfo="周",timeInfo="";
		/*
		 * 处理week字符
		 */
		switch (week){
		case 0: weekInfo += "[1-16周]";break;
		case 1: weekInfo += "[1-8周]";break;
		case 2: weekInfo += "[9-16周]";break;
		}
		
		/*
		 * 处理day字符
		 */
		for (int i = 0; i < day.length(); i++){
			if ( i != (day.length()-1) )
				dayInfo += (day.charAt(i)+"周  ");
			else dayInfo += (day.charAt(i));
		}
		
		/*
		 * 处理time字符
		 */
		for (int i = 0; i < time.length(); i++){
			if ( i != (time.length()-1) )
				timeInfo += (time.charAt(i)+"、");
			else timeInfo += (time.charAt(i)+"节");
		}
		//拼接星期、天、时间，形成时间信息
		courseInfo.setTime(dayInfo+"&nbsp;&nbsp;"+timeInfo+"&nbsp;&nbsp;"+weekInfo);
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
	
	/**
	 * 获取当前课程的所有没到的学生的学号、姓名和缺勤数
	 * @return 学生信息的列表
	 */
	public static List<StudentInfo> getStudentInfoForAClassByCnoTno(String cno,String tno){
		List<StudentInfo> result = new ArrayList<StudentInfo>();
		ScHome schome = new ScHome();
		Transaction tran = schome.createTransaction();
		List<Sc> students =schome.findByCnoTno(cno, tno);
		tran.commit();
		StudentInfo tempInfo = new StudentInfo();
		Sc tempSc = new Sc();
		Student tempStudent = new Student();
		StudentHome tempSHome = new StudentHome();
		
		for (int i = 0; i < students.size(); i++){
			Transaction tran1 = tempSHome.createTransaction();
			tempInfo = new StudentInfo();
			tempSc = students.get(i);
			tempInfo.setSno(tempSc.getId().getSno());
			tempInfo.setAbsenceNum(tempSc.getAbsenceNum());
			tempStudent = tempSHome.findById(tempSc.getId().getSno());
			tempInfo.setSname(tempStudent.getSname());
			result.add(tempInfo);
			tran1.commit();
		}
		return result;
	}
	
	
	public CourseInfo getCourseInfo(String cno,String tno){
		CourseInfo courseInfo = new CourseInfo();
		CourseHome courseHome = new CourseHome();
		Transaction tran = courseHome.createTransaction();
		Course course = courseHome.findById(cno);
		tran.commit();
		if ( course !=  null ){
			courseInfo.setCno(course.getCno());
			courseInfo.setCname(course.getCname());
		}
		TcId tcid = new TcId();
		tcid.setCno(cno);
		tcid.setTno(tno);
		TcHome tchome = new TcHome();
		Transaction tran1 = tchome.createTransaction();
		Tc tc = tchome.findById(tcid);
		setCourseTime(courseInfo,tc.getCweek(),tc.getCday()+"",tc.getCtime()+"");
		courseInfo.setMaxAbsence(tc.getMaxAbsence());
		courseInfo.setCheckTime(tc.getCheckTime());
		
		return courseInfo;
	}
	
	public int getMaxAbsenceNum(String cno,String tno){
		int result = 0;
		Tc tc = new Tc();
		TcId tcid = new TcId();
		tcid.setCno(cno);
		tcid.setTno(tno);
		TcHome tchome = new TcHome();
		Transaction tran = tchome.createTransaction();
		tc = tchome.findById(tcid);
		if (tc != null)
			result = tc.getMaxAbsence();		
		return result;
	}
	
	
}
