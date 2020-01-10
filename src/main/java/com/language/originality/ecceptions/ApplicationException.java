package com.language.originality.ecceptions;

import com.language.originality.enums.ResultStatusEnum;

/**
 * 自定义异常，用于某些判断或校验手动抛出
 *
 * @author mujinbao
 * @createTime 2018/9/12
 *
 */
public class ApplicationException extends RuntimeException {

	private int errorCode;

	private static final long serialVersionUID = 8098760580872738239L;

	public ApplicationException(String errorMessage) {
		super(errorMessage);
	}

	public ApplicationException(Throwable throwable) {
		super(throwable);
	}

	public ApplicationException(String errorMessage, Throwable throwable) {
		super(errorMessage, throwable);
	}

	public ApplicationException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public ApplicationException(ResultStatusEnum resultStatusEnum) {
		super(resultStatusEnum.getMessage());
		this.errorCode = resultStatusEnum.getCode();
	}

	public int getErrorCode() {
		return errorCode;
	}

}
