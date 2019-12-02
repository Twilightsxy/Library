package library.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import library.db.LibrarianRepository;
import library.db.ReaderRepository;
import library.domain.Librarian;
import library.domain.LibrarianForm;
import library.domain.Reader;

/**
 * 管理员控制类
 * 
 * @author dyf
 * @version v1.0
 */
@Controller
@SessionAttributes({"librarian"})
@RequestMapping("/librarian/manager")
public class ManageLibrarianController {

	@Autowired
	private LibrarianRepository librarianRepository;

    /**
	 * 进入管理管理员页面
	 * 
	 * @return manageLibrarian.jsp
	 */
	@RequestMapping(method = GET)
	public String ReturnAdmin(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model) {
		
		model.addAttribute(new LibrarianForm());
		model.addAttribute(new Librarian());
		model.addAttribute("librarians", librarianRepository.findPage(pageNo, pageSize));
		return "manageLibrarian";
	}

	/**
	 * 提交添加管理者信息，提交成功后跳转到管理者信息
	 * 
	 * @return 管理员主页
	 * @throws IOException 
	 */
	@RequestMapping(value = "/add", method = POST)
	public String addLibrarian(@Valid LibrarianForm librarianForm, Errors errors, HttpSession session, HttpServletResponse response) throws IOException {
		
		//boolean isexist = librarianRepository.isEsxit(librarian.getlUserName());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Librarian librarian2 = librarianRepository.getLibrarian(librarianForm.getlUserName());
		if (librarian2 != null) {
			out.print("<script type='text/javascript'>alert('账号已存在，添加失败！');location.href='/Library/librarian/manager'</script>");
			out.flush();
			out.close();
			return "redirect:/librarian/manager";
		}
		Librarian librarian3 = new Librarian(librarianForm.getlUserName(), librarianForm.getlPassword(),librarianForm.getlName(),librarianForm.getlSex(),librarianForm.getlEmail(),librarianForm.getlTel(),false);
		librarianRepository.addLibrarian(librarian3);
		out.print("<script type='text/javascript'>alert('添加成功！');location.href='/Library/librarian/manager'</script>");
		out.flush();
		out.close();
		return "redirect:/librarian/manager";
	}

	/**
	 * 删除请求
	 * 
	 * @param userName
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/delete", method = GET)
	public String processLogin(@RequestParam(value = "userName", defaultValue = "") String userName,
			HttpSession session, HttpServletResponse response) throws IOException {
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
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		Librarian librarian = librarianRepository.getLibrarian(userName);
		Librarian now = (Librarian) session.getAttribute("librarian");
		if (librarian != null && !librarian.getlUserName().equals(now.getlUserName())) {
			librarianRepository.deleteLibrarian(userName);
			out.print("<script type='text/javascript'>alert('删除成功！');location.href='/Library/librarian/manager'</script>");
			out.flush();
			out.close();
			return "redirect:/librarian/manager";
		} else {
			out.print("<script type='text/javascript'>alert('删除失败，不能删除自己！');location.href='/Library/librarian/manager'</script>");
			out.flush();
			out.close();
			return "redirect:/librarian/manager";
		}
	}

	/**
	 * 修改个人信息
	 * 
	 * @param userName
	 * @param model
	 * 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/change", method = POST)
	public String changeInformation(@RequestParam(value = "lUserName", defaultValue = "") String userName,
			@RequestParam(value = "oldUserName", defaultValue = "") String oldUserName,
			@RequestParam(value = "lName", defaultValue = "") String fullName,
			@RequestParam(value = "lEmail", defaultValue = "") String email,
			@RequestParam(value = "lSex", defaultValue = "") String lSex,
			@RequestParam(value = "lTel", defaultValue = "") String lTel,
			@RequestParam(value = "lPassword", defaultValue = "") String lPassword, HttpSession session, Model model, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		Librarian librarian = new Librarian(userName, lPassword, fullName, lSex, email, lTel, false);
		librarianRepository.updateLibrarian(librarian, oldUserName);
		out.print("<script type='text/javascript'>alert('修改个人信息成功！');location.href='/Library/librarian/manager'</script>");
		out.flush();
		out.close();
		return "redirect:/librarian/manager";
	}

}
