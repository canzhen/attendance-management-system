package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import utils.CheckHelper;
import utils.PicFace;
import utils.StudentAbsenceTimerTask;
import db.entity.Course;
import db.entity.CourseInfo;
import db.util.DBHelper;
import pic.entity.FaceEntity;

public class studentAction extends MyActionSupport{	
	String msg="";
	FaceEntity face;


	/**
	 * 在返回页面之前，需要从数据库中比对，查找当前要上的课，
	 * 然后把课程信息放到request里传递过去。
	 * 若当前时间有课，那么还要判断老师是否已经上传点名图片，
	 * 若有，则也要放到request里传递到网页端。
	 * 若当前时间段没有课，那么就返回NOCURRENTCLASS，跳转到无课页面。
	 */
	@Override
	public String index(){
		Map session = getSession();
		String sno = (String) session.get("id");//获取学号
		List<CourseInfo> courses = new ArrayList<CourseInfo>();//课程链表，保存当前时间学生的课程信息
		/*
		 * --------测试部分----------
		 */
		//session.put("identity", "student");
		//sno = "13301081";
		/*
		 * --------测试部分----------
		 */
		
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
				session.put("coursesInfo", courses);//传入所有课程编号
				return NOCURRENTCLASS;
			}else if ( count == 1 ){//当天有一节课，返回SUCCESS
				session.put("courseId", courses.get(0).getCno());
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
	 * 在数据库内的缺勤次数上加一
	 * @return
	 */
	public String addAbsenceNum(){
		String stuId="";
		String className="";
		Map session = getSession();
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
	
	/**
	 * 选择自己的脸，并将信息进行处理和传递
	 * @return
	 */
	public String chooseOneFace(){
		Map session = getSession();
		//左上角坐标点的信息
		FaceEntity face=(FaceEntity) session.get("face");
		if(face.getSno()==null||face.getSno()==""){
			@SuppressWarnings("unchecked")
			List<FaceEntity> faceInfo=(List<FaceEntity>) session.get("faces");
			String sno=(String) session.get("id");
			for(int i=0;i<faceInfo.size();i++){
				if(faceInfo.get(i).equals(face)){
					face.setSno(sno);
					PicFace picFace=(PicFace) session.get("picface");
					picFace.setFaceName(face.getcX(), face.getcY(), sno);
				}
			}
			msg="这张脸是你的啦^_^";
			session.put("faces",faceInfo);
			return SUCCESS;
		}
		msg="该脸已被占用=.=";
		return SUCCESS;
	}
	
	public String addFace(){
		//FaceEntity myface = (FaceEntity) getRequest().get("face");
		if (face != null)			
			CheckHelper.getCheckHelper().addFace(face);
		return SUCCESS;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}