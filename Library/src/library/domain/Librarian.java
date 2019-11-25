package library.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

/**
 * 管理员类
 * 
 * @author ozj
 * @version 1.0
 *
 */
public class Librarian {

	@NotNull
	@Size(min = 5, max = 16)
	private String lUserName;

	@NotNull
	@Size(min = 5, max = 25)
	private String lPassword;

	@NotNull
	@Size(min = 3, max = 30)
	private String lName;

	@NotNull
	@Size(min = 1, max = 1)
	private String lSex;

	@NotNull
	@Email
	private String lEmail;

	@NotNull
	@Size(min = 11, max = 11)
	private String lTel;

	@Size(min = 1, max = 1)
	private boolean deleteS;

	public Librarian() {

	}

	/**
	 * @param lUserName 用户名
	 * @param lPassword 密码
	 * @param lName     姓名
	 * @param lSex      性别
	 * @param lEmail    邮箱
	 * @param lTel      电话
	 * @param deleteS   删除标记
	 */
	public Librarian(String lUserName, String lPassword, String lName, String lSex, String lEmail, String lTel,
			boolean deleteS) {
		super();
		this.lUserName = lUserName;
		this.lPassword = lPassword;
		this.lName = lName;
		this.lSex = lSex;
		this.lEmail = lEmail;
		this.lTel = lTel;
		this.deleteS = deleteS;
	}

	/**
	 * 返回用户名
	 * 
	 * @return the lUserName
	 */
	public String getlUserName() {
		return lUserName;
	}

	/**
	 * 返回用户密码
	 * 
	 * @return the lPassword
	 */
	public String getlPassword() {
		return lPassword;
	}

	/**
	 * 返回用户姓名
	 * 
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * 返回性别
	 * 
	 * @return the lSex
	 */
	public String getlSex() {
		return lSex;
	}

	/**
	 * 返回邮箱
	 * 
	 * @return the lEmail
	 */
	public String getlEmail() {
		return lEmail;
	}

	/**
	 * 返回电话
	 * 
	 * @return the lTel
	 */
	public String getlTel() {
		return lTel;
	}

	/**
	 * 返回删除标记
	 * 
	 * @return the deleteS
	 */
	public boolean isDeleteS() {
		return deleteS;
	}

	/**
	 * 设置用户名
	 * 
	 * @param lUserName the lUserName to set
	 */
	public void setlUserName(String lUserName) {
		this.lUserName = lUserName;
	}

	/**
	 * 设置用户密码
	 * 
	 * @param lPassword the lPassword to set
	 */
	public void setlPassword(String lPassword) {
		this.lPassword = lPassword;
	}

	/**
	 * 设置用户姓名
	 * 
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * 设置用户性别
	 * 
	 * @param lSex the lSex to set
	 */
	public void setlSex(String lSex) {
		this.lSex = lSex;
	}

	/**
	 * 设置用户邮箱
	 * 
	 * @param lEmail the lEmail to set
	 */
	public void setlEmail(String lEmail) {
		this.lEmail = lEmail;
	}

	/**
	 * 设置用户电话
	 * 
	 * @param lTel the lTel to set
	 */
	public void setlTel(String lTel) {
		this.lTel = lTel;
	}

	/**
	 * 设置删除标记
	 * 
	 * @param deleteS the deleteS to set
	 */
	public void setDeleteS(boolean deleteS) {
		this.deleteS = deleteS;
	}

	/**
	 * @param obj 需要比较的对象
	 * @return boolean 相同为true，不同为false
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Librarian other = (Librarian) obj;
		if (lUserName == null) {
			if (other.lUserName != null)
				return false;
		} else if (!lUserName.equals(other.lUserName))
			return false;
		return true;
	}

}
