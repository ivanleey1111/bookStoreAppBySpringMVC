package ctrl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import beans.Member;
import common.Messages;
import service.LoginService;



@Controller
public class LoginController {
	
	@RequestMapping("login")
	
	public String login(HttpServletRequest request)  {
		//System.out.println(testDao.testtt(20));
		if (request.getParameter("message") != null) {
			request.setAttribute("messages", "Register successful");
		}
		return "loginPage";
	}
	
	@RequestMapping("registerPage")
	
	public String register() {
		return "register";
	}
	
	@RequestMapping("validate")
	@ResponseBody
	public Messages validation(Member member,HttpServletRequest request) {
		LoginService ls = new LoginService();
		Messages re = ls.validation(member);
		if (re.getErrMessage() == null) {
			request.getSession().setAttribute("status", 1);
			request.getSession().setAttribute("member", member);
		}
		return re;
	}
}
