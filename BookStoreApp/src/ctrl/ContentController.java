package ctrl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import beans.AnalyticBean;
import beans.BookBean;
import beans.Review;
import common.Messages;
import service.ContentService;

@Controller
public class ContentController {

	ContentService ContentService = new ContentService();

	@RequestMapping("content")
	public String gotoContentPage(Model model) {
		List<String> list = ContentService.getAllCategories();
		List<BookBean> books = ContentService.getAllBooks();
		model.addAttribute("categories", list);
		model.addAttribute("books", books);

		return "contentPage";
	}

	@RequestMapping("bookByCategory")

	public String showByCategory(String category, Model model) {

		List<String> list = ContentService.getAllCategories();
		List<BookBean> books = ContentService.getByCategory(category);
		model.addAttribute("categories", list);
		model.addAttribute("books", books);
		return "contentPage";
	}

	@RequestMapping("productDeatil")
	public String productDeatil(String bid, Model model,HttpServletRequest request) {
		// add to listener
		AnalyticBean ab = new AnalyticBean();
		ab.setBid(bid);
		Date date = new Date();
		String day = (date.getMonth() + 1) + "/" + date.getDate() + "/" + (date.getYear() + 1900);
		ab.setDay(day);
		ab.setEventType("VIEW");
		request.getSession().setAttribute("ab", ab);
		BookBean bookBean = ContentService.getByBookId(bid);
		model.addAttribute("book", bookBean);
		List<Review> reviews = ContentService.getReviewByBookId(bid);

		model.addAttribute("reviews", reviews);
		return "productDetail";
	}

	@RequestMapping("insertReview")

	@ResponseBody
	public Messages insertReview(Review review) {
		int result = ContentService.insertReview(review);
		Messages messages = new Messages();
		if (result == 1) {
			messages.setOkMessage("Adding Review Successful!");
		} else {
			messages.setErrMessage("Adding Review Unsuccessful!");
		}

		return messages;
	}

	@RequestMapping("search")

	public String search(String title, Model model) {

		List<BookBean> result = null;
		result = ContentService.searchByTitle(title);
		model.addAttribute("books", result);
		return "searchResult";

	}
}
