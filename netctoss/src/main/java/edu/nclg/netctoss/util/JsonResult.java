package edu.nclg.netctoss.util;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * �û���װJSON�������ݵ�ֵ�Ķ�����
 * 
 * @JsonInclude ��springmvc�еı�ע����Ϊ�˿��Ʒ��ص�json�ַ�����ʾ��Щ�ֶ�
 * @JsonInclude(JsonInclude.Include.NON_NULL) �����������Ϊnull���ֶβ���ʾ
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JsonResult <T> implements Serializable{
	
	private static final long serialVersionUID = 2186688948383625192L;
	
	public static final String SUCCESS = "success";
	public static final String ERROR = "error";
	
	private String state; //״̬
	private String message; //��Ϣ
	private T data; //����ֵ
	
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
