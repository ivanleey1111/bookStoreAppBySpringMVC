package service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.BookBean;
import beans.Member;
import beans.Review;
import dao.ContentDao;
import dao.MembersDao;

public class ContentService {
	
	ContentDao contentDao = new ContentDao();
	MembersDao membersDao = new MembersDao();
	public List<String> getAllCategories(){
		List<String> list = null;
		try {
			list = contentDao.getAllCategory();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<BookBean> getAllBooks(){
		List<BookBean> books = null;
		
		try {
			books = contentDao.getAllBooks();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	
	public List<BookBean> getByCategory(String category) {
		List<BookBean> books = null;
		try {
			books = contentDao.getByCategory(category);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	
	public BookBean getByBookId(String bid) {
		BookBean bookBean = null;
		
		try {
			bookBean = contentDao.getByBookId(bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return bookBean;
		
	}
	
	public List<Review> getReviewByBookId(String bid) {
		List<Review> result = new ArrayList<Review>();
		try {
			result = contentDao.getReviewsByBookId(bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(Review review : result) {
			try {
				Member member = membersDao.byEmail(review.getEmail());
				review.setMember(member);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int insertReview(Review review) {
		int result = 0;
		try {
			result = contentDao.insertReview(review);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<BookBean> searchByTitle(String title){
		List<BookBean> list = null;
		try {
			list = contentDao.searchByTitle(title);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}	
