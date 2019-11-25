package library.domain;

import java.sql.Date;

/**
 * 每天收入类
 * 
 * @author ozj
 * @version 1.0
 */
public class Income {
	
	private Date day;
	private int totalfine;
	private int totaldeposit;

	public Income() {
		super();
	}

	/**
	 * 
	 * @param day          天
	 * @param totalfine    所有的罚金数额
	 * @param totaldeposit 所有的保证金
	 */
	public Income(Date day, int totalfine, int totaldeposit) {
		super();
		this.day = day;
		this.totalfine = totalfine;
		this.totaldeposit = totaldeposit;

	}

	/**
	 * 返回天
	 * 
	 * @return the day
	 */
	public Date getDay() {
		return day;
	}

	/**
	 * 返回所有的罚金
	 * 
	 * @return the totalfine
	 */
	public int getTotalfine() {
		return totalfine;
	}

	/**
	 * 返回所有的保证金
	 * 
	 * @return the totaldeposit
	 */
	public int getTotaldeposit() {
		return totaldeposit;
	}

	/**
	 * 设置天
	 * 
	 * @param day the day to set
	 */
	public void setDay(Date day) {
		this.day = day;
	}

	/**
	 * 设置所有的罚金数额
	 * 
	 * @param totalfine the totalfine to set
	 */
	public void setTotalfine(int totalfine) {
		this.totalfine = totalfine;
	}

	/**
	 * 设置所有的保证金数额
	 * 
	 * @param totaldeposit the totaldeposit to set
	 */
	public void setTotaldeposit(int totaldeposit) {
		this.totaldeposit = totaldeposit;
	}

}
