package com.pptv.rpc.bean;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class User implements UserFacatde {

	@Override
	public void say(String name) {
		System.out.println(" name:===" + name);
	}

}
