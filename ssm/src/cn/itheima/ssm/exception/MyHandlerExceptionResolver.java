package cn.itheima.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class MyHandlerExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception exception) {
		// 1.自定义异常
		MyException myException =null;
		if (exception instanceof MyException) {
			myException = (MyException)exception;
		}else {
			myException = new MyException("未知异常");
		}
		
		// 2.包装异常页面
		ModelAndView mav = new ModelAndView();
		mav.addObject("message", myException.getMessage());
		mav.setViewName("error/error");
		return mav;
	}
	
	

}
