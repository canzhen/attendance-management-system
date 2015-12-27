package action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import utils.CheckHelper;
import utils.FileHelper;
import utils.PicFace;
import utils.PictureHelper;
import utils.StopCheckingTimerTask;
import utils.Values;
import db.entity.Course;
import db.entity.CourseInfo;
import db.entity.StudentInfo;
import db.util.DBHelper;
import pic.entity.FaceEntity;

public class studentAction extends MyActionSupport{	
	String msg="";
	String index="";
	Map session = getSession();
	String sno = (String) session.get("id");//获取学号
	ArrayList<CourseInfo> courses = new ArrayList<CourseInfo>();//课程链表，保存当前时间学生的课程信息

	/**
	 * 在返回页面之前，需要从数据库中比对，查找当前要上的课，
	 * 然后把课程信息放到request里传递过去。
	 * 若当前时间有课，那么还要判断老师是否已经上传点名图片，
	 * 若有，则也要放到request里传递到网页端。
	 * 若当前时间段没有课，那么就返回NOCURRENTCLASS，跳转到无课页面。
	 */
	@Override
	public String index(){
		
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
			courses = DBHelper.getAllCoursesInfo("student",sno,coursesno,false);
			int count = -1;
			if ( courses != null) 
				count = courses.size();
			session.put("coursesNum", count);//返回当天的课程数
			if ( count == 0 ){//当天无课，返回NOCURRENTCLASS
				courses = DBHelper.getAllCoursesInfo("student",sno,coursesno,true);
				count = courses.size();
				session.put("coursesNum",count);
				session.put("coursesInfo", courses);//传入所有课程编号
				return NOCURRENTCLASS;
			}else if ( count == 1 ){//当天有一节课，返回SUCCESS
				String cno = courses.get(0).getCno();
				String tno = courses.get(0).getTno();
				session.put("cno", cno);
				session.put("tno", tno );
				session.put("coursesInfo", courses);//传入当前课程的类，包含具体信息
				/*
				 * 判断老师是否已经将点名的图片上传，
				 * 若已经上传，则放在session里传过去
				 */
				String servletPath = ServletActionContext.getServletContext().getRealPath("/");
				String url = PictureHelper.getPicUrl(
						servletPath,DBHelper.getTnoBySnoCno(sno, cno));
				session.put("picUrl", url);
				session.put("absolutePicUrl", servletPath+url);
				/*
				 * 0为没签到，1为已经签到
				 */
				ArrayList<StudentInfo> list = FileHelper.deserializeStudentsInfo(tno);
				for (int i=0; i < list.size(); i++){
					if ( list.get(i).getSno().equals(sno)){
						session.put("ifChecked", 1);
						break;
					}
				}
				session.put("ifChecked", 0);
				
			}else if ( count > 1){//课程冲突，返回SUCCESS，由界面判断处理
				session.put("coursesInfo", courses);//课程冲突，将所有课传入，便于页面显示
			}else if ( count == -1 ){//当天无课，返回SUCCESS，由界面判断处理
				session.put("coursesInfo", "这周不属于上课周，放假或者为自习周，无课");
			}
			
			return SUCCESS;
		}
	}
	
	public String addFace(){
		String tno = (String)session.get("tno");
		ArrayList<StudentInfo> studentsInfo = FileHelper.deserializeStudentsInfo(tno);
		CheckHelper.checkIn(studentsInfo, sno);
		FileHelper.serializeStudentsInfo(studentsInfo, tno);
		session.put("checkStatus", 1);
		return INFO;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}
	
}