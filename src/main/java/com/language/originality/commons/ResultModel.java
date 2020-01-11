package com.language.originality.commons;


import com.language.originality.enums.ResultStatusEnum;

/**
 * 自定义通用返回结果类
 * @author hughes
 */
public class ResultModel<T> {

	/**
	 * 返回码
	 */
	private int code;

	/**
	 * 返回结果描述
	 */
	private String message;

	/**
	 * 返回内容
	 */
	private T content;

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getContent() {
		return content;
	}

	public ResultModel() {

	}
	
	public ResultModel(int code) {
		this.code = code;
		this.message = "";
		this.content = null;
	}

	public ResultModel(int code, String message) {
		this.code = code;
		this.message = message;
		this.content = null;
	}

	public ResultModel(int code, String message, T content) {
		this.code = code;
		this.message = message;
		this.content = content;
	}

	public ResultModel(ResultStatusEnum status) {
		this.code = status.getCode();
		this.message = status.getMessage();
		this.content = null;
	}

	public ResultModel(ResultStatusEnum status, T content) {
		this.code = status.getCode();
		this.message = status.getMessage();
		this.content = content;
	}

	public static ResultModel success(Object content) {
		return new ResultModel(ResultStatusEnum.OPERATION_SUCCESS, content);
	}

	public static ResultModel success(ResultStatusEnum resultStatus) {
		return new ResultModel(resultStatus);
	}

	public static ResultModel success() {
		return new ResultModel(ResultStatusEnum.OPERATION_SUCCESS);
	}

	public static ResultModel fail(ResultStatusEnum error) {
		return new ResultModel(error);
	}

	public static ResultModel fail(ResultStatusEnum error, Object content) {
		return new ResultModel(error, content);
	}

	public static ResultModel fail(int errorCode, String content) {
		return new ResultModel(errorCode,content);
	}

}
