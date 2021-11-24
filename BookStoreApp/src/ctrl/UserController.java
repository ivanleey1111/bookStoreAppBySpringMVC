package ctrl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import beans.Member;
import common.Messages;
import dao.MembersDao;
import service.LoginService;
import service.UserService;

@Controller
public class UserController {
	
	@RequestMapping("edit")
	public String edit() {
		return "editProfile";
	}
	
	@RequestMapping("changeFile")
	public String changeFile(HttpServletRequest request,@RequestParam("filePhoto") MultipartFile file,@RequestParam("emailaddress")String email, @RequestParam("password") String password,RedirectAttributes re) {
		Member member = (Member) request.getSession().getAttribute("member");
		UserService us = new UserService();
		us.upload(file, member.getEmail(),request);
		us.changeEmailAndPassword(email, password);
//		LoginService ls = new LoginService();
//		Messages re = ls.validation(member);
//		if (re.getErrMessage() == null) {
//			
//			request.getSession().setAttribute("member", member);
//		}
		request.getSession().invalidate();
		re.addFlashAttribute("notice","Edit successful, please login again!");
		
		return "redirect:login";
	}
	
	@RequestMapping("changeimg")
	@ResponseBody
	public Messages changeimg(Model model,HttpServletRequest request,@RequestParam("filePhoto")String file) throws SQLException {
		Member member = (Member) request.getSession().getAttribute("member");
		System.out.println(file);
		MembersDao md = new MembersDao();
		String email = member.getEmail();
		member = md.byEmail(email);
		member.setImg(file);
		request.getSession().setAttribute("hahha", "hahhahahahaha");
		//request.setAttribute("member", member);
		Messages messages = new Messages();
		//request.getSession().setAttribute("member", member);
		//System.out.println(request.getSession().getAttribute("member").toString());
		messages.setOkMessage(member.getImg());
		
		return messages;
		
	}
	
	@RequestMapping("ha")
	public String hapage(HttpServletRequest request) {
		request.setAttribute("hahha", "hahahhahah");
		return "main";
	}

}
