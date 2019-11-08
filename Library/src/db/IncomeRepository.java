package db;

import java.util.List;

import domain.Income;
import domain.Incomegroup;

/**
 * 收入类数据库接口
 * 
 * @author lhb
 * @version 1.0
 */

public interface IncomeRepository {

	/**
	 * 添加收入记录
	 * 
	 * @param income
	 */
	void addIncome(Income income);

	/**
	 * 获取今日收入
	 * 
	 * @return 今日收入
	 */
	Incomegroup getTodayIncome();

	/**
	 * 获取某年所有月收入
	 * 
	 * @param year
	 * @return 月收入列表
	 */
	List<Incomegroup> getMonthIncome(String syear);

	/**
	 * 获取某年所有周收入
	 * 
	 * @param year
	 * @return 周收入列表
	 */
	List<Incomegroup> getWeekIncome(String syear);

	/**
	 * 获取某年某月的每日收入
	 * 
	 * @param year
	 * @param month
	 * @return 每日收入列表
	 */
	List<Incomegroup> getMonthdayIncome(String year, String month);

	/**
	 * 增加今日收入
	 * 
	 * @param deposit 增加的金额
	 * 
	 * @return 新的今日收入
	 */
	Incomegroup addTodayIncome(int deposit);

	/**
	 * 减少今日收入
	 * 
	 * @param deposit 减少的金额
	 * 
	 * @return 新的今日收入
	 */
	Incomegroup decreaseTodayIncome(int deposit);
}
