package domain;

import java.sql.Timestamp;

/**
 * 预定类
 * 
 * @author lhb
 * @version 1.0
 *
 */
public class Reserve {

	private int bookNo;
	private String readerNo;
	private Timestamp reserveTime;

	public Reserve() {
		super();
	}

	/**
	 * 构造方法
	 * 
	 * @param bookNo      书号
	 * @param readerNo    读者号
	 * @param reserveTime 预定时间
	 */
	public Reserve(int bookNo, String readerNo, Timestamp reserveTime) {
		this.bookNo = bookNo;
		this.readerNo = readerNo;
		this.reserveTime = reserveTime;
	}

	/**
	 * 取得书号
	 * 
	 * @return
	 */
	public int getBookNo() {
		return bookNo;
	}

	/**
	 * 设置书号
	 * 
	 * @return
	 */
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	/**
	 * 取得读者号
	 * 
	 * @return
	 */
	public String getReaderNo() {
		return readerNo;
	}

	/**
	 * 设置读者号
	 * 
	 * @return
	 */
	public void setReaderNo(String readerNo) {
		this.readerNo = readerNo;
	}

	/**
	 * 取得预定时间
	 * 
	 * @return
	 */
	public Timestamp getReserveTime() {
		return reserveTime;
	}

	/**
	 * 设置预定时间
	 * 
	 * @return
	 */
	public void setReserveTime(Timestamp reserveTime) {
		this.reserveTime = reserveTime;
	}
}