package library.web;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import library.db.BookRepository;
import library.db.CartRepository;
import library.db.*;
import library.domain.*;


/**
 * 系统主页控制类
 * 
 * @author zbl
 * @version v1.0
 */
@Controller // 控制定义
@SessionAttributes({ "reader" })
@RequestMapping("/reader") // 相应web路径
public class ReaderController {

	@Autowired // 自动注入资源
	private CartRepository cartRepository; 
	
	@Autowired // 自动注入资源
	private BookRepository bookRepository;
	
	@Autowired // 自动注入资源
	private ReserveRepository reserveRepository;
	
	@Autowired // 自动注入资源
	private BorrowRepository borrowRepository;
	

	/**
	 * 进入cart界面
	 * 
	 * @return cart.jsp
	 */
	@RequestMapping(value="/cart", method = GET)
	public String showcart(HttpServletRequest request,Model model) {

		HttpSession session = request.getSession();
	    String username = (String)session.getAttribute("username");
	   
	    List<Book> book = new ArrayList<Book>();
	    
	    List<Integer> book0 = cartRepository.getCarts(username);
	    for(int bookno : book0) {
	    	Book book2 = bookRepository.getBook(bookno);
	    	if(book2!= null&&book2.getStatus()!=1&&book2.getStatus()!=2)
	    	book.add(book2);
	    }
	    
	    
	    List<Reserve> reserves = reserveRepository.getReserves();
	    for(Reserve reserve :reserves) {
	    	if(reserve.getReaderNo().equals(username))
	    		book.add(bookRepository.getBook(reserve.getBookNo()));
	    }
	    
	    
	    List<Borrow> borrows = borrowRepository.getlenBorrows(username);
	    for(Borrow borrow :borrows) {
	    	book.add(bookRepository.getBook(borrow.getBookNo()));
	    }
	    
	    model.addAttribute("books", book);
	    
		return "cart";
	}
	
	/**
	    * 加入购物车
	 * 
	 * @return home.jsp
	 * @throws IOException 
	 */
	@RequestMapping(value = "/cart/{bookNo}", method = GET)
	public String addcart(@PathVariable("bookNo") int bookNo, Model model,
			@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize,
			HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/html;charset=gb2312");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
	    String username = (String)session.getAttribute("username");
	   
	    List<Integer> books = cartRepository.getCarts(username);
	    
	    int i = 0;
	    for(Integer in : books) {
	    	if(in == bookNo) {
	    		i =1 ; break;
	    	}
	    }
	    
	    if( i==0) {
		    Cart cart = new Cart(bookNo,username);
		   
		    
			cartRepository.addCart(cart);
			out.print("<script type='text/javascript'>alert('书籍添加购物车成功！');location.href='/Library/home'</script>");
			out.flush();
			out.close();
		   }
	    else {
	    	out.print("<script type='text/javascript'>alert('该书已在购物车！');location.href='/Library/home'</script>");
			out.flush();
			out.close();
	    }
		
	    
		
	    model.addAttribute("books", bookRepository.findPage(pageNo, pageSize));
		
		return "home";
	}

	/**
	       申请借阅书籍
	 * 
	 * @return cart.jsp
	 * @throws IOException 
	 */
	@RequestMapping(value ="/cart/reserve/{bookNo}" , method = GET)
	public String reserve(@PathVariable("bookNo") int bookNo,
			       HttpServletRequest request,Model model) throws IOException {
		
		
		HttpSession session = request.getSession();
	    String username = (String)session.getAttribute("username");
	    
        Book booktoreserve = bookRepository.getBook(bookNo);
        List<Integer> book0 = cartRepository.getCarts(username);
        
        for(int bookno : book0) {
        	if(bookno==bookNo) {  //首先判断该书在购物车当中
        		 Cart cart = new Cart(bookNo,username);
        		 
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
        		   	    
        		   	    
        		   	    Reserve reserve0 = new Reserve(bookNo,username,date2);
        		   	 try { 
        		   	    cartRepository.deleteCart(cart);
        		   	    reserveRepository.addReserve(reserve0);
        		   	    booktoreserve.setStatus(1);
        		   	    bookRepository.updateBook(booktoreserve);
        		   	} catch (Exception e) {
        				// TODO Auto-generated catch block
        				e.printStackTrace();
        			}       
        	}
        } 	    
	    
	    List<Book> book = new ArrayList<Book>();
	    
	    book0 = cartRepository.getCarts(username);
	    for(int bookno : book0) {
	    	Book book2 = bookRepository.getBook(bookno);
	    	if(book2!= null&&book2.getStatus()!=1&&book2.getStatus()!=2)
	    	book.add(book2);
	    }
	    
	    
	    List<Reserve> reserves = reserveRepository.getReserves();
	    for(Reserve reserve :reserves) {
	    	if(reserve.getReaderNo().equals(username))
	    		book.add(bookRepository.getBook(reserve.getBookNo()));
	    }
	    
	    
	    List<Borrow> borrows = borrowRepository.getlenBorrows(username);
	    for(Borrow borrow :borrows) {
	    	book.add(bookRepository.getBook(borrow.getBookNo()));
	    }
	    
	    model.addAttribute("books", book);
	    
		return "cart";
	}
	
      /**
	 * 进入书籍管理页面
	 * 
	 * @return personInfo.jsp
	 */

	@RequestMapping(value="/personInfo", method = GET)
	public String checkpage(HttpServletRequest request, Model model) {
		
		
		HttpSession session = request.getSession();
	    String username = (String)session.getAttribute("username");
        List<Borrow> borrowing = borrowRepository.getlenBorrows(username);
        List<Borrow> borrowed = borrowRepository.getFinBorrows(username);
        
        
	    model.addAttribute("ingBooks",borrowing);
	    model.addAttribute("returnBooks",borrowed);
		return "personInfo";
	}
	
}
