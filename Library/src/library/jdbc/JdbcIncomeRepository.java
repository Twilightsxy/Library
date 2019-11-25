package library.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.db.IncomeRepository;
import library.domain.Income;
import library.domain.Incomegroup;

/**
 * 收入资源库接口的jdbc实现类
 * 
 * @author lhb
 * @version v1.0
 */

@Repository
public class JdbcIncomeRepository implements IncomeRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcIncomeRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	/**
	 * 添加收入记录
	 * 
	 * @param income
	 */
	@Override
	public void addIncome(Income income) {
		jdbc.update(INSERT_INCOME, income.getDay(), income.getTotalfine(), income.getTotaldeposit());
	}

	/**
	 * 获取今日收入
	 * 
	 * @return 今日收入
	 */
	@Override
	public Incomegroup getTodayIncome() {
		Incomegroup total = new Incomegroup();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			String sdate = sdf.format(sqlDate);
			String const_s = "\'" + sdate + "\'";

			String sql = "select * from income where day = " + const_s;
			List<Income> incomes = jdbc.query(sql, new IncomeRowMapper());
			for (int i = 0; i < incomes.size(); i++) {
				Income income = (Income) incomes.get(i);
				int fine = income.getTotalfine();
				int desposit = income.getTotaldeposit();
				int sumincome = fine + desposit;
				total.setIncome(sumincome);
				total.setFine(fine);
				total.setDeposit(desposit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;

	}


	/**
	 * 增加今日收入
	 * 
	 * @param deposit 增加的金额
	 * 
	 * @return 新的今日收入
	 * @throws ParseException 
	 */
	@Override
	public Incomegroup addTodayIncome(int deposit) throws ParseException {
		Incomegroup total = new Incomegroup();
		Incomegroup t = getTodayIncome();
		int oldDeposit = t.getDeposit();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			String sdate = sdf.format(sqlDate);
			Date sdate1=sdf.parse(sdate);
			String sql = "update income set totaldeposit=? where day =? ";
			jdbc.update(sql, oldDeposit + deposit, sdate1);
		return total;
	}


	/**
	 * 减少今日收入
	 * 
	 * @param deposit 减少的金额
	 * 
	 * @return 新的今日收入
	 * @throws ParseException 
	 */
	@Override
	public Incomegroup decreaseTodayIncome(int deposit) throws ParseException {
		Incomegroup total = new Incomegroup();
		Incomegroup t = getTodayIncome();
		int oldDeposit = t.getDeposit();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			String sdate = sdf.format(sqlDate);
			Date sdate1=sdf.parse(sdate);
			String sql = "update income set totaldeposit=? where day =? ";
			jdbc.update(sql, oldDeposit - deposit, sdate1);
		return total;
	}
	
	
private static class IncomeRowMapper implements RowMapper<Income> {
	public Income mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Income(rs.getDate("day"), rs.getInt("totalfine"), rs.getInt("totaldeposit"));
	}}

	private static final String INSERT_INCOME = "insert into Income (day, totalfine, totaldeposit) values (?, ?, ?)";

}
