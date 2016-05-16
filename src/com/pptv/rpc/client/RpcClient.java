package com.pptv.rpc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;
import java.net.UnknownHostException;

import com.pptv.rpc.bean.Invocation;

public class RpcClient {

	public Object getProxy(final Class clazz, final int port, final String host){
		return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[] {clazz},new InvocationHandler(){@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			Invocation invocation = new Invocation();
			invocation.setInterfaceObj(clazz);
			invocation.setMethod(new com.pptv.rpc.bean.Method(method.getName(), method.getParameterTypes()));
			invocation.setParams(args);
			Client client = new Client(host, port);
			client.invoke(invocation);
			return invocation.getResult();
		}});
	}
	
	class Client {
		private int port;
		private String host;
		private Socket socket;
		private ObjectInputStream ois;
		private ObjectOutputStream oos;
		public Client(String host, int port) {
			this.host = host;
			this.port = port;
		}
		
		public void init(){
			try {
				socket = new Socket(host, port);
				oos = new ObjectOutputStream(socket.getOutputStream());
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void invoke(Invocation invocation){
			init();
			try {
				oos.writeObject(invocation);
				oos.flush();
				ois = new ObjectInputStream(socket.getInputStream());
				Invocation inv = (Invocation) ois.readObject();
				invocation.setResult(inv.getResult());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if((socket!=null) && !socket.isClosed() ){
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
