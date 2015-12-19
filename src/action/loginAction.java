package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Transaction;

import db.entity.Student;
import db.entity.StudentHome;
import db.entity.Teacher;
import db.entity.TeacherHome;

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
	private String identity="student";
	/**
	 * 用户名，可以是老师的也可以是学生的
	 */
	private String id="13301085";
	/**
	 * 密码
	 */
	private String pwd="111";

	/**
	 * 错误信息
	 */
	private String msg="";


	/**
	 * 和数据库进行比对，
	 * 查看用户名密码是否存在并且正确
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String check(){
		String name = "";
		Transaction trans;
		session = getSession();
		/*
		 * 根据老师或者学生身份的不同进行比对，
		 * 并把老师或学生编号存入session
		 */
		if (identity.equals("teacher")){

			TeacherHome teacherHome = new TeacherHome();
			trans = teacherHome.createTransaction();
			Teacher teacher ;
			teacher = teacherHome.findById(id);
			if(teacher==null){
				msg="该账号不存在";
				return SUCCESS;
			}
			if(!pwd.equals(teacher.getTpwd())){
				msg="密码错误";
				return SUCCESS;
			}
			name = teacher.getTname();
			trans.commit();

		}else if (identity.equals("student")){

			StudentHome studentHome = new StudentHome();
			trans = studentHome.createTransaction();
			Student student =null;
			student=studentHome.findById(id);
			if(student==null){
				msg="该账号不存在";
				return SUCCESS;
			}
			if(!pwd.equals(student.getSpwd())){
				msg="密码错误";
				return SUCCESS;
			}
			name = student.getSname();
			trans.commit();
		}


		/*
		 * 保存信息到session中
		 */
		session.put("identity", identity);
		session.put("id", id);
		session.put("name", name);
		return SUCCESS;

	}
		
}