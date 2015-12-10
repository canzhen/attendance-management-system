package action;

import java.util.Map;

public class teacherAction extends MyActionSupport{
	private Map request;
	/**
	 * 在返回页面之前，需要从数据库中比对，查找当前要上的课，
	 * 然后把课程信息放到request里传递过去。
	 * 若当前时间段没有课，那么就返回NOCURRENTCLASS，跳转到无课页面。
	 */
	@Override
	public String index(){
		request = getRequest();
		request.put("test", new Integer(1));//类似这样传值过去
		return SUCCESS;
	}
}
