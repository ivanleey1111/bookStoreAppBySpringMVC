package service;

import java.sql.SQLException;

import beans.BillingAddressBean;
import beans.Member;
import common.Messages;
import dao.MembersDao;

public class RegisterService {
	MembersDao membersDao = new MembersDao();
	public Messages retrivebyEmail(Member member,BillingAddressBean ba) {
		String email = member.getEmail();
		Messages messages = new Messages();
		try {
			Member fromDB = membersDao.retrivebyEmail(email).get(email);
			if (fromDB != null) {
				messages.setErrMessage("This email has been registered, please try another!");
			}else {
				int exe = membersDao.insert(member);
				int insertaddress = membersDao.insertBillAddress(ba);
				System.out.println(insertaddress);
				if (exe != 0 && insertaddress != 0) {
					messages.setOkMessage("Register successful!");
				}else {
					messages.setErrMessage("Register unsuccessful! Please fill all blanks!");
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(messages.getOkMessage());
		return messages;
	}
	
	
	
	
	
		
}
