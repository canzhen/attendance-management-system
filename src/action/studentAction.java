package action;

import java.util.List;
import java.util.Map;

import dbentity.Sc;
import dbentity.ScHome;

public class studentAction extends MyActionSupport{
	private Map session;
	private String sno;//学号
	/**
	 * 在返回页面之前，需要从数据库中比对，查找当前要上的课，
	 * 然后把课程信息放到request里传递过去。
	 * 若当前时间有课，那么还要判断老师是否已经上传点名图片，
	 * 若有，则也要放到request里传递到网页端。
	 * 若当前时间段没有课，那么就返回NOCURRENTCLASS，跳转到无课页面。
	 */
	@Override
	public String index(){
		session = getSession();
		if ( !session.get("identity").equals("student") ){
			return ERROR;
		}else{
			/*
			 * 先找到该学生所有的课的编号
			 
			List<String> classes;
			ScHome schome = new ScHome();
			sno = (String) session.get("id");
			Sc sc = new Sc();
			List<Sc> result = schome.findByExample(sc);
			for (int i = 0; i < result.size(); i++){
				classes.add(result.get(i).get)
			}
			*/
			return SUCCESS;
		}
	}
}