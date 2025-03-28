package com.incapp.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.models.Book;
import com.incapp.repo.BookRepo;
import com.incapp.services.AppService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
	@Autowired
	private AppService appService;
	@ModelAttribute
	public void commonValues(ModelMap m) {
		m.addAttribute("appName","Book App");
	}
	@GetMapping("/getBooks")
	public String getBooks(@RequestParam String name,ModelMap m) {
		List<Book> list=appService.getBooks(name);
		m.addAttribute("books",list);
		return "ViewBooks.jsp";
	}
	@GetMapping("/allBook")
	public String allBook(ModelMap m,HttpSession session) {
		String name=(String)session.getAttribute("name");
		if(name==null) {
			m.addAttribute("msg","Please Login First!");
			return "AdminLogin.jsp";
		}
		List<Book> books=appService.getBooks();
		m.addAttribute("books",books);
		return "AllBooks.jsp";
	}

	@PostMapping("/addBook")
	public String addBook(Book book,@RequestPart("photo") MultipartFile photo,@RequestPart("data") MultipartFile data,ModelMap m) throws IOException{
		book.setImage(photo.getBytes());
		book.setContent(data.getBytes());
		boolean result=appService.addBook(book);
		if(result) {
			m.addAttribute("msg","Book added successfully!");
		}else {
			m.addAttribute("msg","Book name already exist!");
		}
		return "AdminHome.jsp";
	}

	@PostMapping("/updateBookImage")
	public String updateBookImage(String name,@RequestPart("image") MultipartFile photo,ModelMap m) {
		appService.updateBookImage(name,photo);
		m.addAttribute("msg","Book Image updated successfully!");
		Book book=appService.getBook(name);
		m.addAttribute("b",book);
		return "EditBook.jsp";
	}
	
	@GetMapping("/getBookImage")
	public void getBookImage(String name,HttpServletResponse response ) throws IOException {
		byte image[]=appService.getBookImage(name);
		if(image==null || image.length==0 ) {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream("static/book.png");
			image=is.readAllBytes();
		}
		response.getOutputStream().write(image);
	}
	@PostMapping("/editBook")
	public String editBook(String name,ModelMap m) {
		Book book=appService.getBook(name);
		m.addAttribute("b",book);
		return "EditBook.jsp";
	}
	@PostMapping("/updateBook")
	public String updateBook(Book book,ModelMap m) {
		appService.updateBook(book);
		m.addAttribute("msg","Book updated successfully!");
		m.addAttribute("b",book);
		return "EditBook.jsp";
	}
	@PostMapping("/deleteBook")
	public String deleteBook(String name,ModelMap m) {
		appService.deleteBook(name);
		m.addAttribute("msg","Book deleted successfully!");
		List<Book> books=appService.getBooks();
		m.addAttribute("books",books);
		return "AllBooks.jsp";
	}
	@PostMapping("/downloadBook")
	public String downloadBook(String name,HttpServletResponse response, ModelMap m) throws IOException {
		byte content[]=appService.getBookContent(name);
		if(content==null || content.length==0 ) {
			m.addAttribute("msg","Book Content Not Available!");
		}else {
			response.setContentType("APPLICATION/OCTET-STREAM");
			response.setHeader("Content-Disposition","attachment; filename=" + name+".pdf" );
			response.getOutputStream().write(content);
		}
		List<Book> books=appService.getBooks();
		m.addAttribute("books",books);
		return "AllBooks.jsp";
	}
	@PostMapping("/viewBook")
	public String viewBook(String name,HttpServletResponse response, ModelMap m) throws IOException {
		byte content[]=appService.getBookContent(name);
		if(content==null || content.length==0 ) {
			m.addAttribute("msg","Book Content Not Available!");
		}else {
			response.setContentType("application/pdf");
			response.addHeader("content-disposition", "inline; filename=" + name+".pdf");
			response.getOutputStream().write(content);
		}
		List<Book> books=appService.getBooks();
		m.addAttribute("books",books);
		return "AllBooks.jsp";
	}
	
}
