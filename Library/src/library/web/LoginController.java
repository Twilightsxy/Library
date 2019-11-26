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
 * 登录页面控制
 * 
 * @author dyf
 * @version v1.0
 */
@Controller // 控制定义
public class LoginController {

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
	
	
}
