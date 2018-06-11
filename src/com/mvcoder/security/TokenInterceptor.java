package com.mvcoder.security;

import com.mvcoder.utils.Constants;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import org.apache.struts2.StrutsStatics;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TokenInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 1L;

	private TokenManager manager;

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// TODO Auto-generated method stub
		ActionContext actionContext = invocation.getInvocationContext();
		HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
		System.out.println("URL : "+request.getRequestURI());
		Cookie[] cookies = request.getCookies();
		int code = Constants.StateCode.COOKIE_INVAILD;
		String msg = null;
		TokenBody body = null;
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals(Constants.TOKEN_NAME)) {
					body = manager.decryptJWT(c.getValue());
					if (body == null) {
						code = Constants.StateCode.COOKIE_INVAILD;
						msg = "token invalid";
					} else if (body.getExp() < System.currentTimeMillis()) {
						code = Constants.StateCode.COOKIE_TIME_OUT;
						msg = "token timeout,please login again";
					} else {
						code = Constants.StateCode.COOKIE_VAILD;
					}
				}
			}
		}else{
			System.out.println("cookie is null");
		}
		if (code == Constants.StateCode.COOKIE_VAILD) {
			actionContext.put("token", body);
			return invocation.invoke();
		}
		HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);
		response.setContentType("application/json;charset=utf-8");
		Cookie cookie = new Cookie(Constants.TOKEN_NAME, "");
		cookie.setPath("/pdchecker");
		response.addCookie(cookie);
		// PrintWriter writer = response.getWriter();
		//response.sendError(code, msg);
		/*
		 * result = new Result<>(); result.setCode(code); result.setMsg(msg);
		 * Gson gson = new Gson(); writer.print(gson.toJson(result));
		 * writer.flush();
		 */
		return ActionSupport.ERROR;
	}

	public TokenManager getManager() {
		return manager;
	}

	public void setManager(TokenManager manager) {
		this.manager = manager;
	}

}
