package library.domain;

import java.sql.Timestamp;

/**
 * 删除记录类
 * 
 * @author ozj
 * @version 1.0
 */
public class Delete {
	
	private int bookNo;
	private String librarianUsername;
	private Timestamp deleteTime;
	private String title;
	private String author;

	public Delete() {
		super();
	}

	/**
	 * 
	 * @param bookNo            删除的书的no
	 * @param librarianUsername 删除书的管理员的用户名
	 * @param deleteTime        删除时间
	 */
	public Delete(int bookNo, String librarianUsername, Timestamp deleteTime) {
		super();
		this.bookNo = bookNo;
		this.librarianUsername = librarianUsername;
		this.deleteTime = deleteTime;
	}

	/**
	 * 
	 * @param bookNo            删除书籍的no
	 * @param librarianUsername 删除书籍的管理员的用户名
	 * @param deleteTime        删除时间
	 * @param title             删除的书的书名
	 * @param author            删除的书的作者
	 */
	public Delete(int bookNo, String librarianUsername, Timestamp deleteTime, String title, String author) {
		super();
		this.bookNo = bookNo;
		this.librarianUsername = librarianUsername;
		this.deleteTime = deleteTime;
		this.title = title;
		this.author = author;
	}

	/**
	 * 返回删除的书的no
	 * 
	 * @return the bookNo
	 */
	public int getBookNo() {
		return bookNo;
	}

	/**
	 * 返回删除的书的管理员的用户名
	 * 
	 * @return the librarianUsername
	 */
	public String getLibrarianUsername() {
		return librarianUsername;
	}

	/**
	 * 返回删除的书的删除时间
	 * 
	 * @return the deleteTime
	 */
	public Timestamp getDeleteTime() {
		return deleteTime;
	}

	/**
	 * 返回删除的书的题目
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 返回删除的书的作者
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 设置要删除的书的no
	 * 
	 * @param bookNo the bookNo to set
	 */
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	/**
	 * 设置要删除的书的管理员的用户名
	 * 
	 * @param librarianUsername the librarianUsername to set
	 */
	public void setLibrarianUsername(String librarianUsername) {
		this.librarianUsername = librarianUsername;
	}

	/**
	 * 设置要删除的书的删除时间
	 * 
	 * @param deleteTime the deleteTime to set
	 */
	public void setDeleteTime(Timestamp deleteTime) {
		this.deleteTime = deleteTime;
	}

	/**
	 * 设置要删除的书的题目
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 设置要删除的书的作者
	 * 
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

}
