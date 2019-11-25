package library.db;

import java.text.ParseException;

import library.domain.Income;
import library.domain.Incomegroup;

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
	 * 增加今日收入
	 * 
	 * @param deposit 增加的金额
	 * 
	 * @return 新的今日收入
	 */
	Incomegroup addTodayIncome(int deposit) throws ParseException;

	/**
	 * 减少今日收入
	 * 
	 * @param deposit 减少的金额
	 * 
	 * @return 新的今日收入
	 */
	Incomegroup decreaseTodayIncome(int deposit) throws ParseException;
}
