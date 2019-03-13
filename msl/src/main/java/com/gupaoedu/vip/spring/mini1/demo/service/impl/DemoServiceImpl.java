package com.gupaoedu.vip.spring.mini1.demo.service.impl;


import com.gupaoedu.vip.spring.mini1.demo.service.IDemoService;
import com.gupaoedu.vip.spring.mini1.spring.annotation.Service;

@Service
public class DemoServiceImpl implements IDemoService {

	public String get(String name) {
		return "My name is " + name;
	}

	public static void main(String[] args) {
		System.out.println(1);
	}

}
