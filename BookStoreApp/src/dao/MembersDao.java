package dao;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.web.multipart.MultipartFile;

import beans.BillingAddressBean;
import beans.Member;

public class MembersDao {
	DataSource ds;
	
	public MembersDao()  {
		// TODO Auto-generated constructor stub
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String, Member> retrivebyEmail(String email) throws SQLException {
		String query = "select * from MEMBER where EMAIL = " + "'" + email + "'";
		Map<String, Member> rv = new HashMap<String, Member>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String em = r.getString("EMAIL");
			String fn = r.getString("FIRST_NAME");
			String ln = r.getString("LAST_NAME");
			String pw = r.getString("PASSWORD");
			String img = r.getString("IMG");
			System.out.println(img);
			Member member = new Member();
			member.setEmail(em);
			member.setFirstname(fn);
			member.setLastname(ln);
			member.setPassword(pw);
			member.setImg(img);
			rv.put(em, member);
		}
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	public Member byEmail(String email) throws SQLException {
		String query = "select * from MEMBER where EMAIL = " + "'" + email + "'";
		Member member = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		
		while(r.next()) {
			String em = r.getString("EMAIL");
			String fn = r.getString("FIRST_NAME");
			String ln = r.getString("LAST_NAME");
			String pw = r.getString("PASSWORD");
			String img = r.getString("IMG");
			System.out.println(img);
			member = new Member();
			member.setEmail(em);
			member.setFirstname(fn);
			member.setLastname(ln);
			member.setPassword(pw);
			member.setImg(img);
			
		}
		r.close();
		p.close();
		con.close();
		return member;
		
	}
	
	public int insert(Member member) throws SQLException {
		String preparedStatement ="insert into MEMBER values(?,?,?,?,?)";
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		System.out.println(member.getEmail());
		stmt.setString(1, member.getEmail());
		stmt.setString(2, member.getFirstname());
		stmt.setString(3, member.getLastname());
		stmt.setString(4, member.getPassword());
		stmt.setString(5, null);
		
		return stmt.executeUpdate();
		

	}
	
	
	public int insertBillAddress(BillingAddressBean ba) throws SQLException {
		String preparedStatement ="insert into BILLADDRESS values(?,?,?,?,?,?)";
		Connection con = this.ds.getConnection();
		PreparedStatement stmt = con.prepareStatement(preparedStatement);
		stmt.setString(1, ba.getEmail());
		stmt.setString(2, ba.getName());
		stmt.setString(3, ba.getAddress());
		stmt.setString(4, ba.getCity());
		stmt.setString(5, ba.getProvince());
		stmt.setString(6, ba.getZip());
		return stmt.executeUpdate();
	}
	
	public void uploadImg(String email,MultipartFile file,String path) throws SQLException {
		if (!file.isEmpty()) {
			
			String filename = file.getOriginalFilename();
			String filePath = path + filename;
			try {
				file.transferTo(new File(filePath));
				
				String extensionName = filename.substring(filename.lastIndexOf(".") + 1);
				String insertStat = "update MEMBER set IMG=" + "'" + filename + "'" + " where email=" + "'" + email + "'";
				Connection con = this.ds.getConnection();
				PreparedStatement stmt = con.prepareStatement(insertStat);
				stmt.executeUpdate();
				
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
	}
	
	public void changeEmailAndPassword(String email, String password) {
		String stat = "update MEMBER set email=" + "'" + email + "'" + "," + " password=" + "'" + password + "'" + " where email=" + "'" + email + "'";
		Connection con;
		try {
			con = this.ds.getConnection();
			PreparedStatement ps = con.prepareStatement(stat);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
