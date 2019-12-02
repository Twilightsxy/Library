package library.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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

import com.sun.org.apache.xpath.internal.operations.Mod;
//import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import library.db.*;
import library.domain.*;

/**
 * 用户控制类
 * 
 * @author dyf
 * @version v1.0
 */
@Controller
@RequestMapping("/librarian/reader")
public class ManageReaderController {

	@Autowired
    private ReaderRepository readerRepository;
    
    @Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	/**
	 * 进入管理用户页面
	 * 
	 * @return manageReader.jsp
	 */
	@RequestMapping(method = GET)
	public String ReturnAccount(@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, Model model) {
		
		model.addAttribute("readers", readerRepository.findPage(pageNo, pageSize));
		model.addAttribute(new Reader());
		return "manageReader";
	}
	
	/**
	 * 提交添加读者信息，提交成功后跳转到管理员主页
	 * 
	 * @return 管理员主页
	 * @throws IOException 
	 */
	@RequestMapping(value = "/add", method = POST)
	public String processRegistration2(@Valid Reader reader, Errors errors, HttpSession session, HttpServletResponse response) throws IOException {
	
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		Reader reader2 = readerRepository.getReader(reader.getReaderNo());
		if (reader2 != null) {
			out.print("<script type='text/javascript'>alert('账户已存在，添加失败！');location.href='//librarian/reader'</script>");
			out.flush();
			out.close();
			return "redirect:/librarian/reader";
		}

		readerRepository.addReader(reader);
		out.print("<script type='text/javascript'>alert('添加成功！');location.href='/Library/librarian/reader'</script>");
		out.flush();
		out.close();
		return "redirect:/librarian/reader";
	}
	
	/**
	 * 删除读者请求
	 * 
	 * @param userName
	 * @param session
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/delete", method = GET)
	public String deleteReader(@RequestParam(value = "readerNo", defaultValue = "") String readerNo,
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
		Reader reader = readerRepository.getReader(readerNo);
		if (reader != null) {
			readerRepository.deleteReader(readerNo);
			out.print("<script type='text/javascript'>alert('删除成功！');location.href='/Library/librarian/reader'</script>");
			out.flush();
			out.close();
			return "redirect:/librarian/reader";
			
		} 
		else {
			out.print("<script type='text/javascript'>alert('删除失败！');location.href='/Library/librarian/reader'</script>");
			out.flush();
			out.close();
			return "redirect:/librarian/reader";
		}
	}

	/**
	 * 修改读者信息
	 * 
	 * @param userName
	 * @param model
	 * 
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/change", method = POST)
	public String changeInformation2(@RequestParam(value = "readerNo", defaultValue = "") String readerNo,
			@RequestParam(value = "oldReaderNo", defaultValue = "") String oldReaderNo,
			@RequestParam(value = "readerName", defaultValue = "") String readerName,
			@RequestParam(value = "readerPassword", defaultValue = "") String readerPassword,
			@RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "fine", defaultValue = "0") String fine, HttpSession session, Model model, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		Reader reader = new Reader(readerNo, readerName, readerPassword, email, fine);
		readerRepository.updateReader(reader, oldReaderNo);
		out.print("<script type='text/javascript'>alert('修改成功！');location.href='/Library/librarian/reader'</script>");
		out.flush();
		out.close();
		return "redirect:/librarian/reader";
	}
    
	/**
	 * 
	 * @param searchContent
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/search",method = GET)
	public String viewReader() {
		return "viewReader";
	}
	
	/**
	 * 
	 * @param searchContent
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/search", method = POST)
	public String readerborrow(@RequestParam(value = "searchContent", defaultValue = "") String searchContent,
			                                 Model model) {
		
		Reader reader = readerRepository.getReader(searchContent);
		if(reader!=null) {
			model.addAttribute("reader",reader);
			List<Borrow> borrows =borrowRepository.getlenBorrows(searchContent);
			List<Reserved> reserveds = new ArrayList<Reserved>();
			for(Borrow borrow : borrows) {
				Book book = bookRepository.getBook(borrow.getBookNo());
				Reserved reserved = new Reserved(book.getBookNo(),book.getTitle(),searchContent,borrow.getShouldReturnTime());
				reserveds.add(reserved);
			}
			
			model.addAttribute("reutrnBooks",reserveds);
		}
		
		return "viewReader";
	}
}
