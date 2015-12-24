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

import utils.Values;
import db.entity.CourseInfo;
import db.util.DBHelper;

public class teacherAction extends MyActionSupport{
	File pic;//用于获取教师上传的图片
	
	/**
	 * 在返回页面之前，需要从数据库中比对，查找当前要上的课，
	 * 然后把课程信息放到request里传递过去。
	 * 若当前时间段没有课，那么就返回NOCURRENTCLASS，跳转到无课页面。
	 */
	@Override
	public String index(){
		Map session = getSession();//获取session
		String tno = (String) session.get("id");//获取教工号
		List<CourseInfo> courses = new ArrayList<CourseInfo>();//课程链表，保存当前时间老师的课程信息
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
			session.put("classNum", count);
			if ( count == 0 ){//当天无课，返回NOCURRENTCLASS
				courses = DBHelper.getAllCoursesInfo("teacher",tno,coursesno,true);
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
	 * 修改老师点名的时长
	 * @return
	 */
	public String editChecktime(){
		DBHelper.editTcValue("11111111", "cs002", "checktime", 11);
		return SUCCESS;
	}
	
	public String getStudentInfoForAClass(){
		List result = DBHelper.getStudentInfoForAClassByCnoTno("cs001","1111114");
		return SUCCESS;
	}
	
	
	
	public String savePic(){
		//pic = new File("d:\\my_icon.jpg");
		//String path =  ServletActionContext.getServletContext().getRealPath("/");
		/*获取当前系统时间*/
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		String date = df.format(new Date());//获取当前日期
		String savePath = "d:\\teacher\\"+getSession().get("name")+""+date;
		String filePath = "d:\\teacher\\"+getSession().get("name")+""+date+"\\"+(Values.index++) +".jpg";
		if ( pic != null ){
			try {
				DataInputStream in = new DataInputStream(new FileInputStream(pic));
				File saveFile = new File(savePath);
				if ( !saveFile.exists())
					saveFile.mkdirs();
				DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath));
				/*将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址*/
	            byte[] buffer = new byte[4096];
	            int count = 0;
	            while ((count = in.read(buffer)) > 0)/*将输入流以字节的形式读取并写入buffer中*/
	                out.write(buffer, 0, count);
	            out.close();/*后面三行为关闭输入输出流以及网络资源的固定格式*/
	            in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
}
