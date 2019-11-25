package library.jdbc;

import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.db.MemberRepository;
import library.domain.Member;

/**
 * 成员管理数据库接口的jdbc实现
 * 
 * @author ozj
 * @version 1.0
 *
 */
@Repository
public class JdbcMemberRepository implements MemberRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcMemberRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public List<Member> getMembers() {
		return jdbc.query(SELECT_MEMBER, new MemberRowMapper());
	}

	private static class MemberRowMapper implements RowMapper<Member> {
		public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Member(rs.getInt("memberno"), rs.getString("memberdescribe"), rs.getString("membername"),
					rs.getString("memberpicutre"));
		}
	}

	private static final String SELECT_MEMBER = "select * from teammember";
}
