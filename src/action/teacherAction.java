package action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import utils.PictureHelper;
import utils.StopCheckingTimerTask;
import utils.TimerHelper;
import utils.Values;
import db.entity.CourseInfo;
import db.entity.StudentInfo;
import db.util.DBHelper;

public class teacherAction extends MyActionSupport{
	File pic;//用于获取教师上传的图片
	String picContentType;
	String picFileName;
	String courseNo="";//当前所在页面的课程id
	String max_absence="";//最大缺勤数
	String check_time="";//点名时间
	Map session = getSession();//获取session
	String tno = (String) session.get("id");//获取教工号
	List<CourseInfo> courses = new ArrayList<CourseInfo>();//课程链表，保存当前时间老师的课程信息
	List<StudentInfo> studentsInfo = null;//如果此时有课，保存此时的课的所有学生的信息

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
		//session.put("identity", "teacher");
		//tno = "11111111";
		/*
		 * --------测试部分----------
		 */
		
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
			courses = DBHelper.getAllCoursesInfo("teacher",tno,coursesno,false);
			int count = -1;
			if ( courses != null)
				count = courses.size();
			/**
			 * 测试有课的时候
			 */
			session.put("coursesNum", count);
//			session.put("coursesNum", 1);
			if ( count == 0 ){//当天无课，返回NOCURRENTCLASS
				courses = DBHelper.getAllCoursesInfo("teacher",tno,coursesno,true);
				count = courses.size();
				session.put("coursesNum",count);
				session.put("coursesInfo", courses);//传入所有课程编号
				return NOCURRENTCLASS;
			}else if ( count == 1 ){//当天有一节课，返回SUCCESS
				session.put("cno", courses.get(0).getCno());
//				session.put("cno", "cs002");
				session.put("coursesInfo", courses);//传入当前课程的类，包含具体信息
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
	
	public String getStudentInfoForAClass(){
		List result = DBHelper.getStudentInfoForAClassByCnoTno(courseNo,tno);
		return SUCCESS;
	}
	
	public String savePic(){
		//pic = new File("d:\\my_icon.jpg");
		//String path =  ServletActionContext.getServletContext().getRealPath("/");
		if (pic != null)
			PictureHelper.savePic(pic,(String)session.get("id"));
		start_checking();
		return SUCCESS;
	}
	
	public String setMaxAbsence(){
		if ( !max_absence.equals("") )
			DBHelper.editTcValue(tno, courseNo, "maxabsence", new Integer(max_absence));
		return SUCCESS;
	}
	
	public String start_checking(){
		Values.start_check_time.put(tno, new Date());//在Values里给该老师的上课时间赋值
		if ( studentsInfo == null ){
			studentsInfo = DBHelper.getStudentInfoForAClassByCnoTno((String)session.get("cno"),tno);
			Values.studentsInfo_for_each_class.put(tno, studentsInfo);
		}
		TimerHelper.startTimer(new StopCheckingTimerTask(tno), 
				DBHelper.getCourseDetails(tno, (String)session.get("cno")).getCheckTime());
		return SUCCESS;
	}

	public File getPic() {
		return pic;
	}

	public void setPic(File pic) {
		this.pic = pic;
	}
	

	public String getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(String picContentType) {
		this.picContentType = picContentType;
	}

	public String getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(String picFileName) {
		this.picFileName = picFileName;
	}
	
	public String getMax_absence() {
		return max_absence;
	}

	public void setMax_absence(String max_absence) {
		this.max_absence = max_absence;
	}

	public String getCheck_time() {
		return check_time;
	}

	public void setCheck_time(String check_time) {
		this.check_time = check_time;
	}

}
