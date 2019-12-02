package library.domain;

/**
 * 读者购物车类
 * 
 * @author ozj
 * @version 1.0
 *
 */
public class Cart {

	/**
	 * 书籍No
	 */
	private int bookNo;

	/**
	 * 读者No
	 */
	private String readerNo;

	public Cart() {

	}

	/**
	 * 
	 * @param bookNo
	 * @param readerNo
	 */
	public Cart(int bookNo, String readerNo) {

		this.bookNo = bookNo;
		this.readerNo = readerNo;
	}

	/**
	 * @return the bookNo
	 */
	public int getBookNo() {

		return bookNo;
	}

	/**
	 * @param bookNo the bookNo to set
	 */
	public void setBookNo(int bookNo) {

		this.bookNo = bookNo;
	}

	/**
	 * @return the readerNo
	 */
	public String getReaderNo() {

		return readerNo;
	}

	/**
	 * @param readerNo the readerNo to set
	 */
	public void setReaderNo(String readerNo) {

		this.readerNo = readerNo;
	}

}
