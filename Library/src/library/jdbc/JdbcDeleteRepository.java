package library.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.db.DeleteRepository;
import library.domain.Delete;
import library.web.PaginationSupport;

/**
 * 删除记录资源库接口的jdbc实现类
 * 
 * @author lhb
 * @version v1.0
 */

@Repository
public class JdbcDeleteRepository implements DeleteRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcDeleteRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	/**
	 * 添加删除记录
	 * 
	 * @param delete
	 */
	@Override
	public void addDelete(Delete delete) {
		jdbc.update(INSERT_DELETE, delete.getBookNo(), delete.getLibrarianUsername(), delete.getDeleteTime(),
				delete.getTitle(), delete.getAuthor());

	}

	/**
	 * 取得所有的删除记录
	 * 
	 * @return 删除记录列表
	 */
	@Override
	public List<Delete> getDeletes() {
		return jdbc.query(SELECT_DELETE, new DeleteRowMapper());
	}

	/**
	 * 依据页码和指定页面大小，返回删除记录列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	@Override
	public PaginationSupport<Delete> findPage(int pageNo, int pageSize) {
		int totalCount = (int) jdbc.queryForLong("select count(*) from Deleterec");
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Delete>(new ArrayList<Delete>(0), 0);

		List<Delete> items = jdbc.query(SELECT_PAGE_DELETE, new DeleteRowMapper(), pageSize, startIndex);
		PaginationSupport<Delete> ps = new PaginationSupport<Delete>(items, totalCount, pageSize, startIndex);
		return ps;
	}

	private static class DeleteRowMapper implements RowMapper<Delete> {
		public Delete mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Delete(rs.getInt("bookno"), rs.getString("librarianUsername"), rs.getTimestamp("deleteTime"),
					rs.getString("title"), rs.getString("author"));
		}
	}

	private static final String INSERT_DELETE = "insert into Deleterec (bookNo, librarianUsername, deleteTime, title, author) values (?, ?, ?, ?, ?)";

	private static final String SELECT_DELETE = "select bookno, librarianUsername, deleteTime, title, author from Deleterec";

	private static final String SELECT_PAGE_DELETE = SELECT_DELETE + "  limit ? offset  ?";

}