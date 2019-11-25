package library.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 书籍类
 * 
 * @author ozj
 * @version 1.0
 */
public class Book {

	private int bookNo;

	@NotNull
	@Size(min = 4, max = 30)
	private String title;

	@NotNull
	@Size(min = 4, max = 30)
	private String author;

	@NotNull
	private String price;

	@NotNull
	private String time;

	@NotNull
	private String publish;

	@NotNull
	private String brief;

	@NotNull
	private String ISBN;

	@NotNull
	private String category;

	@NotNull
	private String location;

	@NotNull
	private int status;

	public Book() {

	}

	/**
	 * @param bookNo   书籍no
	 * @param title    书名
	 * @param author   作者
	 * @param price    价格
	 * @param time     出版日期
	 * @param publish  出版社
	 * @param brief    简介
	 * @param iSBN     ISBN
	 * @param category 分类
	 * @param location 位置
	 * @param status   状态
	 */
	public Book(int bookNo, String title, String author, String price, String time, String publish, String brief,
			String iSBN, String category, String location, int status) {
		super();
		this.bookNo = bookNo;
		this.title = title;
		this.author = author;
		this.price = price;
		this.time = time;
		this.publish = publish;
		this.brief = brief;
		this.ISBN = iSBN;
		this.category = category;
		this.location = location;
		this.status = status;
	}

	/**
	 * 返回no
	 * 
	 * @return the bookNo
	 */
	public int getBookNo() {
		return bookNo;
	}

	/**
	 * 返回书名
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 返回作者
	 * 
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * 返回价格
	 * 
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * 返回出版时间
	 * 
	 * @return the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * 返回出版社
	 * 
	 * @return the publish
	 */
	public String getPublish() {
		return publish;
	}

	/**
	 * 返回简介
	 * 
	 * @return the brief
	 */
	public String getBrief() {
		return brief;
	}

	/**
	 * 返回ISBN
	 * 
	 * @return the iSBN
	 */
	public String getISBN() {
		return ISBN;
	}

	/**
	 * 返回分类
	 * 
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * 返回位置
	 * 
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * 返回状态
	 * 
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * 设置no
	 * 
	 * @param bookNo the bookNo to set
	 */
	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	/**
	 * 设置书名
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 设置作者
	 * 
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * 设置价格
	 * 
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}

	/**
	 * 、 设置出版时间
	 * 
	 * @param time the time to set
	 */
	public void setTime(String time) {
		this.time = time;
	}

	/**
	 * 设置出版社
	 * 
	 * @param publish the publish to set
	 */
	public void setPublish(String publish) {
		this.publish = publish;
	}

	/**
	 * 设置简介
	 * 
	 * @param brief the brief to set
	 */
	public void setBrief(String brief) {
		this.brief = brief;
	}

	/**
	 * 设置ISBN
	 * 
	 * @param iSBN the iSBN to set
	 */
	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	/**
	 * 设置分类
	 * 
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * 设置位置
	 * 
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * 设置状态
	 * 
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

}
