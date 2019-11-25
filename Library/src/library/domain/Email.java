package library.domain;

import java.sql.Timestamp;

/**
 * 邮件数据库处理类
 * @author ozj
 *@version 1.0
 */
public class Email {

	private String email;
	private int bookno;
	private String title;
	private Timestamp shouldreturntime;
	
	public Email() {
		super();
	}
	/**
	 * @param emial
	 * @param bookno
	 * @param title
	 * @param shouldreturntime
	 */
	public Email(String email, int bookno, String title, Timestamp shouldreturntime) {
		super();
		this.email = email;
		this.bookno = bookno;
		this.title = title;
		this.shouldreturntime = shouldreturntime;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @return the bookno
	 */
	public int getBookno() {
		return bookno;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return the shouldreturntime
	 */
	public Timestamp getShouldreturntime() {
		return shouldreturntime;
	}
	/**
	 * @param emial the emial to set
	 */
	public void setEmial(String email) {
		this.email = email;
	}
	/**
	 * @param bookno the bookno to set
	 */
	public void setBookno(int bookno) {
		this.bookno = bookno;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @param shouldreturntime the shouldreturntime to set
	 */
	public void setShouldreturntime(Timestamp shouldreturntime) {
		this.shouldreturntime = shouldreturntime;
	}
	
	
}
