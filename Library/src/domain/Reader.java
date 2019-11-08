package domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
  * 读者类
 * 
 * @author lhb
 * @version 1.0
 *
 */
public class Reader {
	
	@NotNull
	private String readerNo ;
	
	@NotNull
	@Size(min = 5, max = 16)
	private String readerName;
	
	@NotNull
	@Size(min = 5, max = 25)
	private String readerPassword;
		
	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String fine;

	
	/**
	 * 构造方法
	 * @param readerNo
	 * 			电话
	 * @param readerName
	 * 			用户名
	 * @param readerPassword
	 * 			密码
	 * @param email
	 * 			邮箱
	 * @param fine
	 * 			罚金
	 */
	public Reader() {
		
	}
	
	public Reader(String readerNo, String readerName, String readerPassword, String email, String fine) {
		super();
		this.readerNo = readerNo;
		this.readerName = readerName;
		this.readerPassword = readerPassword;
		this.email = email;
		this.fine = fine;
	}

	/**
	 * @return 读者账号
	 */
	public String getReaderNo() {
		return readerNo;
	}

	/**
	 * @return 读者姓名
	 */
	public String getReaderName() {
		return readerName;
	}

	/**
	 * @return 读者密码
	 */
	public String getReaderPassword() {
		return readerPassword;
	}

	/**
	 * @return 读者邮件
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return 罚金
	 */
	public String getFine() {
		return fine;
	}

	/**
	 * @param 设置读者账户
	 */
	public void setReaderNo(String readerNo) {
		this.readerNo = readerNo;
	}

	/**
	 * @param 设置读者姓名
	 */
	public void setReaderName(String readerName) {
		this.readerName = readerName;
	}

	/**
	 * @param 设置读者密码
	 */
	public void setReaderPassword(String readerPassword) {
		this.readerPassword = readerPassword;
	}

	/**
	 * @param 设置读者邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param 设置罚金
	 */
	public void setFine(String fine) {
		this.fine = fine;
	}
	
	
}
