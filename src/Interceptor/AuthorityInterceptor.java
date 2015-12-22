package Interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor{

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		String id = (String)invocation.getInvocationContext().getSession().get("id");
		if(id!=null){
			return invocation.invoke();
		}else{
			return "welcome";
		}
	}

}
