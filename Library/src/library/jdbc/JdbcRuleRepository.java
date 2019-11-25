package library.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.db.RuleRepository;
import library.domain.Rule;

/**
 * 规则管理数据库接口Jbdc实现
 * 
 * @author ozj
 * @version 1.0
 */
@Repository
public class JdbcRuleRepository implements RuleRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcRuleRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public int getFine() {
		int fine = jdbc.queryForInt("select bookfine from rule");
		return fine;
	}

	@Override
	public Rule getRule() {
		return jdbc.queryForObject("select * from rule", new RuleRowMapper());
	}

	private static class RuleRowMapper implements RowMapper<Rule> {
		public Rule mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Rule(rs.getInt("bookfine"), rs.getInt("returnperiod"), rs.getInt("securitydesposit"));
		}
	}

	@Override
	public void updateFine(int fine) {
		jdbc.update(UPDATE_RULE + " bookfine = " + fine + "");

	}

	@Override
	public void updateReturnPeriod(int returnPeriod) {
		jdbc.update(UPDATE_RULE + " returnperiod = " + returnPeriod + "");

	}

	@Override
	public void updateSecurityDesposit(int securityDesposit) {
		jdbc.update(UPDATE_RULE + " securitydesposit = " + securityDesposit);

	}

	private static final String UPDATE_RULE = "update rule set ";
}
