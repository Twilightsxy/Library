package library.db;

import java.util.List;

import library.domain.Book;
import library.web.PaginationSupport;

/**
 * 书籍数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface BookRepository {

	/**
	 * 取得所有的书籍的数量
	 * 
	 * @return
	 */
	int getCount();

	/**
	 * 取得所有的未借出的书籍的数量
	 * 
	 * @return
	 */
	int getCountOfUnlend();

	/**
	 * 取得bookNo中的最大值
	 * 
	 * @return
	 */
	int getMaxNo();

	/**
	 * 添加书籍
	 * 
	 * @param book
	 */
	void addBook(Book book);

	/**
	 * 更新书籍信息
	 * 
	 * @param book
	 */
	void updateBook(Book book);

	/**
	 * 修改同一类别的所有的书的类别
	 * 
	 * @param newCatergory 新类别名字
	 * @param oldCategory  旧类别名字
	 */
	void updateBookCategory(String newCategory, String oldCategory);

	/**
	 * 修改同一地址的所有的书籍的地址
	 * 
	 * @param newLocation 新地址
	 * @param oldLocation 旧地址
	 */
	void updateBookLocation(String newLocation, String oldLocation);

	/**
	 * 通过书籍的No删除书籍
	 * 
	 * @param bookNo
	 */
	void deleteBook(int bookNo);

	/**
	 * 通过书籍的No取得书籍
	 * 
	 * @param bookNo
	 * @return 书
	 */
	Book getBook(int bookNo);

	/**
	 * 通过ISBN取得同一ISBN的所有的书
	 * 
	 * @param ISBN
	 * @return 书籍列表
	 */
	List<Book> getBooksbyISBN(String ISBN);

	/**
	 * 使用sortTyoe排序类型和搜索内容content搜索借阅出书籍并返回书籍列表
	 * 
	 * @param sortType 搜索类型
	 * @param content  搜索内容
	 * @return 书籍列表
	 */
	List<Book> getLendBooks(String sortType, String content);

	/**
	 * 使用sortTyoe排序类型和搜索内容content搜索未借阅出书籍并返回书籍列表
	 * 
	 * @param sortType 搜索类型
	 * @param content  搜索内容
	 * @return 书籍列表
	 */
	List<Book> getUnLendBooks(String sortType, String content);

	/**
	 * 使用sortTyoe排序类型和搜索内容content搜索所有的书籍并返回书籍列表
	 * 
	 * @param sortType 搜索类型
	 * @param content  搜索内容
	 * @return 书籍列表
	 */
	List<Book> getAllBooks(String sortType, String content);

	/**
	 * 读者搜索书
	 * 
	 * @param type1   category
	 * @param name    通过title还是author搜索
	 * @param content 搜索框输入内容
	 * @return
	 */
	List<Book> findBooks(String type1, String name, String content);

	/**
	 * 依据页码和指定页面大小，返回所有的书籍记录列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	PaginationSupport<Book> findPage(int pageNo, int pageSize);

	/**
	 * 依据页码和指定页面大小，返回未借出的书籍记录列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	PaginationSupport<Book> findUnlendPage(int pageNo, int pageSize);

}
