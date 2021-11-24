package ctrl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import beans.AnalyticBean;
import beans.BookBean;
import beans.BookInCartBean;
import common.Messages;
import service.CartService;

@Controller
public class CartController {
	CartService cartservice = new CartService();
	@RequestMapping("cart")
	public String cart() {
		return "cart";
	}
	
	@RequestMapping("addToCart")
	@ResponseBody
	public Messages addToCart(String bid,HttpServletRequest request) {
		Messages messages = new Messages();
		List<BookInCartBean> cart = null;
		if (request.getSession().getAttribute("cart") == null) {
			cart = new ArrayList<BookInCartBean>();
			
		}else {
			cart = (List<BookInCartBean>) request.getSession().getAttribute("cart");
		}
		
		BookBean bookBean = cartservice.getBookByID(bid);
		
		int index = cartservice.check(cart, bookBean);
		if (index != -1) {
			if (cart.get(index).getItemcount() + 1 <= bookBean.getCount()) {
				cart.get(index).setItemcount(cart.get(index).getItemcount() + 1);
				request.getSession().setAttribute("cart", cart);
				messages.setOkMessage("Add to cart successful!");
			}else {
				
				messages.setErrMessage("Exceed our Stock!");
				
			}
		}else {
			if (bookBean.getCount() > 0) {
				BookInCartBean bookInCartBean = new BookInCartBean(bid, bookBean.getTitle(), bookBean.getPrice(), bookBean.getCategory(), bookBean.getImg(), 1);
				cart.add(bookInCartBean);
				request.getSession().setAttribute("cart", cart);
				messages.setOkMessage("Add to cart successful!");
			}else {
				messages.setErrMessage("Exceed our Stock!");
			}
			
		}
		int cartCount = cartservice.countCart(cart);
		request.getSession().setAttribute("cartCount", cartCount);
		double subtotal = cartservice.subtotal(cart);
		request.getSession().setAttribute("subtotal", subtotal);
		double tax = cartservice.tax(subtotal);
		request.getSession().setAttribute("tax", tax);
		request.getSession().setAttribute("total", subtotal + tax);
		//add to listener
		AnalyticBean ab = new AnalyticBean();
		ab.setBid(bid);
		Date date = new Date();
		String day = (date.getMonth() + 1) + "/" + date.getDate() + "/" + (date.getYear() + 1900);
		ab.setDay(day);
		ab.setEventType("CART");
		request.getSession().setAttribute("ab", ab);
		return messages;
		
	}
	
	@RequestMapping("removeFromCart")
	@ResponseBody
	public Messages removeFromCart(String bid,HttpServletRequest request) {
		Messages messages = new Messages();
		List<BookInCartBean> list = (List<BookInCartBean>) request.getSession().getAttribute("cart");
		int index = cartservice.getIndexInCart(bid, list);
		list.remove(index);
		int cartCount = cartservice.countCart(list);
		request.getSession().setAttribute("cartCount", cartCount);
		messages.setOkMessage("Remove item successful!");
		double subtotal = cartservice.subtotal(list);
		request.getSession().setAttribute("subtotal", subtotal);
		double tax = cartservice.tax(subtotal);
		request.getSession().setAttribute("tax", tax);
		request.getSession().setAttribute("total", subtotal + tax);
		return messages;
		
	}
	
	@RequestMapping("minusFromCart")
	@ResponseBody
	public Messages minusFromCart(String bid,HttpServletRequest request) {
		Messages messages = new Messages();
		List<BookInCartBean> list = (List<BookInCartBean>) request.getSession().getAttribute("cart");
		int index = cartservice.getIndexInCart(bid, list);
		if (list.get(index).getItemcount() - 1 == 0) {
			list.remove(index);
			int cartCount = cartservice.countCart(list);
			request.getSession().setAttribute("cartCount", cartCount);
			messages.setOkMessage("Remove item successful!");
		}else {
			list.get(index).setItemcount(list.get(index).getItemcount() - 1);
			int cartCount = cartservice.countCart(list);
			request.getSession().setAttribute("cartCount", cartCount);
			messages.setOkMessage("Remove 1 item from your cart Successful!");
		}
		double subtotal = cartservice.subtotal(list);
		request.getSession().setAttribute("subtotal", subtotal);
		double tax = cartservice.tax(subtotal);
		request.getSession().setAttribute("tax", tax);
		request.getSession().setAttribute("total", subtotal + tax);
		return messages;
		
	}
	
	
	
	
}
