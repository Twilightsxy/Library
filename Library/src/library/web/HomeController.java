package library.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sun.org.apache.bcel.internal.generic.NEW;

import library.db.*;
import library.domain.*;

/**
 * 系统主页控制类
 * 
 * @author dyf
 * @version v1.0
 */
@Controller // 控制定义
@RequestMapping("/") // 相应web路径
public class HomeController {

	@Autowired // 自动注入资源
	private BookRepository bookRepository;

	@Autowired // 自动注入资源
	private LibrarianRepository librarianRepository;
	
	@Autowired // 自动注入资源
	private ReaderRepository readerRepository;
	
	@Autowired // 自动注入资源
	private CartRepository cartRepository;
	
	/**
	 * 首页及自动登录
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = GET) // 相应的请求方法
	public String home(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model,
			HttpServletRequest request, HttpServletResponse response) {
		/*
		 * 依据WebConfig.viewResolver中的
		 * org.springframework.web.servlet.view.InternalResourceViewResolver定义
		 * 
		 * InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		 * resolver.setPrefix("/WEB-INF/views/"); resolver.setSuffix(".jsp");
		 * 
		 * 返回相应jsp视图，即返回/WEB-INF/views/home.jsp
		 * 
		 */
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession();
		if (cookies == null) {
			return "redirect:/login/reader";
		}

		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("username") && session.getAttribute("username") != null) {
				if (cookie.getValue().equals(session.getAttribute("username"))) {
					model.addAttribute("books", bookRepository.findPage(pageNo, pageSize));

					return "redirect:/home";
				}
			}
		}

		return "redirect:/login/reader";
	}

	/**
	 * 进入找回密码页面
	 * 
	 * @return retrieve.jsp
	 */
	@RequestMapping(value = "/retrieve", method = GET)
	public String ReturnRetrieve() {

		return "retrieve";
	}

	/**
	 * 进入用户登录界面
	 * 
	 * @param userName
	 * @param password
	 * @param session
	 * 
	 * @return loginReader.jsp
	 */
	@RequestMapping(value = "/login/reader", method = GET)
	public String showReaderLogin( HttpSession session, Model model) {

		if (session.getAttribute("username") != null) {
			return "redirect:/home";
		}

		/*
		 * @RequestParam注解：
		 * 
		 * value：参数名字，即入参的请求参数名字，如userName表示请求的参数区中的名字为userName的参数的值将传入；
		 * 
		 * required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报404错误码；
		 * 
		 * defaultValue：默认值，表示如果请求中没有同名参数时的默认值
		 * 
		 */
		session.removeAttribute("librarian");
		session.removeAttribute("username");

		return "loginReader";

	}
	
	/**
	 * 进入用户登录界面
	 * 
	 * @param userName
	 * @param password
	 * @param session
	 * 
	 * @return loginReader.jsp
	 */
	@RequestMapping(value = "/login/reader", method = POST)
	public String loginReader(@RequestParam(value = "username", defaultValue = "") String username,
	@RequestParam(value = "password", defaultValue = "") String password, HttpSession session, Model model , HttpServletResponse response, HttpServletRequest request) {

		if (session.getAttribute("username") != null) {
			return "redirect:/home";
		}

		/*
		 * @RequestParam注解：
		 * 
		 * value：参数名字，即入参的请求参数名字，如userName表示请求的参数区中的名字为userName的参数的值将传入；
		 * 
		 * required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报404错误码；
		 * 
		 * defaultValue：默认值，表示如果请求中没有同名参数时的默认值
		 * 
		 */
		session.removeAttribute("librarian");
		session.removeAttribute("username");

		boolean rNo = readerRepository.getrNo(username);
		boolean test = readerRepository.get(username, password);

		if (test) {
			model.addAttribute("username", username);
			Reader reader = readerRepository.findByPassword(username, password);
			session.setAttribute("reader", reader);
			session.setAttribute("username", username);
			Cookie usernameCookie = new Cookie("username", username);
			usernameCookie.setMaxAge(500);
			usernameCookie.setPath("/");
		    response.addCookie(usernameCookie);
			return "redirect:/home";
		} else if (!rNo) {
			model.addAttribute("allTrue", true);
			model.addAttribute("rNoError", true);
			model.addAttribute("rPassError", false);
			return "loginReader";
		} else {
			model.addAttribute("allTrue", true);
			model.addAttribute("rNoError", false);
			model.addAttribute("rPassError", true);
			return "loginReader";
		}

	}


	/**
	 * 进入主页
	 * 
	 * @return home.jsp
	 */
	@RequestMapping(value = "/home", method = GET)
	public String ReturnHome(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			HttpSession session, Model model) {
      Search search = (Search)session.getAttribute("search");
		if(search == null) {
		
		model.addAttribute("books", bookRepository.findPage(pageNo, pageSize));
		}
		else {
			String category = search.getCategory();
			String searchLimit = search.getSearchLimit();
			String searchContent   = search.getSearchContent();
			
			if (!category.equals("15")) {
				if (searchLimit.equals("title")) {
					model.addAttribute("books",
							bookRepository.findbytitle(pageNo, pageSize, category, searchContent));

				} else {
					model.addAttribute("books",
							bookRepository.findbyauthor(pageNo, pageSize, category, searchContent));

				}
			}

			else {
				if (searchLimit.equals("title")) {
					model.addAttribute("books", bookRepository.findbytitle(pageNo, pageSize, "", searchContent));

				} else {
					model.addAttribute("books",
							bookRepository.findbyauthor(pageNo, pageSize, "", searchContent));

				}
			}
			
		}
	
		return "home";
	}

	/*
	* 进入管理员登录界面
	* 
	*/
	@RequestMapping(value = "/login/librarian", method = GET)
	public String showLibrarianLogin(HttpSession session, Model model) {

		if (session.getAttribute("librarian") != null) {
			return "redirect:/librarian/book";
		}

		session.removeAttribute("reader");
        session.removeAttribute("username");
        
        return "loginLibrarian";
	}
	
	/*
	* 管理员登录

	* @RequestParam注解：
	* 
	* value：参数名字，即入参的请求参数名字，如userName表示请求的参数区中的名字为userName的参数的值将传入；
	* 
	* required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报404错误码；
	* 
	* defaultValue：默认值，表示如果请求中没有同名参数时的默认值
	* 
	*/
	@RequestMapping(value = "/login/librarian", method = POST)
	public String loginLibrarian(HttpServletRequest request,@RequestParam(value = "username", defaultValue = "") String username,
	@RequestParam(value = "password", defaultValue = "") String password, HttpSession session, Model model) {

		boolean rNo = librarianRepository.getrNo(username);
		boolean test = librarianRepository.get(username, password);

		if (test) {
			Librarian librarian = librarianRepository.findByPassword(username, password);
			session.setAttribute("librarian", librarian);
			return "redirect:/librarian/book";
		} else if (!rNo) {
			model.addAttribute("allTrue", true);
			model.addAttribute("rNoError", true);
			model.addAttribute("rPassError", false);
			return "loginLibrarian";
		} else {
			model.addAttribute("allTrue", true);
			model.addAttribute("rNoError", false);
			model.addAttribute("rPassError", true);
			return "loginLibrarian";
		}
	}
	
	/**
	 * 注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout", method = GET)
	public String logout(HttpSession session) {
		session.removeAttribute("reader");
		session.removeAttribute("librarian");
		session.removeAttribute("username");
		session.removeAttribute("search");
		session.invalidate();
		return "redirect:/home";
	}

	/**
	 * 搜索功能
	 * 
	 * @return
	 */
	@RequestMapping(value = "/home/bookSearch", method = POST)
	public String search(@RequestParam(value = "category", defaultValue = "") String category,
			@RequestParam(value = "searchLimit", defaultValue = "") String searchLimit,
			@RequestParam(value = "searchContent", defaultValue = "") String searchContent,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			 HttpSession session,Model model) {
		
		Search search = new Search(category,searchLimit,searchContent);
		session.setAttribute("search", search);
		
		if (!category.equals("15")) {
			if (searchLimit.equals("title")) {
				model.addAttribute("books",
						bookRepository.findbytitle(pageNo, pageSize, category, searchContent));

			} else {
				model.addAttribute("books",
						bookRepository.findbyauthor(pageNo, pageSize, category, searchContent));

			}
		}

		else {
			if (searchLimit.equals("title")) {
				model.addAttribute("books", bookRepository.findbytitle(pageNo, pageSize, "", searchContent));

			} else {
				model.addAttribute("books",
						bookRepository.findbyauthor(pageNo, pageSize, "", searchContent));

			}
		}
		model.addAttribute("searchContent", searchContent);
		return "home";
	}

	
	
	/**
	 *进入404提示界面
	 * 
	 * @return 404.jsp
	 */
	@RequestMapping(value = "/404", method = GET)
	public String Return404() {

		return "404";
	}
}
