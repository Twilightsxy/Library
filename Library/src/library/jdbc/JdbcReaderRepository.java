package library.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.db.ReaderRepository;
import library.domain.Reader;
import library.web.PaginationSupport;

/**
 * 读者数据库接口jdbc实现
 * 
 * @author ozj
 * @version 1.0
 */
@Repository
public class JdbcReaderRepository implements ReaderRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcReaderRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public void addReader(Reader reader) {
		jdbc.update(INSERT_READER, reader.getReaderNo(), reader.getReaderName(), reader.getReaderPassword(),
				reader.getEmail(), reader.getFine());

	}

	@Override
	public void deleteReader(String readerNo) {
		jdbc.update(DELETE_READER, readerNo);
	}

	@Override
	public void updateReader(Reader reader, String oldReaderNo) {
		jdbc.update(UPDATE_READER, reader.getReaderNo(), reader.getReaderName(), reader.getReaderPassword(),
				reader.getEmail(), Integer.valueOf(reader.getFine()), oldReaderNo);
	}

	@Override
	public void updateReaderName(String readerNo, String name) {
		jdbc.update("update reader set readername = '" + name + "' " + " where readerno = '" + readerNo + "'");

	}

	@Override
	public void updateReaderEmial(String readerNo, String emial) {
		jdbc.update("update reader set emial = '" + emial + "' " + " where readerno = '" + readerNo + "'");

	}

	@Override
	public void updateReaderPassword(String readerNo, String password) {
		jdbc.update("update reader set readerpassword = '" + password + "' " + " where readerno = '" + readerNo + "'");

	}

	@SuppressWarnings("deprecation")
	@Override
	public int getReaderCount() {
		return jdbc.queryForInt("select count(readerno) from reader");
	}

	@Override
	public List<Reader> getReaders(String readerNo) {
		return jdbc.query("select * from reader where readerno like \'%" + readerNo + "%\' order by readerno asc",
				new ReaderRowMapper());
	}

	@Override
	public Reader getReader(String readerNo) {
		Reader reader = null;
		try {
			reader = jdbc.queryForObject(SELECT_READER + " where readerno = ? ", new ReaderRowMapper(), readerNo);
		} catch (DataAccessException e) {

		}
		return reader;
	}

	@Override
	public PaginationSupport<Reader> findPage(int pageNo, int pageSize) {
		int totalCount = getReaderCount();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Reader>(new ArrayList<Reader>(0), 0);

		List<Reader> items = jdbc.query(SELECT_PAGE_READERS, new ReaderRowMapper(), pageSize, startIndex);
		PaginationSupport<Reader> rs = new PaginationSupport<Reader>(items, totalCount, pageSize, startIndex);
		return rs;
	}

	@Override
	public Reader findByPassword(String userName, String password) {
		Reader reader = null;
		try {
			reader = jdbc.queryForObject(SELECT_READER + " where readerno = ? and readerpassword = ?",
					new ReaderRowMapper(), userName, password);
		} catch (Exception e) {

		}
		return reader;
	}

	@Override
	public boolean getrNo(String userName) {
		boolean test = true;
		String rno = "";
		if (userName.length() != 11 || userName.equals("")) {
			return !test;
		} else {
			try {
				rno = jdbc.queryForObject(SELECT_READER + " where readerno = ? ", new ReaderRowMapper(), userName)
						.getReaderNo();
			} catch (DataAccessException e) {

			}
		}
		if (rno.equals("")) {
			test = false;
		}
		return test;
	}

	@Override
	public boolean get(String rNo, String rPassword) {
		Reader reader = new Reader();
		boolean test = false;
		if (rNo.length() != 11 || rNo.equals("")) {
			return test;
		} else {
			try {
				reader = jdbc.queryForObject(SELECT_READER + " where readerno = ? and readerpassword = ?",
						new ReaderRowMapper(), rNo, rPassword);
			} catch (DataAccessException e) {

			}
			test = rPassword.equals(reader.getReaderPassword());
		}
		return test;
	}

	private static class ReaderRowMapper implements RowMapper<Reader> {
		public Reader mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Reader(rs.getString("readerno"), rs.getString("readername"), rs.getString("readerpassword"),
					rs.getString("email"), rs.getString("readerfine"));
		}
	}

	private static final String SELECT_READER = "select * from reader";

	private static final String INSERT_READER = "insert into reader (readerno, readername, readerpassword, email, readerfine) values(?,?,?,?,?)";

	private static final String DELETE_READER = "delete from reader where readerno = ?";

	private static final String UPDATE_READER = "update reader set readerno = ? ,readername = ? , readerpassword = ?,email = ?,readerfine = ? where readerno = ?";

	private static final String SELECT_PAGE_READERS = SELECT_READER + " order by readerno desc limit ? offset  ?";

}
