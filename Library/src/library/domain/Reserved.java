package library.domain;

import java.sql.Timestamp;

/**
 * 预定类额外加上书名
 * 
 * @author zbl
 * @version 1.0
 *
 */
public class Reserved {

	
	private int bookNo;
	private String title;
	private String readerNo;
	private Timestamp reserveTime;
	
	
	public Reserved() {
		super();
	}
	/**
	 * 构造方法
	 * 
	 * @param bookNo      书号
	 * @param title       书名
	 * @param readerNo    读者号
	 * @param reserveTime 预定时间
	 */
	public Reserved(int bookNo,String title, String readerNo, Timestamp reserveTime) {
		this.bookNo = bookNo;
		this.title = title;
		this.readerNo = readerNo;
		this.reserveTime = reserveTime;
	}
	public int getBookNo() {
		return bookNo;
	}
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReaderNo() {
		return readerNo;
	}
	public void setReaderNo(String readerNo) {
		this.readerNo = readerNo;
	}
	public Timestamp getReserveTime() {
		return reserveTime;
	}
	public void setReserveTime(Timestamp reserveTime) {
		this.reserveTime = reserveTime;
	}
	
}
