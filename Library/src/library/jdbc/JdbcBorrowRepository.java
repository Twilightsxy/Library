package library.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.db.BorrowRepository;
import library.domain.Borrow;
import library.web.PaginationSupport;

/**
 * 借阅记录资源库接口的jdbc实现类
 * 
 * @author lhb
 * @version v1.0
 */

@Repository
public class JdbcBorrowRepository implements BorrowRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcBorrowRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}


	/**
	 * 添加借书记录
	 * 
	 * @param borrow
	 */
	@Override
	public void addBorrow(Borrow borrow){
		jdbc.update(INSERT_BORROW,borrow.getBookNo(),borrow.getTitle(),borrow.getAuthor(),borrow.getReaderNo(),
				borrow.getBorrowTime(),borrow.isReturned()
				,borrow.getShouldReturnTime(),borrow.getShouldReturnTime(),borrow.getFine());

	}

	
	/**
	 * 还书(设置isReturned = true)
	 * 
	 * @param bookNo     需要还的书的no
	 * @param isreturned Boolean值
	 * @param returnTime 还书时间
	 */
	@Override
	public void updateBorrow(int bookNo, Boolean isreturned, Timestamp returnTime) {
		jdbc.update("update borrow set isreturned=?,returntime=? where bookNo= ?",isreturned,returnTime,bookNo);
	}
	
	
	/**
	 * 查找读者正借阅的所有书的数量
	 * 
	 * @param readerNo
	 * @return 正在借阅的书籍数量
	 */
	@Override
	public int getLenCount(String readerNo) {
		String const_s = "\'" + readerNo + "\'";
		return jdbc.queryForInt("select count(*) from borrow where isreturned is false and readerno="+const_s);
	}
	
	
	/**
	 * 取得所有的正在借阅的书籍的列表
	 * 
	 * @param readerNo
	 * @return 正在借阅的书籍的列表
	 */
	@Override
	public List<Borrow> getlenBorrows(String readerNo){
		return jdbc.query("select bookno, title, author, readerno, borrowtime, isreturned, shouldreturntime,"
				+ "returntime,fine from Borrow where isreturned is false and readerno="+"\'" + readerNo + "\'", new BorrowRowMapper());
	}
	
	
	/**
	 * 取得所有的已还书籍的数量
	 * 
	 * @param readerNo
	 * @return 已还书籍数量
	 */
	@Override
	public int getFinCount(String readerNo) {
		String const_s = "\'" + readerNo + "\'";
		return jdbc.queryForInt("select count(*) from borrow where isreturned is true and readerno="+ const_s);
	}
	
	
	/**
	 * 取得所有的已还的借阅记录列表
	 * 
	 * @param readerNo
	 * @return 已还的书籍的借阅列表
	 */
	@Override
	public List<Borrow> getFinBorrows(String readerNo){
		return jdbc.query("select bookno, title, author, readerno, borrowtime, isreturned, shouldreturntime,"
				+ "returntime,fine from Borrow where isreturned is true and readerno="+"\'" + readerNo + "\'", new BorrowRowMapper());
	}
	
	
	/**
	 * 取得所有的逾期未还的借阅记录(每一条借阅记录中包括罚金)
	 * 
	 * @param readerNo
	 * @param dayFine
	 * @return 读者所有的逾期借阅记录列表
	 */
	public List<Borrow> getOverTimeBorrows(String readerNo, int dayFine){
		List<Borrow> overTimeBooks = new ArrayList<Borrow>();
		Timestamp now = new Timestamp(System.currentTimeMillis());
		long t1 = now.getTime();
		List<Borrow> allborrow=	jdbc.query("select * from borrow  where borrow.isreturned=false and borrow.readerno = '"
				+ readerNo + "' order by borrow.bookNo ", new BorrowRowMapper());
		
		for(int i=0;i<allborrow.size();i++) {
			
			
		}
		for (int i = 0; i < allborrow.size(); i++) {
			Borrow borrow=(Borrow) allborrow.get(i);
			if(borrow.getShouldReturnTime().getTime()<t1) {
				int fine = (int) ((t1 - borrow.getShouldReturnTime().getTime()) / (1000 * 60 * 60 * 24)) * dayFine;

				int ingBookNo =borrow.getBookNo();
				String ingTitle = borrow.getTitle();
				String ingAuthor = borrow.getAuthor();
				String ingReaderNo = readerNo;
				Timestamp ingBorrowTime = borrow.getBorrowTime();
				boolean isReturned = false;
				Timestamp ingShouldReturnTime = borrow.getShouldReturnTime();
				Timestamp ingReturnTime = borrow.getReturnTime();
				Borrow overTimeBook = new Borrow(ingBookNo, ingTitle, ingAuthor, ingReaderNo, ingBorrowTime,
						isReturned, ingShouldReturnTime, ingReturnTime, fine);
				overTimeBooks.add(overTimeBook);

			}
		}
		return overTimeBooks;
	}
	
	
	/**
	 * 取得所有的借阅记录
	 * 
	 * @return 借阅记录列表
	 */
	@Override
	public List<Borrow> getAllBorrows(String readerNo) {
		return jdbc.query("select bookno, title, author, readerno, borrowtime, isreturned, shouldreturntime,"
				+ "returntime,fine from Borrow where readerno="+"\'" + readerNo + "\'", new BorrowRowMapper());
	}
	
	/**
	 * 依据页码和指定页面大小，返回借阅记录列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	@Override
	public PaginationSupport<Borrow> findPage(int pageNo, int pageSize) {
		int totalCount = (int) jdbc.queryForLong("select count(*) from Borrow");
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Borrow>(new ArrayList<Borrow>(0), 0);

		List<Borrow> items = jdbc.query(SELECT_PAGE_BORROW, new BorrowRowMapper(), pageSize, startIndex);
		PaginationSupport<Borrow> ps = new PaginationSupport<Borrow>(items, totalCount, pageSize, startIndex);
		return ps;
	}

	private static class BorrowRowMapper implements RowMapper<Borrow> {
		public Borrow mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Borrow(rs.getInt("bookno"), rs.getString("title"),rs.getString("author"),rs.getString("readerno"), rs.getTimestamp("borrowTime"),
					rs.getBoolean("isreturned"), rs.getTimestamp("shouldreturntime"),rs.getTimestamp("returntime"),rs.getInt("fine"));
		}
	}

	private static final String INSERT_BORROW = "insert into Borrow (bookno, title, author, readerno, borrowtime, isreturned, shouldreturntime,returntime,fine) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private static final String SELECT_BORROW = "select bookno, title, author, readerno, borrowtime, isreturned, shouldreturntime,returntime,fine from Borrow";

	private static final String SELECT_PAGE_BORROW = SELECT_BORROW + "  limit ? offset  ?";

}
