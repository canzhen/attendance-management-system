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
import db.entity.CourseInfo;
import db.entity.Sc;
import db.entity.ScHome;
import db.entity.ScId;
import db.util.DBHelper;

public class studentAction extends MyActionSupport{
	private Map session = getSession();//获取session
	private String sno;//学号
	private List<CourseInfo> courses = new ArrayList<CourseInfo>();//课程链表，保存当前时间学生的课程信息
	
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
			courses = DBHelper.checkHasClasses("student",sno,coursesno);
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
		}
		return SUCCESS;
	}
	
	
}