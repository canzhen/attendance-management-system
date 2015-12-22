package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import db.entity.CourseInfo;
import db.util.DBHelper;

public class teacherAction extends MyActionSupport{
	private Map session = getSession();//获取session
	private String tno;//教工号
	private List<CourseInfo> courses = new ArrayList<CourseInfo>();//课程链表，保存当前时间老师的课程信息
	
	/**
	 * 在返回页面之前，需要从数据库中比对，查找当前要上的课，
	 * 然后把课程信息放到request里传递过去。
	 * 若当前时间段没有课，那么就返回NOCURRENTCLASS，跳转到无课页面。
	 */
	@Override
	public String index(){
		/*
		 * --------测试部分----------
		 */
		session.put("identity", "teacher");
		tno = "11111111";
		/*
		 * --------测试部分----------
		 */
		//tno = (String) session.get("id");//获取教工号
		
		if ( !session.get("identity").equals("teacher") ){
			return ERROR;
		}else{
			List<String> coursesno = DBHelper.getCoursesno("teacher",tno);
			/*
			 * 0为显示所有课程和信息
			 * 1为显示当前课程信息
			 * -1为这周不属于上课周，放假或者为自习周，无课
			 * 大于1为课程冲突
			 */
			courses = DBHelper.checkHasClasses("teacher",tno,coursesno);
			int count = -1;
			if ( courses != null) 
				count = courses.size();
			session.put("classNum", count);
			if ( count == 0 ){//当天无课，返回NOCURRENTCLASS
				session.put("coursesInfo", coursesno);//传入所有课程编号
				return NOCURRENTCLASS;
			}else if ( count == 1 ){//当天有一节课，返回SUCCESS
				session.put("coursesInfo", courses.get(0));//传入当前课程的类，包含具体信息
			}else if ( count > 1){//课程冲突，返回SUCCESS，由界面判断处理
				session.put("coursesInfo", courses);//课程冲突，将所有课传入，便于页面显示
			}else if ( count == -1 ){//当天无课，返回SUCCESS，由界面判断处理
				session.put("coursesInfo", "这周不属于上课周，放假或者为自习周，无课");
			}
			
			return SUCCESS;
		}
	}
	
	/**
	 * 修改老师点名的时长
	 * @return
	 */
	public String editChecktime(){
		DBHelper.editTcValue("11111111", "cs002", "checktime", 11);
		return SUCCESS;
	}
}
