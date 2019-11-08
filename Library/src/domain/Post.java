package domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * 公告类
 * 
 * @author xh
 * @version 1.0
 */
public class Post {

	private int postno;

	@NotNull
	private Timestamp posttime;

	@NotNull
	@Size(min = 4, max = 20)
	private String title;

	@NotNull
	@Size(min = 20, max = 400)
	private String content;

	@NotNull
	private String librarianno;

	public Post() {
		super();
	}

	/**
	 * 
	 * @param postno      公告no
	 * @param posttime    发布时间
	 * @param title       公告题目
	 * @param content     公告内容
	 * @param librarianno 发布公告的管理员
	 */
	public Post(int postno, Timestamp posttime, String title, String content, String librarianno) {
		super();
		this.postno = postno;
		this.posttime = posttime;
		this.title = title;
		this.content = content;
		this.librarianno = librarianno;
	}

	/**
	 * 返回公告的No
	 * 
	 * @return the postno
	 */
	public int getPostno() {
		return postno;
	}

	/**
	 * 返回发布的时间
	 * 
	 * @return the posttime
	 */
	public Timestamp getPosttime() {
		return posttime;
	}

	/**
	 * 返回公告的题目
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 返回公告的内容
	 * 
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 返回管理员No
	 * 
	 * @return the librarianno
	 */
	public String getLibrarianno() {
		return librarianno;
	}

	/**
	 * 设置公告的no
	 * 
	 * @param postno the postno to set
	 */
	public void setPostno(int postno) {
		this.postno = postno;
	}

	/**
	 * 设置公告的发布时间
	 * 
	 * @param posttime the posttime to set
	 */
	public void setPosttime(Timestamp posttime) {
		this.posttime = posttime;
	}

	/**
	 * 设置公告的题目
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 设置公告的内容
	 * 
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 设置公告的发布者
	 * 
	 * @param librarianno the librarianno to set
	 */
	public void setLibrarianno(String librarianno) {
		this.librarianno = librarianno;
	}

}
