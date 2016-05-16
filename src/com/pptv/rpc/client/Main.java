package com.pptv.rpc.client;

import com.pptv.rpc.bean.UserFacatde;

public class Main {

	public static void main(String[] args) {
		RpcClient rpcClient = new RpcClient();
		UserFacatde userFacatde = (UserFacatde)rpcClient.getProxy(UserFacatde.class,5050,"127.0.0.1");
		userFacatde.say("mengyakun");
	}
}
