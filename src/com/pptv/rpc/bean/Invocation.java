package com.pptv.rpc.bean;

import java.io.Serializable;


public class Invocation implements Serializable{

	private Class interfaceObj ;
	
	private Method method;
	
	private Object[] params;
	
	private Object result;

	public Class getInterfaceObj() {
		return interfaceObj;
	}

	public void setInterfaceObj(Class interfaceObj) {
		this.interfaceObj = interfaceObj;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object[] getParams() {
		return params;
	}

	public void setParams(Object[] params) {
		this.params = params;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
