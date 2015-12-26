package action;

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
	private String identity="";
	/**
	 * 用户名，可以是老师 的也可以是学生的
	 */
	private String id="";

	/**
	 * 密码
	 */
	private String pwd="";
	
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
		/*
		 * 根据老师或者学生身份的不同进行比对，
		 * 并把老师或学生编号存入session
		 */
		if (identity.equals("teacher")){

			TeacherHome teacherHome = new TeacherHome();
			trans = teacherHome.createTransaction();
			Teacher teacher  = teacherHome.findById(id);
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

		}else if (identity.equals("manager")){
			if ( !id.equals("00000000")) return SUCCESS;
			else {
				TeacherHome teacherHome = new TeacherHome();
				trans = teacherHome.createTransaction();
				Teacher teacher = teacherHome.findById(id);
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
			}
			
		}else if (identity.equals("student")){

			StudentHome studentHome = new StudentHome();
			trans = studentHome.createTransaction();
			Student student = studentHome.findById(id);
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
		Map session = getSession();
		session.put("identity", identity);
		session.put("id", id);
		session.put("name", name);
		return SUCCESS;

	}
	
	public String logout(){
		msg = "登出成功！";
		Map session = getSession();
		session.put("identity",null);
		session.put("id", null);
		session.put("name", null);
		session.put("url", null);
		session.put("count", null);
		session.put("coursesInfo", null);
		session.put("coursesNum", null);
		session.put("daojishi", null);
		return SUCCESS;
	}
	
	public String getIdentity() {
		return identity;
	}


	public void setIdentity(String identity) {
		this.identity = identity;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getMsg() {
		return msg;
	}


	public void setMsg(String msg) {
		this.msg = msg;
	}
		
}