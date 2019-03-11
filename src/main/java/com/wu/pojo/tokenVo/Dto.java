package com.wu.pojo.tokenVo;

/**
 * 数据传输对象（后端输出对象）
 * @param <T>
 * Created by hanlu on 2017/5/7.
 */
public class Dto<T>{
	private String success; //判断系统是否出错做出相应的true或者false的返回，与业务无关，出现的各种异常
	private String errorCode;//该错误码为自定义，一般0表示无错
	private String msg;//对应的提示信息
	private String status;//状态
	private T data;//具体返回数据内容(pojo、自定义VO、其他)

	private T obj;

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public String getSuccess() {
		return success;
	}
	public void setSuccess(String success) {
		this.success = success;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}