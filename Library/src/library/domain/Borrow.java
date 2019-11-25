package library.domain;


import java.sql.Timestamp;

/**
 * 借阅类
 * 
 * @author lhb
 * @version 1.0
 *
 */
public class Borrow {
	private int bookNo;
	private String title;
	private String author;
	private String readerNo;
	private Timestamp borrowTime;
	private boolean isReturned;
	private Timestamp shouldReturnTime;
	private Timestamp returnTime;
	private int fine;

	public Borrow() {

	}

	/**
	 * 构造方法
	 * 
	 * @param bookNo           书号
	 * @param title            标题
	 * @param author           作者
	 * @param readerNo         读者账号
	 * @param borrowTime       借阅时间
	 * @param isReturned       借阅标记
	 * @param shouldReturnTime 应还时间
	 * @param returnTime       归还时间
	 * @param fine             罚金
	 */
	public Borrow(int bookno, String title, String author, String readerno, Timestamp borrowtime, boolean isreturned,
			Timestamp shouldreturntime, Timestamp returntime, int fine) {
		super();
		this.bookNo = bookno;
		this.title = title;
		this.author = author;
		this.readerNo = readerno;
		this.borrowTime = borrowtime;
		this.isReturned = isreturned;
		this.shouldReturnTime = shouldreturntime;
		this.returnTime = returntime;
		this.fine = fine;
	}
	/**
	 * 取得标题
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置标题
	 * 
	 * @return
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 取得作者
	 * 
	 * @return
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * 设置作者
	 * 
	 * @return
	 */
	public void setAuthor(String author) {
		this.author = author;
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
	 * 取得借阅时间
	 * 
	 * @return
	 */
	public Timestamp getBorrowTime() {
		return borrowTime;
	}
	/**
	 * 设置借阅时间
	 * 
	 * @return
	 */
	public void setBorrowTime(Timestamp borrowTime) {
		this.borrowTime = borrowTime;
	}
	/**
	 * 取得借阅标记
	 * 
	 * @return
	 */
	public boolean isReturned() {
		return isReturned;
	}
	/**
	 * 设置借阅标记
	 * 
	 * @return
	 */
	public void setReturned(boolean isReturned) {
		this.isReturned = isReturned;
	}
	/**
	 * 取得应还时间
	 * 
	 * @return
	 */
	public Timestamp getShouldReturnTime() {
		return shouldReturnTime;
	}
	/**
	 * 设置应还时间
	 * 
	 * @return
	 */
	public void setShouldReturnTime(Timestamp shouldReturnTime) {
		this.shouldReturnTime = shouldReturnTime;
	}
	/**
	 * 取得归还时间
	 * 
	 * @return
	 */
	public Timestamp getReturnTime() {
		return returnTime;
	}
	/**
	 * 设置归还时间
	 * 
	 * @return
	 */
	public void setReturnTime(Timestamp returnTime) {
		this.returnTime = returnTime;
	}
	/**
	 * 取得罚金
	 * 
	 * @return
	 */
	public int getFine() {
		return fine;
	}
	/**
	 * 设置罚金
	 * 
	 * @return
	 */
	public void setFine(int fine) {
		this.fine = fine;
	}
}
