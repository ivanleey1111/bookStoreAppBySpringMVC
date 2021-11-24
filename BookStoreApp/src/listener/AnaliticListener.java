package listener;

import java.sql.SQLException;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import beans.AnalyticBean;
import dao.AnalyticsDao;

/**
 * Application Lifecycle Listener implementation class AnaliticListener
 *
 */
@WebListener
public class AnaliticListener implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public AnaliticListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	if (arg0.getName().equals("ab")) {
			AnalyticBean ab = (AnalyticBean) arg0.getSession().getAttribute("ab");
			AnalyticsDao ad = new AnalyticsDao();
			try {
				ad.insertEvent(ab);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	if (arg0.getName().equals("ab")) {
			AnalyticBean ab = (AnalyticBean) arg0.getSession().getAttribute("ab");
			AnalyticsDao ad = new AnalyticsDao();
			try {
				ad.insertEvent(ab);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
    }
    
    
    
    
	
}
