package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.AnalyticBean;
import beans.BillingAddressBean;
import beans.BookBean;
import beans.MonthlySold;
import beans.TopBooks;

public class AnalyticsDao {
	DataSource ds;

	public AnalyticsDao() {
		// TODO Auto-generated constructor stub
		
		try {
			ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public List<MonthlySold> getMonthlySold(String month) throws SQLException {
		String query = "select tt.c as title, sum(tt.b) as count from (SELECT  POItem.bid as a, POItem.count as b, book.title as c FROM BOOK, POItem where book.bid = POItem.bid and POItem.day like" + "'" + 
			month + "/%" + "'" + ") tt group by tt.c";
		List<MonthlySold> list = new ArrayList<MonthlySold>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String title = r.getString("TITLE");
			int count  = r.getInt("COUNT");
			list.add(new MonthlySold(title, count));
			
		}
		return list;
		
	}
	
	public List<TopBooks> getTops(String hp) throws SQLException {
		String query ="SELECT b as title, count(*) as count FROM (SELECT VisitEvent.bid as a, book.title as b FROM VisitEvent, book where eventtype = " + "'" + hp + "'" + " and book.bid =VisitEvent.bid) tt" + 
		" group by tt.b ORDER BY count(*) DESC fetch first 10 rows only";
		
		
		
		List<TopBooks> list = new ArrayList<TopBooks>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		ResultSet r = p.executeQuery();
		while(r.next()) {
			String title = r.getString("TITLE");
			int count  = r.getInt("COUNT");

			list.add(new TopBooks(title, count));
			
		}
		return list;
		
	}
	
	
	
	
	
	
	public int insertEvent(AnalyticBean ab) throws SQLException {
		String day = ab.getDay();
		String bid = ab.getBid();
		String event = ab.getEventType();
		Connection con = this.ds.getConnection();
		String statement = "insert into VISITEVENT values(?,?,?)";
		PreparedStatement stmt = con.prepareStatement(statement);
		
		stmt.setString(1, day);
		stmt.setString(2, bid);
		stmt.setString(3, event);
		return stmt.executeUpdate();
	}
}
