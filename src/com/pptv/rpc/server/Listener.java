package com.pptv.rpc.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

import com.pptv.rpc.bean.Invocation;

public class Listener extends Thread {

	private ServerSocket socket;
	private RpcServer server;

	public Listener(RpcServer server) {
		this.server = server;
	}

	@Override
	public void run() {
		try {
			socket = new ServerSocket(5050);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (server.isFlag()) {
			try {
				Socket client = socket.accept();
				ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
				Invocation inv = (Invocation) ois.readObject();
				server.call(inv);
				ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(inv);
				oos.flush();
				oos.close();
				ois.close();
			}  catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			if (socket != null && !socket.isClosed())
				socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
