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

import library.db.BookRepository;
import library.domain.Book;
import library.domain.Category;
import library.web.PaginationSupport;

/**
 * 书籍管理数据库接口Jdbc实现
 *
 * @author ozj
 * @version 1.0
 *
 */
@Repository
public class JdbcBookRepository implements BookRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcBookRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public int getCount() {
		return jdbc.queryForInt("select count(*) from book");
	}

	@Override
	public int getCountOfUnlend() {
		return jdbc.queryForInt("select count(*) from book where status = 0 ");
	}

	@Override
	public int getMaxNo() {
		return jdbc.queryForInt("select max(bookno) from book");
	}

	@Override
	public void addBook(Book book) {
		int no = getMaxNo() + 1;
		jdbc.update(INSERT_BOOK, no, book.getTitle(), book.getAuthor(), book.getPrice(), book.getTime(),
				book.getPublish(), book.getBrief(), book.getISBN(), book.getCategory(), book.getLocation(),
				book.getStatus());

	}

	@Override
	public void updateBook(Book book) {
		jdbc.update(UPDATE_BOOK, book.getTitle(), book.getAuthor(), book.getPrice(), book.getTime(), book.getPublish(),
				book.getBrief(), book.getISBN(), book.getCategory(), book.getLocation(), book.getStatus(),
				book.getBookNo());

	}

	@Override
	public void updateBookCategory(String newCategory, String oldCategory) {
		//jdbc.update("update book set category = ? where category = ?", newCategory, oldCategory);
		//因为我把数据库里book的category改成了categoryNo,所以直接改category表里categoryNo对应的categoryName,这个方法无用
		//要更新请调用updateCategory(Category category)更改category表
	}

	@Override
	public void updateBookLocation(String newLocation, String oldLocation) {
		jdbc.update("update book set location = ? where location = ?", newLocation, oldLocation);

	}

	@Override
	public void deleteBook(int bookNo) {
		jdbc.update(DELETE_BOOK, bookNo);

	}

	@Override
	public Book getBook(int bookNo) {
		Book book = null;
		try {
			book = jdbc.queryForObject(SELECT_BOOK + "where bookno = ?", new BookRowMapper(), bookNo);
		} catch (DataAccessException e) {

		}
		return book;
	}

	@Override
	public List<Book> getBooksbyISBN(String ISBN) {
		List<Book> books = null;
		try {
			books = jdbc.query(SELECT_BOOK + "where isbn = ?", new BookRowMapper(), ISBN);
		} catch (DataAccessException e) {

		}
		return books;
	}

	@Override
	public List<Book> getLendBooks(String sortType, String content) {
		List<Book> books = new ArrayList<Book>();
		String bookContent = "\'%" + content + "%\'";
		String sql = null;
		if (sortType.equals("title")) {
			sql = "select * from book where bookno  in (SELECT bookno from borrow where isreturned is NOT TRUE) and title like "
					+ bookContent + " order by bookNo asc";

		} else if (sortType.equals("author")) {
			sql = "select * from book where bookno  in (SELECT bookno from borrow where isreturned is NOT TRUE) and author like "
					+ bookContent + " order by bookNo asc";

		} else if (sortType.equals("category")) {
			sql = "select * from book where bookno  in (SELECT bookno from borrow where isreturned is NOT TRUE) and category like "
					+ bookContent + " order by bookNo asc";

		} else if (sortType.equals("bookNo")) {
			sql = "select * from book where bookno  in (SELECT bookno from borrow where isreturned is NOT TRUE) and bookNo = "
					+ bookContent;

		}
		try {
			books = jdbc.query(sql, new BookRowMapper());
		} catch (DataAccessException e) {

		}
		return books;
	}

	@Override
	public List<Book> getUnLendBooks(String sortType, String content) {
		List<Book> books = new ArrayList<Book>();
		String bookContent = "\'%" + content + "%\'";
		String sql = null;
		if (sortType.equals("title")) {
			sql = "select * from book left join(select bookno as bool FROM reserve)as state on book.bookno = state.bool where book.bookno  not in (SELECT bookno from borrow where isreturned is NOT TRUE) and title like "
					+ bookContent + "order by book.bookNo asc";
		} else if (sortType.equals("author")) {
			sql = "select * from book where bookno not in (SELECT bookno from borrow where isreturned is not TRUE) and author like "
					+ bookContent + " order by bookNo asc";
		} else if (sortType.equals("category")) {
			sql = "select * from book where bookno not in (SELECT bookno from borrow where isreturned is not TRUE) and category like "
					+ bookContent + " order by bookNo asc";
		} else if (sortType.equals("ISBN")) {
			sql = "select * from book where bookno not in (SELECT bookno from borrow where isreturned is not TRUE) and ISBN like "
					+ bookContent + " order by bookNo asc";
		}
		try {
			books = jdbc.query(sql, new BookRowMapper());
		} catch (DataAccessException e) {

		}
		return books;
	}

	@Override
	public List<Book> getAllBooks(String sortType, String content) {
		List<Book> books = new ArrayList<Book>();
		String bookContent = "\'%" + content + "%\'";
		String sql = new String();
		if (sortType.equals("title")) {
			sql = "select * from book  where title like" + bookContent + " order by bookNo asc";

		} else if (sortType.equals("author")) {
			sql = "select * from book  where author like" + bookContent + " order by bookNo asc";

		} else if (sortType.equals("category")) {
			sql = "select * from book  where category like" + bookContent + " order by bookNo asc";

		} else if (sortType.equals("ISBN")) {
			sql = "select * from book  where isbn like" + bookContent + " order by bookNo asc";

		}
		try {
			books = jdbc.query(sql, new BookRowMapper());
		} catch (DataAccessException e) {

		}
		return books;
	}

	@Override
	public List<Book> findBooks(String type1, String name, String content) {
		List<Book> books = new ArrayList<Book>();

		String bookCategory = "\'%" + type1 + "%\'";
		String bookContent = "\'%" + content + "%\'";
		String sql = null;
		if (type1.equals("") && name.equals("title")) {
			sql = "select * from book where title like " + bookContent + " order by bookNo asc";
		} else if (type1.equals("") && name.equals("author")) {
			sql = "select * from book where author like " + bookContent + " order by bookNo asc";
		} else if (!type1.equals("")) {
			if (name.equals("title")) {
				sql = "select * from book where category like " + bookCategory + " and title like " + bookContent
						+ " order by bookNo asc";
			} else if (name.equals("author")) {
				sql = "select * from book where category like " + bookCategory + " and author like " + bookContent
						+ " order by bookNo asc";
			}
		}
		try {
			books = jdbc.query(sql, new BookRowMapper());
		} catch (DataAccessException e) {

		}
		for (int i = 0; i < books.size(); i++) {
			for (int j = i + 1; j < books.size(); j++) {
				if (books.get(i).getISBN().equals(books.get(j).getISBN())) {
					books.remove(j);
					j--;
				}
			}
		}
		return books;
	}

	@Override
	public PaginationSupport<Book> findbytitle(int pageNo, int pageSize , String category ,String content) {
		String bookContent = "\'%" + content + "%\'";
		category = "\'%" + category + "%\'";
		int totalCount = getCountOfUnlend();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Book>(new ArrayList<Book>(0), 0);

		List<Book> items = jdbc.query("select * from book where category like " + category + " and title like " + bookContent
				+ " order by bookNo asc limit ? offset  ?", new BookRowMapper(), pageSize, startIndex);
		PaginationSupport<Book> rs = new PaginationSupport<Book>(items, totalCount, pageSize, startIndex);
		return rs;
	}
	
	@Override
	public PaginationSupport<Book> findbyauthor(int pageNo, int pageSize , String category ,String content) {
		String bookContent = "\'%" + content + "%\'";
		category = "\'%" + category + "%\'";
		int totalCount = getCountOfUnlend();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Book>(new ArrayList<Book>(0), 0);

		List<Book> items = jdbc.query("select * from book where category like " + category + " and author like " + bookContent
				+ " order by bookNo asc limit ? offset  ?", new BookRowMapper(), pageSize, startIndex);
		PaginationSupport<Book> rs = new PaginationSupport<Book>(items, totalCount, pageSize, startIndex);
		return rs;
	}
	
	@Override
	public PaginationSupport<Book> findPage(int pageNo, int pageSize) {
		int totalCount = getCount();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Book>(new ArrayList<Book>(0), 0);

		List<Book> items = jdbc.query(SELECT_PAGE_BOOKS, new BookRowMapper(), pageSize, startIndex);
		PaginationSupport<Book> rs = new PaginationSupport<Book>(items, totalCount, pageSize, startIndex);
		return rs;
	}
	
	@Override
	public PaginationSupport<Book> findPageLend(int pageNo, int pageSize) {
		int totalCount = getCount();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Book>(new ArrayList<Book>(0), 0);

		List<Book> items = jdbc.query(SELECT_PAGE_BOOKS_LEND, new BookRowMapper(), pageSize, startIndex);
		PaginationSupport<Book> rs = new PaginationSupport<Book>(items, totalCount, pageSize, startIndex);
		return rs;
	}

	@Override
	public PaginationSupport<Book> findUnlendPage(int pageNo, int pageSize) {
		int totalCount = getCountOfUnlend();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Book>(new ArrayList<Book>(0), 0);

		List<Book> items = jdbc.query(SELECT_PAGE_UNLENDBOOKS, new BookRowMapper(), pageSize, startIndex);
		PaginationSupport<Book> rs = new PaginationSupport<Book>(items, totalCount, pageSize, startIndex);
		return rs;
	}

	private static class BookRowMapper implements RowMapper<Book> {
		public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Book(rs.getInt("bookno"), rs.getString("title"), rs.getString("author"), rs.getString("price"),
					rs.getString("time"), rs.getString("publish"), rs.getString("brief"), rs.getString("isbn"),
					rs.getString("category"), rs.getString("location"), rs.getInt("status"));
		}
	}

	private static final String SELECT_BOOK = "select * from book ";

	private static final String DELETE_BOOK = "delete from book where bookno = ? ";

	private static final String UPDATE_BOOK = "update book set title = ?,author = ?,price = ?,time = ?,publish = ?,brief = ?,isbn = ?,category = ?,location = ?,status = ? where bookno = ?";

	private static final String INSERT_BOOK = "insert into book(bookno,title,author,price,time,publish,brief,isbn,category,location,status) values(?,?,?,?,?,?,?,?,?,?,?)";

	private static final String SELECT_PAGE_BOOKS = SELECT_BOOK + "order by bookno asc limit ? offset  ?";
	
	private static final String SELECT_PAGE_BOOKS_LEND = SELECT_BOOK + " where status = '2' order by bookno asc limit ? offset  ?";

	private static final String SELECT_PAGE_UNLENDBOOKS = SELECT_BOOK
			+ " where status = 0 order by bookno desc limit ? offset  ?";

}
