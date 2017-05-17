package edu.nclg.netctoss.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * 用户封装JSON返回数据的值的对象类
 * 
 * @JsonInclude 是springmvc中的标注，是为了控制返回的json字符串显示哪些字段
 * @JsonInclude(JsonInclude.Include.NON_NULL) 这里的设置是为null的字段不显示
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult <T> implements Serializable{
	
	private static final long serialVersionUID = 2186688948383625192L;
	
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	
	private String state; //状态
	private String message; //消息
	private T data; //返回值
	
	public JsonResult(){}
	public JsonResult(String state, String message, T data) {
		this.state = state;
		this.message = message;
		this.data = data;
	}
	public JsonResult(String state, String message) {
		this.state = state;
		this.message = message;
	}
	public JsonResult(String errorMassage){
		this(ERROR,errorMassage,null);
	}
	public JsonResult(Exception e){
		this(ERROR,e.getMessage(),null);
	}
	public JsonResult(T data){
		this(SUCCESS,null,data);
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "JsonResult [state=" + state + ", message=" + message + ", data=" + data + "]";
	}
	
	
}
