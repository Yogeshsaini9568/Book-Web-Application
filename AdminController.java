package com.incapp.controllers;





import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.client.RestTemplate;


import com.incapp.models.Admin;

import jakarta.servlet.http.HttpSession;


@Controller
public class AdminController {
	
	RestTemplate restTemplate=new RestTemplate();
	String URL="http://localhost:8077/";
	

	@ModelAttribute
	public void commonValues(ModelMap m) {
		m.addAttribute("appName","Book App");
	}
	
	@GetMapping("/login")
	public String login() {
		return "AdminLogin.jsp";
	}
	
	@PostMapping("/adminLogin")
	public String adminLogin(@RequestParam String id, @RequestParam String password, ModelMap m, HttpSession session) {
		String API="admin/login";
		Admin admin=new Admin();
		admin.setId(id);
		admin.setPassword(password);
		HttpEntity<Admin> requEntity=new HttpEntity(admin);
		String name=restTemplate.postForObject(URL+API, requEntity, String.class);
		if(name==null) {
			m.addAttribute("msg","Invalid Id or Password!");
			return "AdminLogin.jsp";
		}else {
			session.setAttribute("name", name);
			session.setAttribute("id", id);
			return "AdminLogin";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "AdminLogin.jsp";
	}
	
	@PostMapping("/updatePassword")
	public String updatePassword(String opass,String npass,String rpass,ModelMap m,HttpSession session) {
		String id=(String)session.getAttribute("id");
		
		String API="admin/updatePassword/"+opass+"/"+npass+"/"+rpass+"/"+id;
//		restTemplate.put(URL+API,null);
		ResponseEntity<String> result=restTemplate.exchange(URL+API, HttpMethod.PUT, null, String.class);
		String msg=result.getBody();
		int statusCode=result.getStatusCodeValue();
		m.addAttribute("msg",msg+"-"+statusCode);
		return "AdminHome.jsp";
	}
	
	@PostMapping("/forgetPassword")
	public String forgetPassword(String email,ModelMap m) {
		String API="admin/forgetPassword/"+email;
		ResponseEntity<String> result=restTemplate.exchange(URL+API,HttpMethod.POST, null,String.class);
		m.addAttribute("msg",result.getBody());
		return "AdminLogin.jsp";
	}

}
