package library.db;

import library.domain.Rule;

/**
 * 规则管理数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface RuleRepository {

	/**
	 * 取得逾期一天的罚金
	 * 
	 * @return fine
	 */
	int getFine();

	/**
	 * 取得规则对象，包括罚金、借书时长、保证金
	 * 
	 * @return rule 规则对象
	 */
	Rule getRule();

	/**
	 * 更新fine
	 */
	void updateFine(int fine);

	/**
	 * 更新returnPeriod
	 */
	void updateReturnPeriod(int returnPeriod);

	/**
	 * 更新securityDesposit
	 */
	void updateSecurityDesposit(int securityDesposit);
}
