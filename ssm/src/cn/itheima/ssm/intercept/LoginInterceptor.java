package cn.itheima.ssm.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object hander) throws Exception {
		// TODO Auto-generated method stub
		// 1.获取session对象
		HttpSession session = request.getSession();
		// 2.从session对象中，获取用户对象
		Object user = session.getAttribute("user");
		// 3.检查用户是否登录
		if (user!=null) {
			System.out.println("用户已经登录，放心......");
			return true;
		}
		// 4用户未登录
		System.out.println("用户未登录，请先登录......");
		// 使用response重定向，需要加上工程的上下文路径
		response.sendRedirect(request.getContextPath()+"/toLogin.do");		
		return false;
	}

}
