package action;

import java.util.List;

import org.hibernate.Transaction;

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
		TeacherHome teacherHome1 = new TeacherHome();
		Transaction trans = teacherHome1.createTransaction();
		Teacher teacher1 = new Teacher();
		List result1 = teacherHome1.findByExample(teacher1);
		trans.commit();
		getSession().put("result", result1);
		return SUCCESS;
		
		/*
		List result = null;
		/*
		 * 里面调用一些hibernate的方法之类的和数据库进行比对
		 * 然后若成功则返回"INDEX"登录的首页，失败则返回"ERROR"错误
		 
		if (identity.equals("teacher")){
			TeacherHome teacherHome = new TeacherHome();
			Teacher teacher = new Teacher();
			teacher.setTno(id);
			teacher.setTpwd(pwd);
			result = teacherHome.findByExample(teacher);
		}else if (identity.equals("student")){
			StudentHome studentHome = new StudentHome();
			Student student = new Student();
			student.setSno(id);
			student.setSpwd(pwd);
			result = studentHome.findByExample(student);	
		}
		if ( result.size() == 1 ) 
			return INDEX;
		else{
			getSession().put("login_msg","fail to login");
			return INDEX;
		}
		*/
		
	}
}