package ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import beans.BillingAddressBean;
import beans.BookInCartBean;
import beans.Member;
import beans.ShippingAddressBean;
import common.SendEmail;
import service.BillService;

@Controller
public class CheckOutController {
	BillService bs = new BillService();
	@RequestMapping("checkout")
	public String checkOut(HttpServletRequest request) {
		String email = ((Member) request.getSession().getAttribute("member")).getEmail();
		BillingAddressBean ba = bs.getByEmail(email);
		request.getSession().setAttribute("ba", ba);
		return "checkOutPage";
	}
	
	@RequestMapping("processing")
	public String processing(BillingAddressBean ba, ShippingAddressBean sa, HttpServletRequest request) {
		int result = bs.processing(ba, sa, request);
		
		if (result == 0) {
			request.getSession().setAttribute("pay", "Credit Card Authorization Failed.");
			List<BookInCartBean> books = (List<BookInCartBean>) request.getSession().getAttribute("cart");
			Member member = (Member) request.getSession().getAttribute("member");
			SendEmail.send(books, result, member.getEmail());
		}else {
			
			List<BookInCartBean> books = (List<BookInCartBean>) request.getSession().getAttribute("cart");
			Member member = (Member) request.getSession().getAttribute("member");
			SendEmail.send(books, result, member.getEmail());
			
			List<BookInCartBean> cart = new ArrayList<BookInCartBean>(); 
			
			request.getSession().removeAttribute("cart");
			request.getSession().removeAttribute("subtotal");
			request.getSession().removeAttribute("total");
			request.getSession().removeAttribute("tax");
			request.getSession().removeAttribute("cartCount");
			request.getSession().setAttribute("pay", "Order Successfully Completed.");
		}
		return "StatusPage";
	}
}
