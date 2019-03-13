package com.gupaoedu.vip.spring.mini1.demo.action;


import com.gupaoedu.vip.spring.mini1.demo.service.IDemoService;
import com.gupaoedu.vip.spring.mini1.spring.annotation.Autowired;
import com.gupaoedu.vip.spring.mini1.spring.annotation.Controller;
import com.gupaoedu.vip.spring.mini1.spring.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/demo")
public class DemoAction {
	
	@Autowired("iDemoService")
	private IDemoService demoService;



	@RequestMapping("/query.json")
	public void query(HttpServletRequest req,HttpServletResponse resp,
		   String name){
		String result = demoService.get(name);
		System.out.println(result);
//		try {
//			resp.getWriter().write(result);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	@RequestMapping("/edit.json")
	public void edit(HttpServletRequest req,HttpServletResponse resp,Integer id){

	}


	public IDemoService getDemoService() {
		return demoService;
	}
	
}
