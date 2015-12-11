package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;

import dbentity.Sc;
import dbentity.ScHome;
import dbentity.ScId;
import dbentity.Student;
import dbentity.StudentHome;
import dbentity.Teacher;
import dbentity.TeacherHome;

/**
 * 老师和学生的登录类，
 * 若登录成功则继续，若失败则跳转到失败界面
 * @author 周灿桢
 *
 */
public class loginAction extends MyActionSupport{
	private Map session;
	/**
	 * 身份，老师或者学生
	 */
	private String identity="";
	/**
	 * 用户名，可以是老师的也可以是学生的
	 */
	private String id="";
	/**
	 * 密码
	 */
	private String pwd="";
	
	
	/**
	 * 和数据库进行比对，
	 * 查看用户名密码是否存在并且正确
	 * @return
	 */
	public String check(){
		
		List result = null;
		Transaction trans = null;
		session = getSession();
		/*
		 * 根据老师或者学生身份的不同进行比对，
		 * 并把老师或学生编号存入session
		 */
		if (identity.equals("teacher")){
			
			session.put("identity", "teacher");
			
			TeacherHome teacherHome = new TeacherHome();
			trans = teacherHome.createTransaction();
			Teacher teacher = new Teacher();
			teacher.setTno(id);
			teacher.setTpwd(pwd);
			result = teacherHome.findByExample(teacher);
			trans.commit();
			
		}else if (identity.equals("student")){
			
			session.put("identity", "student");
			
			StudentHome studentHome = new StudentHome();
			trans = studentHome.createTransaction();
			Student student = new Student();
			student.setSno(id);
			student.setSpwd(pwd);
			result = studentHome.findByExample(student);
			trans.commit();
		}
		
		/*
		 * 保存id信息到session中
		 */
		session.put("id", identity);
		
		/*
		 * 判断登录是否成功，并在session里放入"login_result"
		 * 以便界面判断是否登录成功
		 * 0代表登录成功，1代表登录失败
		 */
		if ( result.size() == 1 ){
			session.put("login_result",0);
			return SUCCESS;
		}else{
			session.put("login_result",1);
			return SUCCESS;
		}
		
	}
}