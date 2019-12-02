package library.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import library.db.*;
import library.domain.*;

/**
 * 管理员控制类
 * 
 * @author zbl
 * @version v1.0
 */
@Controller
@SessionAttributes({ "librarian" })
@RequestMapping("/librarian/book")
public class ManageBookController {

	@Autowired
	private BookRepository managerbook;

	/**
	 * 进入书籍管理页面
	 * 
	 * @return manageBook.jsp
	 */
	@RequestMapping(method = GET)
	public String ReturnAdminHome(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model) {

		model.addAttribute("books", managerbook.findPage(pageNo, pageSize));
		return "manageBook";
	}

	/**
	 * 
	 * @param bookNo
	 * @param title
	 * @param author
	 * @param price
	 * @param time
	 * @param publish
	 * @param ISBN
	 * @param brief
	 * @param location
	 * @param model
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/change", method = POST) // 相应的请求方法
	public String updatebook(@RequestParam(value = "bookNo", defaultValue = "") String bookNo,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "author", defaultValue = "") String author,
			@RequestParam(value = "price", defaultValue = "") String price,
			@RequestParam(value = "time", defaultValue = "") String time,
			@RequestParam(value = "publish", defaultValue = "") String publish,
			@RequestParam(value = "ISBN", defaultValue = "") String ISBN,
			@RequestParam(value = "brief", defaultValue = "") String brief,
			@RequestParam(value = "location", defaultValue = "") String location, Model model,
			HttpServletResponse response) throws IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (title == "" || author == "" || price == "" || time == "" || publish == "" || brief == "" || ISBN == ""
				|| location == "") {
			out.print(
					"<script type='text/javascript'>alert('修改失败，信息不能为空！');location.href='/Library/librarian/book'</script>");
			out.flush();
			out.close();
			return "manageBook";
		} else {
			Book book = managerbook.getBook(Integer.valueOf(bookNo));
			book.setTitle(title);
			book.setAuthor(author);
			book.setPrice(price);
			book.setTime(time);
			book.setPublish(publish);
			book.setBrief(brief);
			book.setISBN(ISBN);

			book.setLocation(location);
			managerbook.updateBook(book);
			out.print("<script type='text/javascript'>alert('修改成功！');location.href='/Library/librarian/book'</script>");
			out.flush();
			out.close();
			model.addAttribute("books", managerbook.findPage(1, 10));

			return "manageBook";

		}

	}

	/**
	 * 
	 * @param bookNo
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/delet/{bookNo}", method = GET) // 相应的请求方法
	public String addcart(@PathVariable("bookNo") int bookNo, Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		Book book = managerbook.getBook(bookNo);
		if (book != null && book.getStatus() == 0) {
			managerbook.deleteBook(bookNo);
			out.print("<script type='text/javascript'>alert('删除成功！');location.href='/Library/librarian/book'</script>");
			out.flush();
			out.close();
		} else {
			out.print("<script type='text/javascript'>alert('删除失败，当前书籍为不可借状态！');location.href='/Library/librarian/book'</script>");
			out.flush();
			out.close();
		}
			

		model.addAttribute("books", managerbook.findPage(pageNo, pageSize));

		return "manageBook";
	}

	/**
	 * 添加书籍
	 * 
	 * @param title
	 * @param author
	 * @param price
	 * @param time
	 * @param publish
	 * @param ISBN
	 * @param brief
	 * @param location
	 * @param number
	 * @param model
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/add", method = POST) // 相应的请求方法
	public String addbook(@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "author", defaultValue = "") String author,
			@RequestParam(value = "price", defaultValue = "") String price,
			@RequestParam(value = "time", defaultValue = "") String time,
			@RequestParam(value = "publish", defaultValue = "") String publish,
			@RequestParam(value = "ISBN", defaultValue = "") String ISBN,
			@RequestParam(value = "brief", defaultValue = "") String brief,
			@RequestParam(value = "location", defaultValue = "") String location,
			@RequestParam(value = "number", defaultValue = "") String number, Model model, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (title == "" || author == "" || price == "" || time == "" || publish == "" || brief == "" || ISBN == ""
				|| location == "") {
			out.print("<script type='text/javascript'>alert('添加失败，信息不能为空！');location.href='/Library/librarian/book'</script>");
			out.flush();
			out.close();
			return "manageBook";
		} else {
			int i = 1, num = Integer.valueOf(number);
			for (i = 1; num > 0; i++) {
				Book book1 = managerbook.getBook(i);
				if (book1 == null) {
					book1 = new Book(i, title, author, price, time, publish, brief, ISBN, "11", location, 0);
					managerbook.addBook(book1);
					num--;
				}
			}

			model.addAttribute("books", managerbook.findPage(1, 10));
			out.print("<script type='text/javascript'>alert('添加成功！');location.href='/Library/librarian/book'</script>");
			out.flush();
			out.close();
			return "manageBook";

		}

	}

	/**
	 * 搜索图书
	 * 
	 * @param category
	 * @param searchLimit
	 * @param searchContent
	 * @param pageNo
	 * @param pageSize
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/search", method = POST)
	public String search(@RequestParam(value = "category", defaultValue = "") String category,
			@RequestParam(value = "searchLimit", defaultValue = "") String searchLimit,
			@RequestParam(value = "searchContent", defaultValue = "") String searchContent,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, HttpSession session, Model model) {

		Search search = new Search(category, searchLimit, searchContent);
		session.setAttribute("search", search);

		if (!category.equals("15")) {
			if (searchLimit.equals("title")) {
				model.addAttribute("books", managerbook.findbytitle(pageNo, pageSize, category, searchContent));

			} else {
				model.addAttribute("books", managerbook.findbyauthor(pageNo, pageSize, category, searchContent));

			}
		}

		else {
			if (searchLimit.equals("title")) {
				model.addAttribute("books", managerbook.findbytitle(pageNo, pageSize, "", searchContent));

			} else {
				model.addAttribute("books", managerbook.findbyauthor(pageNo, pageSize, "", searchContent));

			}
		}
		model.addAttribute("searchContent", searchContent);
		return "manageBook";
	}

    /**
         * 进入归还书籍页面
     */
    @RequestMapping(value = "/return", method = GET)
    public String showReturnBook(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model){
    	
    	model.addAttribute("books", managerbook.findPageLend(pageNo, pageSize));
    	
    	return "manageReturn";
    }
    
    
    @RequestMapping(value = "/return",method = POST)
    public String returnBook(@RequestParam(value = "bookNo", defaultValue = "") String bookNo) {
    	try {
    		Book book = managerbook.getBook(Integer.valueOf(bookNo));
        	book.setStatus(0);
        	managerbook.updateBook(book);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	
    	return "redirect:/librarian/book/return";
    }
}
