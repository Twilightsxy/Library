package library.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	private BookRepository test;

	@Autowired // 自动注入资源
	private ReaderRepository readerRepository;
	
	@Autowired // 自动注入资源
	private LibrarianRepository librarianRepository;

	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(method = GET) // 相应的请求方法
	public String home(Model model) {
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

		List<Book> lists = test.getAllBooks("title", "红");
		for (Book book : lists) {
			System.out.println(book.getBookNo());
		}
		return "login";
	}

	/**
	 * 进入用户登录界面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = GET)
	public String showReaderLogin() {

		return "login";
	}

	/**
	 * 处理用户登录请求
	 * 
	 * @param userName
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/login", method = POST)
	public String readerLogin(@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "password", defaultValue = "") String password, HttpSession session) {
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
		Reader reader = readerRepository.findByPassword(userName, password);
		if (reader != null) {
			session.setAttribute("reader", reader);
			return "redirect:/index";
		} else {
			return "login";
		}
	}

	/**
	 * 管理员登录
	 * 
	 * @return
	 */
	@RequestMapping(value = "/loginLibrarian", method = GET)
	public String showLibrarianLogin(HttpSession session) {

		return "loginLibrarian";
	}

	/**
	 * 登录请求
	 * 
	 * @param userName
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/loginLibrarian", method = POST)
	public String librarianLogin(@RequestParam(value = "userName", defaultValue = "") String userName,
			@RequestParam(value = "password", defaultValue = "") String password, HttpSession session) {
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
		session.removeAttribute("reader");
		Librarian librarian = librarianRepository.findByPassword(userName, password);
		if (librarian != null) {
			session.setAttribute("librarian", librarian);
			return "redirect:/librarianManager";
		} else {
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
		session.invalidate();
		return "redirect:/index";
	}
	
	
}
