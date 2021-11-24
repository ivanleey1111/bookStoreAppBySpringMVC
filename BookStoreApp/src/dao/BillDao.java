package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.BillingAddressBean;
import beans.BookBean;
import beans.Member;
import beans.PoByBidBeam;
import beans.ShippingAddressBean;

public class BillDao {
	DataSource ds;
	
	public BillDao() {
		// TODO Auto-generated constructor stub
		
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public BillingAddressBean getByEmail(String email) throws SQLException {
		String query = "select * from BILLADDRESS where EMAIL = " + "'" + email + "'";
		BillingAddressBean ba = null;
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String em = r.getString("EMAIL");
			String na = r.getString("NAME");
			String add = r.getString("ADDRESS");
			String ct = r.getString("CITY");
			String pro = r.getString("PROVINCE");
			String zip = r.getString("ZIP");
			ba = new BillingAddressBean();
			ba.setEmail(em);
			ba.setName(na);
			ba.setAddress(add);
			ba.setCity(ct);
			ba.setProvince(pro);
			ba.setZip(zip);
			
			
		}
		r.close();
		p.close();
		con.close();
		return ba;
	}
	
	public int updateBA(BillingAddressBean ba) throws SQLException {
		String statement = "update BILLADDRESS set EMAIL=" + "'" + ba.getEmail() + "'" + ",NAME=" + "'" + ba.getName() + "'" + ",ADDRESS=" + "'" + ba.getAddress() + "'" + ",CITY=" + "'" + ba.getCity() + "'"+ ",PROVINCE=" + "'" + ba.getProvince() + "'" + ",ZIP=" + "'" + ba.getZip() + "'" + 
				 " where EMAIL=" + "'" + ba.getEmail() + "'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(statement);
		return p.executeUpdate();
	}
	
	
	public int updateSA(ShippingAddressBean sa) throws SQLException {
		
		String getALL = "select * from SHIPADDRESS where EMAIL=" + "'" + sa.getShipemail() + "'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(getALL);
		ResultSet r = p.executeQuery();
		if(!r.next()) {			
			String insertToPO = "insert into SHIPADDRESS values(?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = con.prepareStatement(insertToPO);
			
			stmt.setString(1, sa.getShipemail());
			stmt.setString(2, sa.getShipname());
			stmt.setString(3, sa.getShipaddress());
			stmt.setString(4, sa.getShipcity());
			stmt.setString(5, sa.getShipprovince());
			stmt.setString(6, sa.getShipzip());
			stmt.setString(7, sa.getShipphone());
			
			
			return stmt.executeUpdate();
		}else {
		

			String statement = "update SHIPADDRESS set EMAIL=" + "'" + sa.getShipemail() + "'" + ",NAME=" + "'" + sa.getShipname() + "'" + ",ADDRESS=" + "'" + sa.getShipaddress() + "'" + ",CITY=" + "'" + sa.getShipcity() + "'" + ",PROVINCE=" + "'" + sa.getShipprovince() + "'" + ",ZIP=" + "'" + sa.getShipzip() + "'" + ",PHONE=" + "'" + sa.getShipphone() + "'" + 
				 " where EMAIL=" + "'" + sa.getShipemail() + "'";
		
			p = con.prepareStatement(statement);
			return p.executeUpdate();
		}
	}
	
	
	
	public int insertPO(String status,String email,String total) throws SQLException {
		String getALL = "select * from PO";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(getALL);
		ResultSet r = p.executeQuery();
		int orderID = 1;
		while(r.next()) {			
			orderID++;	
		}
		
		String insertToPO = "insert into PO values(?,?,?,?)";
		
		PreparedStatement stmt = con.prepareStatement(insertToPO);
		
		stmt.setInt(1, orderID);
		stmt.setString(2, email);
		stmt.setString(3, status);
		stmt.setString(4, total);
		
		
		return stmt.executeUpdate();
	}
	
	public int insertPOItem(String bid, int count) throws SQLException {
		String getALL = "select * from PO";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(getALL);
		ResultSet r = p.executeQuery();
		int orderID = 1;
		while(r.next()) {			
			orderID++;	
		}
		orderID--;
		String insertToPOITEM = "insert into POITEM values(?,?,?,?)";
		
		PreparedStatement stmt = con.prepareStatement(insertToPOITEM);
		Date date = new Date();
		String day = date.getMonth() + 1 + "/" + date.getDate() + "/" + (date.getYear() + 1900);
		stmt.setInt(1, orderID);
		stmt.setString(2, bid);
		stmt.setInt(3, count);
		stmt.setString(4, day);
		
		return stmt.executeUpdate();
	}
	
	public int updateBookCount(int count, String bid) throws SQLException {
		String statement = "select * from BOOK where bid=" + "'" + bid + "'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(statement);
		ResultSet r = p.executeQuery();
		int oldCount = 0;
		while(r.next()) {
			oldCount = r.getInt("COUNT");	
		}
		int newCount = oldCount - count;
		String update = "update BOOK set COUNT=" + newCount + "where bid=" + "'" + bid + "'";
		p = con.prepareStatement(update);
		return p.executeUpdate();
	}
	
	
	public List<PoByBidBeam> getOrdersbyBid(String bid) throws SQLException {
		List<PoByBidBeam> books = new ArrayList<PoByBidBeam>();
		BookBean bookBean = null;
		String query = "select * from POITEM where BID=" + "'" + bid + "'";
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			int oid = r.getInt("ORDERID");
			String bid1 = r.getString("BID");
			
			int count = r.getInt("COUNT");
			String day = r.getString("DAY");
			
			books.add(new PoByBidBeam(oid, bid1, count, day));
			
		}
		return books;
	}
	
	
	
	
}
