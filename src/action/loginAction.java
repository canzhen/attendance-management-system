package action;

/**
 * 老师和学生的登录类，
 * 若登录成功则继续，若失败则跳转到失败界面
 * @author lenovo
 *
 */
public class loginAction extends MyActionSupport{
	/**
	 * 和数据库进行比对，
	 * 查看用户名密码是否存在并且正确
	 * @return
	 */
	public String check(){
		/*
		 * 里面调用一些hibernate的方法之类的和数据库进行比对
		 * 然后若成功则返回"INDEX"登录的首页，失败则返回"ERROR"错误
		 */
		return INDEX;
	}
}