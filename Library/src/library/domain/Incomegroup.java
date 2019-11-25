package library.domain;

/**
 * 收入类
 * 
 * @author lhb
 * @version v1.0
 */
public class Incomegroup {
    private String year;
    private String month;
    private String week;
    private String day;
    private int income;
    private int fine;
    private int deposit;
    
    public Incomegroup() {
  	  super();
    }
    /**
	 * 构造方法
	 * 
	 * @param year
	 *           年份
	 * @param month
	 *            月份
	 * @param week
	 *            星期
	 * @param income
	 *            收入       
	 * @param fine
	 *            罚金       
	 * @param deposit
	 *            存款   
	 */
    public Incomegroup(String year,String month,String week,int income,int fine,int deposit) {
  	  this.year=year;
  	  this.month=month;
  	  this.week=week;
  	  this.income=income;
  	  this.fine=fine;
  	  this.deposit=deposit;
    }
    /**
	 * 取得年份
	 * 
	 * @return id
	 */
	public String getYear() {
		return year;
	}
	/**
	 * 设置年份
	 * 
	 * @return id
	 */
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * 取得月份
	 * 
	 * @return id
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * 设置月份
	 * 
	 * @return id
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * 取得收入
	 * 
	 * @return id
	 */
	public int getIncome() {
		return income;
	}
	/**
	 * 设置收入
	 * 
	 * @return id
	 */
	public void setIncome(int income) {
		this.income = income;
	}
	/**
	 * 取得罚金
	 * 
	 * @return id
	 */
	public int getFine() {
		return fine;
	}
	/**
	 * 设置罚金
	 * 
	 * @return id
	 */
	public void setFine(int fine) {
		this.fine = fine;
	}
	/**
	 * 取得存款
	 * 
	 * @return id
	 */
	public int getDeposit() {
		return deposit;
	}
	/**
	 * 设置存款
	 * 
	 * @return id
	 */
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	/**
	 * 取得星期
	 * 
	 * @return id
	 */
	public String getWeek() {
		return week;
	}
	/**
	 * 设置星期
	 * 
	 * @return id
	 */
	public void setWeek(String week) {
		this.week = week;
	}
	/**
	 * 取得日期
	 * 
	 * @return id
	 */
	public String getDay() {
		return day;
	}
	/**
	 * 设置日期
	 * 
	 * @return id
	 */
	public void setDay(String day) {
		this.day = day;
	}

	


    
}