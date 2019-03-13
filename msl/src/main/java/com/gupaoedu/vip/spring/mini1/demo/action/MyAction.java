package com.gupaoedu.vip.spring.mini1.demo.action;


import com.gupaoedu.vip.spring.mini1.demo.service.IDemoService;
import com.gupaoedu.vip.spring.mini1.spring.annotation.Autowired;
import com.gupaoedu.vip.spring.mini1.spring.annotation.Controller;
import com.gupaoedu.vip.spring.mini1.spring.annotation.RequestMapping;

@Controller
public class MyAction {

		@Autowired
        IDemoService demoService;
	
		@RequestMapping("/index.html")
		public void query(){

		}
	
}
