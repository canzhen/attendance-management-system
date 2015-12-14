package action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;

import utils.StudentAbsenceTimerTask;
import utils.TimerHelper;
import utils.Values;
import db.entity.Course;
import db.entity.CourseHome;
import db.entity.Sc;
import db.entity.ScHome;
import db.entity.ScId;
import db.util.DBHelper;

public class studentAction extends MyActionSupport{
	private Map session = getSession();//获取session
	private String sno;//学号
	private List<Course> courses = new ArrayList<Course>();//课程链表，保存当前时间学生的课程信息
	
	/**
	 * 在返回页面之前，需要从数据库中比对，查找当前要上的课，
	 * 然后把课程信息放到request里传递过去。
	 * 若当前时间有课，那么还要判断老师是否已经上传点名图片，
	 * 若有，则也要放到request里传递到网页端。
	 * 若当前时间段没有课，那么就返回NOCURRENTCLASS，跳转到无课页面。
	 */
	@Override
	public String index(){
		/*
		 * --------测试部分----------
		 */
		session.put("identity", "student");
		sno = "13301085";
		/*
		 * --------测试部分----------
		 */
		//sno = (String) session.get("id");//获取学号
		
		if ( !session.get("identity").equals("student") ){
			return ERROR;
		}else{
			List<String> coursesno = DBHelper.getCoursesno("student", sno);
			/*
			 * 0为显示所有课程和信息
			 * 1为显示当前课程信息
			 * -1为这周不属于上课周，放假或者为自习周，无课
			 * 大于1为课程冲突
			 */
			courses = DBHelper.checkHasClasses(coursesno);
			int count = -1;
			if ( courses != null) 
				count = courses.size();
			session.put("coursesNum", count);//返回当天的课程数
			if ( count == 0 ){//当天无课，返回NOCURRENTCLASS
				session.put("coursesInfo", coursesno);//传入所有课程编号
				return NOCURRENTCLASS;
			}else if ( count == 1 ){//当天有一节课，返回SUCCESS
				session.put("coursesInfo", courses.get(0));//传入当前课程的类，包含具体信息
				return SUCCESS;
			}else if ( count > 1){//课程冲突，返回SUCCESS，由界面判断处理
				session.put("coursesInfo", courses);//课程冲突，将所有课传入，便于页面显示
				return SUCCESS;
			}else if ( count == -1 ){//当天无课，返回SUCCESS，由界面判断处理
				session.put("coursesInfo", "这周不属于上课周，放假或者为自习周，无课");
				return SUCCESS;
			}
			
			return SUCCESS;
		}
	}
	
	
<<<<<<< HEAD
	public String addAbsenceNum(){
		String stuId="";
		String className="";
		if ( (session.get("id") != null) && //如果有已经登录
				(session.get("identity") != null) && //且身份为学生
				((int)session.get("coursesNum") == 1) ){//且当天只有一节课
			stuId = (String)session.get("id");
			className = ((Course)session.get("coursesInfo")).getCno();
			session.put("addAbsenceNumInfo", "success");
			new StudentAbsenceTimerTask(stuId,className).execute();
		}else{
			session.put("addAbsenceNumInfo", "error");
=======
	/**
	 * 获得当前学生上的所有的课程的编号
	 * @return 课程编号 String的列表List
	 */
	private List<String> getCoursesno(){
		List<String> temp = new ArrayList<String>();
		ScHome schome = new ScHome();
		Transaction tran = schome.createTransaction();
		List<Sc> result = schome.findBySno(sno);
		tran.commit();
		for (int i = 0; i < result.size(); i++){
			temp.add(result.get(i).getId().getCno());
		}
		HashSet<String> hs = new HashSet<String>(temp);
		temp = new ArrayList<String>(hs);
		return temp;
	}
	
	private int checkHasClasses(List<String> classesno){
		int week;
		String day,time;
		Date date = new Date();//获取当前日期
		int current_week = getCurrentWeek(new Date(115,10,1),date);
		String current_day = getCurrentDay(date);
		String current_time = getCurrentTime(date);
		courses = new ArrayList<Course>();
		/**
		 * 记录符合当前时间段的课程的数量，
		 * 若大于1，则报告错误，等于1则正常显示，0则显示所有的课程
		 */
		int count = 0;
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
			if (current_week<Values.BeginEndWeek[0] || current_week>Values.BeginEndWeek[1] ) return -1;
			if(((week==1 && current_week>=Values.weekTypeOne[0] && current_week<=Values.weekTypeOne[1]) 
					||( week==2 && current_week>=Values.weekTypeTwo[0] && current_week<=Values.weekTypeTwo[1]))//符合周
					&& day.contains(current_day)//符合天
					&& time.contains(current_time)){//符合时间
				courses.add(course);
				count++;
			}
		}
		
		tran.commit();
		return count;
	}
	
	/**
	 * 通过起始日期算出今天是第几周
	 * @param date 今天的日期
	 * @return 这周是开学的第几周
	 */
	private int getCurrentWeek(Date start_date,Date current_date){
		//开学日期
	    int startWeekday=Integer.parseInt(getCurrentDay(start_date));
	    int currentWeekday=Integer.parseInt(getCurrentDay(current_date));
		while(startWeekday!=1){
			start_date=new Date(start_date.getTime()+(long)(24 * 60 * 60 * 1000));
			startWeekday=Integer.parseInt(getCurrentDay(start_date));
		}
		while(currentWeekday!=1){
			current_date=new Date(current_date.getTime()-(long)(24 * 60 * 60 * 1000));
			currentWeekday=Integer.parseInt(getCurrentDay(current_date));
>>>>>>> 99a5b0172da7bbbdde0a29dbe3d614f4a1802764
		}
		
		return SUCCESS;
	}
	
	
	
}