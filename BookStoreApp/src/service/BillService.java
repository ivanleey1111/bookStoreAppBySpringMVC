package service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import beans.AnalyticBean;
import beans.BillingAddressBean;
import beans.BookInCartBean;
import beans.Member;
import beans.ShippingAddressBean;
import dao.BillDao;

public class BillService {
	
	BillDao billDao = new BillDao();
	public BillingAddressBean getByEmail(String email) {
		
		BillingAddressBean ba = null;
		
		try {
			ba = billDao.getByEmail(email);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ba;
	}
	
	public int processing(BillingAddressBean ba, ShippingAddressBean sa,HttpServletRequest request) {
		int result = 0;
		try {
			billDao.updateBA(ba);
			billDao.updateSA(sa);
			int cardvalid = (int) request.getServletContext().getAttribute("cardvalid");
			Member member = (Member) request.getSession().getAttribute("member");
			List<BookInCartBean> cart = (List<BookInCartBean>) request.getSession().getAttribute("cart");
			if (cardvalid % 3 != 0) {
				billDao.insertPO("ORDERED", member.getEmail(), request.getSession().getAttribute("total").toString());
				for(BookInCartBean bicb : cart) {
					billDao.insertPOItem(bicb.getBid(), bicb.getItemcount());
					billDao.updateBookCount(bicb.getItemcount(), bicb.getBid());
					//add to listener
					AnalyticBean ab = new AnalyticBean();
					ab.setBid(bicb.getBid());
					Date date = new Date();
					String day = (date.getMonth() + 1) + "/" + date.getDate() + "/" + (date.getYear() + 1900);
					ab.setDay(day);
					ab.setEventType("PURCHASE");
					request.getSession().setAttribute("ab", ab);
				}
				
				result = 1;
				
			}else {
				billDao.insertPO("DENIED", member.getEmail(), request.getSession().getAttribute("total").toString());
				
			}
			cardvalid++;
			request.getServletContext().setAttribute("cardvalid", cardvalid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
}
