package service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import dao.MembersDao;

public class UserService {
	
	public void upload(MultipartFile file,String email,HttpServletRequest request) {
		MembersDao membersDao = new MembersDao();
		try {
			String path = "/Users/liyifan/Desktop/m1pro/BookStoreApp/WebContent/userIMG";
			membersDao.uploadImg(email, file,path);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public void changeEmailAndPassword(String email, String password) {
		MembersDao membersDao = new MembersDao();
		membersDao.changeEmailAndPassword(email, password);
	}
}
