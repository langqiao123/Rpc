package com.pptv.rpc.server;

import com.pptv.rpc.bean.User;
import com.pptv.rpc.bean.UserFacatde;

public class MainServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		RpcServer rpcServer = new RpcServer();
		rpcServer.register(UserFacatde.class, User.class);
		rpcServer.start();
	}

}
