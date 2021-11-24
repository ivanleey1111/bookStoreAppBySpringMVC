package ctrl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import beans.BillingAddressBean;
import beans.Member;
import common.Messages;
import service.RegisterService;

@Controller
public class RegisterController {
	RegisterService rs = new RegisterService();
	@RequestMapping("register")
	@ResponseBody
	public Messages register(Member member,HttpServletRequest request,String address,String city, String province,String zip){
		//System.out.println(member.getEmail());
		System.out.println(address);
		System.out.println(city);
		BillingAddressBean ba = new BillingAddressBean(member.getEmail(), member.getFirstname() + " " + member.getLastname(), address, city, province, zip);
		Messages messages = rs.retrivebyEmail(member,ba);
		//request.getSession().setAttribute("messages", messages);
		return messages;
	}
}
