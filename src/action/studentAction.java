package action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;

import dbentity.Course;
import dbentity.CourseHome;
import dbentity.Sc;
import dbentity.ScHome;

public class studentAction extends MyActionSupport{
	private Map session;
	private String sno;//学号
	/**
	 * 在返回页面之前，需要从数据库中比对，查找当前要上的课，
	 * 然后把课程信息放到request里传递过去。
	 * 若当前时间有课，那么还要判断老师是否已经上传点名图片，
	 * 若有，则也要放到request里传递到网页端。
	 * 若当前时间段没有课，那么就返回NOCURRENTCLASS，跳转到无课页面。
	 */
	@Override
	public String index(){
		session = getSession();
		boolean ifHasClass = false;//默认今天没课
		List<String> classes = new ArrayList<String>();
		sno = (String) session.get("id");
		
		if ( !session.get("identity").equals("student") ){
			return ERROR;
		}else{
			/*
			 * 先找到该学生所有的课的编号，
			 * 存在classes（List<String>）里
			 */
			classes = getClasses();
			ifHasClass = checkIfHasClass(classes);
			
			return SUCCESS;
		}
	}
	
	private List<String> getClasses(){
		List<String> temp = new ArrayList<String>();
		ScHome schome = new ScHome();
		Transaction tran = schome.createTransaction();
		List<Sc> result = (List<Sc>)schome.findBySno(sno);
		tran.commit();
		for (int i = 0; i < result.size(); i++){
			temp.add(result.get(i).getId().getCno());
		}
		return temp;
	}
	
	private boolean checkIfHasClass(List<String> classes){
		int week=0,day=0,time=0;
		Date date = new Date();
		String current_week = getCurrentWeek(date);
		String current_day = getCurrentDay(date);
		String current_time = getCurrentTime(date);
		
		/**
		 * 记录符合当前时间段的课程的数量，若大于1，则报告错误，等于1则正常显示，0则显示所有的课程
		 */
		int count = 0;
		CourseHome courseHome = new CourseHome();
		Course course;
				
		for (int i = 0; i < classes.size(); i++){
			//获取学生每一个课的具体信息
			course = courseHome.findById(classes.get(i));
			week = course.getCweek();
			day = course.getCday();
			time = course.getCtime();
			/*
			 * 和当前时间进行比对，若符合，则在count上加一，
			 * 最后判断count是否大于1，大于1则返回错误并且报告错误信息
			 */
			
			
		}
		
		return false;
	}
	
	private int getCurrentWeek(Date date){
		return 0;
	}
	
	private int getCurrentDay(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	private int getCurrentTime(Date date){
//		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
//		Date now = df.parse(date), 
		return 0;
	}
	
}