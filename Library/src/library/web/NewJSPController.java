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
@RequestMapping("/NewJSP") // 相应web路径
public class NewJSPController {

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
		return "NewFile";
	}
	
	
}
