package ctrl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainPageController {
	@RequestMapping("main")
	public String mainPage(HttpServletRequest request) {
		request.getServletContext().setAttribute("cardvalid", 1);
		return "main";
	}
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:main";
	}
	
	@RequestMapping("contact")
	public String contact() {
		return "contact";
	}
	
	
	
	
}
