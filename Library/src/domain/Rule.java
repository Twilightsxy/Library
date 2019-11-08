package domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 借阅规则类
 * 
 * @author ozj
 * @version 1.0
 */
public class Rule {

	@NotNull
	@Size(min = 0)
	private int bookFine;// 一天的罚金

	@NotNull
	@Size(min = 0)
	private int returnPeriod;// 能借多久

	@NotNull
	@Size(min = 0)
	private int securityDesposit;// 保证金

	public Rule() {

	}

	/**
	 * 
	 * @param bookFine         逾期一天的罚金
	 * @param returnPeriod     借书时长
	 * @param securityDesposit 保证金
	 */
	public Rule(int bookFine, int returnPeriod, int securityDesposit) {
		super();
		this.bookFine = bookFine;
		this.returnPeriod = returnPeriod;
		this.securityDesposit = securityDesposit;

	}

	/**
	 * 返回逾期一天的罚金
	 * 
	 * @return the bookFine
	 */
	public int getBookFine() {
		return bookFine;
	}

	/**
	 * 返回借书时长
	 * 
	 * @return the returnPeriod
	 */
	public int getReturnPeriod() {
		return returnPeriod;
	}

	/**
	 * 返回保证金
	 * 
	 * @return the securityDesposit
	 */
	public int getSecurityDesposit() {
		return securityDesposit;
	}

	/**
	 * 设置逾期一天的罚金
	 * 
	 * @param bookFine the bookFine to set
	 */
	public void setBookFine(int bookFine) {
		this.bookFine = bookFine;
	}

	/**
	 * 设置借书时长
	 * 
	 * @param returnPeriod the returnPeriod to set
	 */
	public void setReturnPeriod(int returnPeriod) {
		this.returnPeriod = returnPeriod;
	}

	/**
	 * 设置保证金
	 * 
	 * @param securityDesposit the securityDesposit to set
	 */
	public void setSecurityDesposit(int securityDesposit) {
		this.securityDesposit = securityDesposit;
	}

}
