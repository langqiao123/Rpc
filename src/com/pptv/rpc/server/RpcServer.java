package com.pptv.rpc.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.pptv.rpc.bean.Invocation;

public class RpcServer {

	private volatile boolean flag = false;
	
	private Map<String, Object> serviceEngine = new HashMap<String, Object>();
	
	public void start(){
		this.setFlag(true);
		Listener listener = new Listener(this);
		listener.start();
	}
	
	public void stop(){
		this.setFlag(false);
	}
	
	public void register(Class interfaceDefiner, Class impl){
		try {
			serviceEngine.put(interfaceDefiner.getName(), impl.newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void call(Invocation inv) {
		Object obj = serviceEngine.get(inv.getInterfaceObj().getName());
		if(obj != null){
			try {
				Method method = obj.getClass().getMethod(inv.getMethod().getMethodName(), inv.getMethod().getParams());
				Object result = method.invoke(obj, inv.getParams());
				inv.setResult(result);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
