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

import library.db.LibrarianRepository;
import library.domain.Librarian;
import library.web.PaginationSupport;

/**
 * 图书管理员资源库接口的jdbc实现类
 * 
 * @author lhb
 * @version v1.0
 */

@Repository
public class JdbcLibrarianRepository implements LibrarianRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcLibrarianRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	/**
	 * 通过用户名查找该管理员用户名是否存在
	 * 
	 * @param userName
	 * @return 存在返回true,不存在返回false
	 */
	@Override
	public boolean isEsxit(String userName) {
		if(jdbc.queryForInt("select count(*) from Librarian where lusername= '" +userName+"'")>0)
			return true;
		else return false;
	}
	
	/**
	 * 添加一个管理员
	 * 
	 * @param librarian
	 */
	@Override
	public void addLibrarian(Librarian librarian) {
		jdbc.update(INSERT_LIBRARIAN, librarian.getlUserName(), librarian.getlPassword(), librarian.getlName(),
				librarian.getlSex(), librarian.getlTel(), librarian.getlEmail(), false);

	}

	/**
	 * 更新管理员信息
	 * 
	 * @param librarian
	 */
	@Override
	public void updateLibrarian(Librarian librarian,String lUsername) {
		jdbc.update("update Librarian set lUserName='" + librarian.getlUserName() + "' , lPassword=' "
				+ librarian.getlPassword() + "' , lName = '" + librarian.getlName() + "' , lSex = '"
				+ librarian.getlSex() + "' , lemail = '" + librarian.getlEmail() + "' ,lTel= '" + librarian.getlTel()
				+"' where lUsername='"+lUsername+"'");
	}

	/**
	 * 删除管理员，把删除标记设为1
	 * 
	 * @param userName
	 */
	@Override
	public void deleteLibrarian(String userName) {
		jdbc.update("update Librarian set delete =true where lUserName='" + userName+"'");
	}

	/**
	 * 通过用户名查找管理员
	 * 
	 * @param userName
	 * @return
	 */
	@Override
	public Librarian getLibrarian(String userName) {
		Librarian librarian = null;
		try {
			librarian = jdbc.queryForObject(SELECT_LIBRARIAN + " where lusername=?", new LibrarianRowMapper(),
					userName);
		} catch (DataAccessException e) {
		}
		return librarian;
	}

	/**
	 * 取得所有的管理员
	 * 
	 * @return
	 */
	@Override
	public List<Librarian> getLibrarians() {
		return jdbc.query(SELECT_LIBRARIAN, new LibrarianRowMapper());
	}
	/**
	 * 取得所有的管理员的数量
	 * 
	 * @return
	 */
	@Override
	public int getLibrarianCount() {
		return (int) jdbc.queryForLong("select count(*) from Librarian");
	}
	/**
	 * 依据页码和指定页面大小，返回管理员列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	@Override
	public PaginationSupport<Librarian> findPage(int pageNo, int pageSize) {
		int totalCount = (int) getLibrarianCount();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Librarian>(new ArrayList<Librarian>(0), 0);

		List<Librarian> items = jdbc.query(SELECT_PAGE_LIBRARIAN, new LibrarianRowMapper(), pageSize, startIndex);
		PaginationSupport<Librarian> ps = new PaginationSupport<Librarian>(items, totalCount, pageSize, startIndex);
		return ps;
	}

	@Override
	public Librarian findByPassword(String userName, String password) {
		Librarian librarian = null;
		try {
			librarian = jdbc.queryForObject(SELECT_LIBRARIAN + " where lUserName = ? and lpassword = ? and delete = false",
					new LibrarianRowMapper(), userName, password);
		} catch (Exception e) {
			
		}
		return librarian;
	}
	
	private static class LibrarianRowMapper implements RowMapper<Librarian> {
		public Librarian mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Librarian(rs.getString("lUserName"), rs.getString("lPassword"), rs.getString("lName"),
					rs.getString("lSex"), rs.getString("lEmail"), rs.getString("lTel"), rs.getBoolean("delete"));
		}
	}

	private static final String INSERT_LIBRARIAN = "insert into Librarian (lUserName, lPassword, lName, lSex, lTel, lEmail, delete) values (?, ?, ?, ?, ?, ?, ?)";

	private static final String SELECT_LIBRARIAN = "select lUserName, lPassword, lName, lSex, lTel, lEmail, delete from Librarian";

	private static final String SELECT_PAGE_LIBRARIAN = SELECT_LIBRARIAN + "  limit ? offset  ?";

}
