package com.mvcoder.bean;

import com.mvcoder.utils.Constants;

import java.io.Serializable;

public class Result<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private T data;
	
	public Result(int code,T data){
		super();
		this.code = code;
		this.data = data;
	}
	
	public Result(T data) {
		this.code = Constants.StateCode.OK;
		this.data = data;
	}
	
	public Result(){
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
}
