package service;

import java.sql.SQLException;

import beans.Member;
import common.Messages;
import dao.MembersDao;

public class LoginService {
	
	public Messages validation(Member member) {
		
		String emailtoValid = member.getEmail();
		MembersDao membersDao = new MembersDao();
		Messages result = new Messages();
		Member fromDB = null;
		try {
			fromDB = membersDao.retrivebyEmail(emailtoValid).get(member.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (fromDB == null) {
			result.setErrMessage("nr");
		}else {
			if (fromDB.getPassword().equals(member.getPassword())) {
				member.setFirstname(fromDB.getFirstname());
				member.setLastname(fromDB.getLastname());
				member.setImg(fromDB.getImg());
				result.setOkMessage("login successful");
			}else {
				result.setErrMessage("pi");
			}
		}
		return result;
	}
	
}
