package com.incapp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.models.Book;
import com.incapp.repo.BookRepo;

@Service
public class BookService {
	@Autowired
	private BookRepo bookRepo;
	
	public List<Book> getBooks(String name){
		return bookRepo.getBooks(name);
	}
	
	public String login(String id,String password) {
		return bookRepo.login(id, password);
	}

	public boolean addBook(Book book) {
		return bookRepo.addBook(book);
	}
	
	public boolean addBookImage(String name, MultipartFile image) {
		return bookRepo.addBookImage(name, image);
	}
	
	public List<String> getBookNames(){
		return bookRepo.getBookNames();
	}
}
