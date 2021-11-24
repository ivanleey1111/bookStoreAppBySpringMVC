package ctrl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import beans.BookBean;
import beans.MonthlySold;
import beans.TopBooks;
import dao.AnalyticsDao;

@Controller
public class AnalyticsController {
	@RequestMapping("analytics")
	public String anaPage() {
		return "analytics";
	}
	@RequestMapping("month")
	public String toMonthPage() {
		return "month";
	}
	@RequestMapping("getbyMonth")
	public String bookSoldEachMonth(String month, Model model) {
		
		AnalyticsDao ad = new AnalyticsDao();
		List<MonthlySold> list = null;
		try {
			list = ad.getMonthlySold(month);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("mon", month);
		model.addAttribute("monthlysold", list);
		return "soldeachmonth";
	}
	
	
	@RequestMapping("hotProducts")
	public String tohotProductsPage() {
		return "hotProducts";
	}
	
	@RequestMapping("toppage")
	public String showHots(String hp, Model model) {
		
		AnalyticsDao ad = new AnalyticsDao();
		List<TopBooks> list = null;
		try {
			list = ad.getTops(hp);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("hp", hp);
		model.addAttribute("hots", list);
		return "showhots";
	}
	
}
