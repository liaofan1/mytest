package cn.itheima.ssm.intercept;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class FirstHandlerInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
		System.out.println("FirstHandlerInterceptor-afterCompletion执行中......");
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView mav)
			throws Exception {
		System.out.println("FirstHandlerInterceptor-postHandle执行中......");
		mav.addObject("message", "我是拦截器方法postHandle......");
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		System.out.println("FirstHandlerInterceptor-preHandle执行中......");
		return true;
	}

}
