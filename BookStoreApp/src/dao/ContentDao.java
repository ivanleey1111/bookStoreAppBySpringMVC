package dao;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.BookBean;
import beans.Member;
import beans.Review;

public class ContentDao {
	
	DataSource ds;
	public ContentDao() {
		// TODO Auto-generated constructor stub
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<String> getAllCategory() throws SQLException{
		
		List<String> categories = new ArrayList<String>();
		
		String query = "select distinct CATEGORY from BOOK";
		
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String em = r.getString("CATEGORY");
			
			categories.add(em);
		}
		r.close();
		p.close();
		con.close();
		return categories;
		
		
	}
	
	public List<BookBean> getAllBooks() throws SQLException {
		List<BookBean> books = new ArrayList<BookBean>();
		String query = "select * from BOOK";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String bid = r.getString("BID");
			String title = r.getString("TITLE");
			
			int price = r.getInt("PRICE");
			String category = r.getString("CATEGORY");
			String img = r.getString("IMG");
			int count = r.getInt("COUNT");
			books.add(new BookBean(bid, title, price, category, img, count));
			
		}
		return books;
		
	}
	
	public List<BookBean> getByCategory(String category) throws SQLException {
		List<BookBean> books = new ArrayList<BookBean>();
		BookBean bookBean = null;
		String query = "select * from BOOK where CATEGORY=" + "'" + category + "'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String bid = r.getString("BID");
			String title = r.getString("TITLE");
			
			int price = r.getInt("PRICE");
			String cate = r.getString("CATEGORY");
			String img = r.getString("IMG");
			int count = r.getInt("COUNT");
			books.add(new BookBean(bid, title, price, cate, img, count));
			
		}
		return books;
	}
	
	public BookBean getByBookId(String sid) throws SQLException {
		BookBean bookBean = null;
		String query = "select * from BOOK where BID=" + "'" + sid + "'";	
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String bid = r.getString("BID");
			String title = r.getString("TITLE");
			
			int price = r.getInt("PRICE");
			String cate = r.getString("CATEGORY");
			String img = r.getString("IMG");
			int count = r.getInt("COUNT");
			bookBean = new BookBean(bid, title, price, cate, img, count);
			
		}
		return bookBean;
	}
	
	public List<Review> getReviewsByBookId(String bid) throws SQLException {
		List<Review> result = new ArrayList<Review>();
		String query = "select * from REVIEWS where BID=" + "'" + bid + "'";
		Member member = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String bookid = r.getString("BID");
			String email = r.getString("EMAIL");
			
			
			
			String comment = r.getString("COMMENT");
			String rate = r.getString("RATING");
			
			result.add(new Review(bookid, email, comment,rate));
			
		}
		return result;
		
		
	}
	
	public int insertReview(Review review) throws SQLException {
		String preparedStatement ="insert into REVIEWS values(?,?,?,?)";
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		
		stmt.setString(1, review.getBid());
		stmt.setString(2, review.getEmail());
		stmt.setString(3, review.getComment());
		stmt.setString(4, review.getRating());
		
		
		return stmt.executeUpdate();
	}
	
	public List<BookBean> searchByTitle(String title) throws SQLException{
		String query = "select * from BOOK where UPPER (TITLE) like" + "'%" + title.toUpperCase() + "%'";
		List<BookBean> books = new ArrayList<BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while(r.next()) {
			String bid = r.getString("BID");
			String tit = r.getString("TITLE");
			
			int price = r.getInt("PRICE");
			String cate = r.getString("CATEGORY");
			String img = r.getString("IMG");
			int count = r.getInt("COUNT");
			books.add(new BookBean(bid, tit, price, cate, img, count));
			
		}
		return books;
	}
	
	
}
