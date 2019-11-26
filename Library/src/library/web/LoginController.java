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
 * 用户登录页面控制
 * 
 * @author dyf
 * @version v1.0
 */
@Controller // 控制定义
public class LoginController {
	
	@Autowired // 自动注入资源
	private ReaderRepository readerRepository;

	/**
	 * 进入主页
	 * 
	 * @return home.jsp
	 */
	@RequestMapping(value = "/home", method = GET)
	public String ReturnHome() {

		return "home";
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
}
