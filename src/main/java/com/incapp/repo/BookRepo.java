package com.incapp.repo;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
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
		List<Book> list=session.createQuery("select b from Book b where b.name like :name",Book.class)
		.setParameter("name", "%"+name+"%")
		.list();
		return list;
	}
	public Book getBook(String name) {
		Session session= entityManager.unwrap(Session.class);
		Book book=session.get(Book.class,name);
		return book;
	}
	public List<String> getBookNames() {
		Session session= entityManager.unwrap(Session.class);
		List<String> list=session.createQuery("select b.name from Book b ",String.class)
		.list();
		
		return list;
	}
	public List<Book> getBooks() {
		Session session= entityManager.unwrap(Session.class);
		List<Book> list=session.createQuery("select b from Book b ",Book.class)
		.list();
		return list;
	}
	@Transactional
	public boolean addBook(Book book) {
		Session session= entityManager.unwrap(Session.class);
		Book b=session.get(Book.class, book.getName());
		if(b==null) {
			session.persist(book);
			return true;
		}else {
			return false;
		}
		
	}
	@Transactional
	public void updateBookImage(String name,MultipartFile image) {
		Session session= entityManager.unwrap(Session.class);
		Book book=session.get(Book.class, name);
		try {
			book.setImage(image.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		session.persist(book);
	}
	@Transactional
	public void updateBook(Book book){
		Session session= entityManager.unwrap(Session.class);
		Book b=session.get(Book.class, book.getName());
		b.setPrice(book.getPrice());
		b.setAname(book.getAname());
		b.setPname(book.getPname());
		session.persist(b);
	}
	@Transactional
	public void deleteBook(String name){
		Session session= entityManager.unwrap(Session.class);
		Book book=session.get(Book.class, name);
		session.remove(book);
	}
	public byte[] getBookImage(String name) {
		Session session= entityManager.unwrap(Session.class);
		Book book=session.get(Book.class, name);
		return book.getImage();
    }
	public byte[] getBookContent(String name) {
		Session session= entityManager.unwrap(Session.class);
		Book book=session.get(Book.class, name);
		return book.getContent();
    }
}
