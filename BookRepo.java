package com.incapp.repo;

import java.io.IOException;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.incapp.models.Admin;
import com.incapp.models.Book;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Repository
public class BookRepo {
	@Autowired
	private EntityManager entityManager;
	
	public List<Book> getBooks(String name) {
		Session session= entityManager.unwrap(Session.class);
		List<Book> list=session.createQuery("select b from Book b WHERE b.name like :name", Book.class).setParameter("name", "%"+name+"%").list();
		return list;
	}
	
	public List<String> getBookNames() {
		Session session= entityManager.unwrap(Session.class);
		List<String> list=session.createQuery("select b.name from Book b Order by name", String.class).list();
		return list;
	}
	
	public String login(String id,String password) {
		Session session= entityManager.unwrap(Session.class);
		Admin a=session.get(Admin.class,id);
		if(a==null || !password.equals(a.getPassword())) {
			return null;
		}
		return a.getName();
	}
	
	@Transactional
	public boolean addBook(Book book) {
		Session session= entityManager.unwrap(Session.class);
		session.persist(book);
		return true;
	}
	
	@Transactional
	public boolean addBookImage(String name, MultipartFile image) {
		Session session=entityManager.unwrap(Session.class);
		Book book=session.get(Book.class, name);
		try {
			book.setImage(image.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.persist(book);
		return true;
	}
	 
}
