package cn.itheima.ssm.exception;

public class MyException extends Exception {

	// 异常消息
	private String message;
	
	public MyException(String message) {
		super();
		this.message = message;
	}
	public MyException() {
		super();
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessage() {
		return message;
	}

}
