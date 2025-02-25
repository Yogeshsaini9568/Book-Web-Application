package com.incapp.controllers;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.models.Book;
import com.incapp.services.BookService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Controller
public class BookController {
	
	RestTemplate restTemplate=new RestTemplate();
	String URL="http://localhost:8077/";
	

	@ModelAttribute
	public void commonValues(ModelMap m) {
		m.addAttribute("appName","Book App");
	}
	
	@PostMapping("/getBooks")
	public String getBooks(@RequestParam String name, ModelMap m) {
		String API="getBooks/"+name;
		List<Book> list=restTemplate.getForObject(URL+API,List.class);
		m.addAttribute("books",list);
		return "ViewBooks.jsp";
	}
	
	@GetMapping("/allBook")
	public String allBook(ModelMap m,HttpSession session) {
		String name=(String)session.getAttribute("name");
		if(name==null) {
			m.addAttribute("msg","Please Logon First!");
			return "AdminLogin.jsp";
		}
		String API="getBooks";
		ResponseEntity<List> result=restTemplate.exchange(URL+API, HttpMethod.GET,null,List.class);
		List<String[]>books=result.getBody();
		m.addAttribute("books",books);
		return "AllBooks.jsp";
	}
	
	@GetMapping("/getBookImage")
	public void getBookImage(String name,HttpServletResponse respons) throws IOException {
		String API="getBookImage";
		ResponseEntity<byte[]> result=restTemplate.exchange(URL+API, HttpMethod.GET, null,byte[].class);
		byte image[]=result.getBody();
		if(image==null || image.length==0) {
			InputStream is =this.getClass().getClassLoader().getResourceAsStream("static/book.png");
			image=is.readAllBytes();
		}
		respons.getOutputStream().write(image);
	}
	

	

}
