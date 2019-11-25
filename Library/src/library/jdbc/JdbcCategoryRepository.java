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

import library.db.CategoryRepository;
import library.domain.Category;
import library.web.PaginationSupport;

/**
 * 分类管理数据库接口Jdbc实现
 * 
 * @author ozj
 * @version 1.0
 */
@Repository
public class JdbcCategoryRepository implements CategoryRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcCategoryRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public int categoryCount() {
		return jdbc.queryForInt("select count(*) from category");
	}

	@Override
	public Boolean addCategory(Category category) {
		int no = jdbc.queryForInt("select max(categoryno) from category") + 1;
		List<Category> lists =  getCategorys();
		boolean isexit = false;
		for(Category category1 :lists) {
			if(category.getCategoryName().equals(category1.getCategoryName())) {
				isexit = true;
			}
		}
		if(isexit) {
			return false;
		}
		jdbc.update(INSERT_CATEGORY, no + 1 , category.getCategoryName());
		return true;
	}

	@Override
	public void deleteCategory(int categoryNo) {
		jdbc.update(DELETE_CATEGORY, categoryNo);

	}

	@Override
	public void updateCategory(Category category) {
		jdbc.update(UPDATE_CATEFORY, category.getCategoryName(), category.getCategoryNo());

	}

	@Override
	public Category getCategory(int categoryNo) {
		Category category = null;
		try {
			category = jdbc.queryForObject(SELECT_CATEGORY + "where categoryno = ? ", new CategoryRowMapper(),
					categoryNo);
		} catch (DataAccessException e) {

		}
		return category;
	}

	@Override
	public List<Category> getCategorys() {
		return jdbc.query(SELECT_CATEGORY, new CategoryRowMapper());
	}

	@Override
	public PaginationSupport<Category> findPage(int pageNo, int pageSize) {
		int totalCount = categoryCount();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Category>(new ArrayList<Category>(0), 0);

		List<Category> items = jdbc.query(SELECT_PAGE_CATEGORY, new CategoryRowMapper(), pageSize, startIndex);
		PaginationSupport<Category> rs = new PaginationSupport<Category>(items, totalCount, pageSize, startIndex);
		return rs;
	}

	private static class CategoryRowMapper implements RowMapper<Category> {
		public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Category(rs.getInt("categoryno"), rs.getString("categoryname"));
		}
	}

	private static final String SELECT_CATEGORY = "select * from category";

	private static final String INSERT_CATEGORY = "insert into category (categoryno, categoryname) values (?,?)";

	private static final String DELETE_CATEGORY = "delete from category where categoryno = ?";

	private static final String UPDATE_CATEFORY = "update category set categoryname = ? where categoryno = ?";

	private static final String SELECT_PAGE_CATEGORY = SELECT_CATEGORY + "order by categoryno desc limit ? offset  ?";
}
