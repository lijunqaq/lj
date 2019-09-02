
package com.yizhu.lj.romote;

/**
 * restTemplate调用异常信息
 */
public class InvokeException extends RuntimeException {

	private static final long serialVersionUID = 8116762683282303637L;

	public InvokeException(String msg) {
		super(msg);
	}

	public InvokeException(String msg, Throwable t) {
		super(msg, t);
	}
}
