package library.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.db.EmailRepository;
import library.domain.Email;

/**
 * 实现邮件数据库接口的Jdbc实现
 * 
 * @author ozj
 * @version 1.0
 */
@Repository
public class JdbcEmailRepository implements EmailRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcEmailRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map emailAndBookNo() {
		List<String> emails = new ArrayList<String>();
		List<Integer> bookNos = new ArrayList<Integer>();
		List<String> titles = new ArrayList<String>();
		List<Integer> days = new ArrayList<Integer>();
		Map map = new HashMap();

		try {
			String sql = "select email,book.bookno,title,shouldreturntime from book,reader,borrow where reader.readerno = borrow.readerno and isreturned = false and borrow.bookno = book.bookno";

			List<Email> list = jdbc.query(sql, new EmailRowMapper());

			for (Email item : list) {
				String email = item.getEmail();
				int bookno = item.getBookno();
				String title = item.getTitle();
				Timestamp shouldreturntime = item.getShouldreturntime();
				long t2 = shouldreturntime.getTime();
				Timestamp nowtime = new Timestamp(System.currentTimeMillis());
				long t1 = nowtime.getTime();

				if (t1 - t2 >= -259200000) {
					emails.add(email);
					bookNos.add(bookno);
					titles.add(title);
					int day = (int) ((t1 - t2) / (1000 * 60 * 60 * 24));

					if (day >= 0) {
						day++;
					} else {
						day--;
					}
					days.add(day);
				}
			}

			map.put("emails", emails);
			map.put("bookNos", bookNos);
			map.put("titles", titles);
			map.put("days", days);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}

	private static class EmailRowMapper implements RowMapper<Email> {
		public Email mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Email(rs.getString("email"), rs.getInt("bookno"), rs.getString("title"),
					rs.getTimestamp("shouldreturntime"));
		}
	}
}
