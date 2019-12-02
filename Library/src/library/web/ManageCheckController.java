package library.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
 * 审核管理类
 * 
 * @author zbl
 * @version v1.0
 */
@Controller
@SessionAttributes({ "librarian" })
@RequestMapping("/librarian/check")
public class ManageCheckController {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ReserveRepository reserveRepository;
	
	@Autowired
	private BorrowRepository borrowRepository;
	
	
	
	
	/**
	 * 进入书籍管理页面
	 * 
	 * @return check.jsp
	 */
	@RequestMapping( method = GET)
	public String checkpage( Model model) {

		List<Reserve> reserves = reserveRepository.getReserves();
		List<Reserved> reserveds = new ArrayList<Reserved>();
		for(Reserve reserve : reserves) {
			Book book = bookRepository.getBook(reserve.getBookNo());
			Reserved reserved = new Reserved(reserve.getBookNo(),book.getTitle(),reserve.getReaderNo(),reserve.getReserveTime());
			reserveds.add(reserved);
			
		}
		
		model.addAttribute("reserves",reserveds);
	
		return "manageCheck";
	}
	

	/**
	 * 审核通过
	 * 
	 * @return check.jsp
	 */
	@RequestMapping(value ="/audit/{bookNo}" , method = GET)
	public String auditbook(@PathVariable("bookNo") int bookNo, Model model) {
		Book book0 = bookRepository.getBook(bookNo);
		
		if(book0.getStatus()!=2) {
			
			List<Reserve> Reserves = reserveRepository.getReserves();
			Reserve reserve = null;
			for(Reserve  one: Reserves) {
				if(one.getBookNo()==bookNo) {
					reserve = one;
					break;
				}
			}
			
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
 		    long time = System.currentTimeMillis();
 	        String time2 = dateFormat.format(time);
 	        java.util.Date date = new java.util.Date();
 	        try {
 				date = dateFormat.parse(time2);
 			} catch (ParseException e) {
 				// TODO Auto-generated catch block
 				e.printStackTrace();
 			}       
 	        
 		   	   
 		    java.sql.Timestamp date2 = new java.sql.Timestamp(date.getTime());
 		    
 		   long time3 =date2.getTime()+(long)1000*3600*24*30;
 		   time2 = dateFormat.format(time3);
 		  try {
				date = dateFormat.parse(time2);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}       
 		 java.sql.Timestamp date3 = new java.sql.Timestamp(date.getTime());
			
 		 Borrow borrow = new Borrow(bookNo,book0.getTitle(),book0.getAuthor(),reserve.getReaderNo(),
 				 date2,false,date3,null,0);
 		book0.setStatus(2);
 		bookRepository.updateBook(book0);
 		borrowRepository.addBorrow(borrow);
 		reserveRepository.deleteReserve(bookNo);
 		
 		 
		}
		
		List<Reserve> reserves = reserveRepository.getReserves();
		List<Reserved> reserveds = new ArrayList<Reserved>();
		for(Reserve reserve : reserves) {
			Book book = bookRepository.getBook(reserve.getBookNo());
			Reserved reserved = new Reserved(reserve.getBookNo(),book.getTitle(),reserve.getReaderNo(),reserve.getReserveTime());
			reserveds.add(reserved);
			
			
		}
	
		model.addAttribute("reserves",reserveds);
		
		return "manageCheck";
	}
}
