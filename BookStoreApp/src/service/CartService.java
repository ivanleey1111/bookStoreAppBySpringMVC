package service;

import java.sql.SQLException;
import java.util.List;

import beans.BookBean;
import beans.BookInCartBean;
import dao.ContentDao;

public class CartService {
	ContentDao contentDao = new ContentDao();
	public BookBean getBookByID(String bid) {
		
		BookBean bookBean = null;
		try {
			bookBean = contentDao.getByBookId(bid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bookBean;
		
	}
	
	
	//check the cart has this book or not! if has, return the index of this book in the cart.
	
		public int check(List<BookInCartBean> cart,BookBean bookBean) {
			int result = -1;
			for(int i = 0;i<cart.size();i++) {
			
				if (cart.get(i).getBid().equals(bookBean.getBid())) {
					return i;
				}
			}
			return result;
		}
		
		public int getIndexInCart(String bid, List<BookInCartBean> list) {
			int result = -1;
			for(int i = 0;i<list.size();i++) {
				if (list.get(i).getBid().equals(bid)) {
					result = i;
				}
			}
			
			return result;
		}
		
		public int countCart(List<BookInCartBean> list) {
			int count = 0;
			for(int i = 0;i<list.size();i++) {
				count += list.get(i).getItemcount();
			}
			return count;
		}
		
		public double subtotal(List<BookInCartBean> list) {
			double amount = 0;
			for(int i = 0;i<list.size();i++) {
				amount += list.get(i).getItemcount() * list.get(i).getPrice();
			}
			return amount;
		}
		
		public double tax(double amount) {
			
			
			return  Math.round((amount * 0.13) * 100.0)/100.0;
		}
		
		
}
