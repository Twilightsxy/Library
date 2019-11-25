package library.db;

import java.sql.Timestamp;
import java.util.List;

import library.domain.Borrow;
import library.web.PaginationSupport;

/**
 * 借书记录数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface BorrowRepository {

	/**
	 * 添加借书记录
	 * 
	 * @param borrow
	 */
	void addBorrow(Borrow borrow);

	/**
	 * 还书(设置isReturned = true)
	 * 
	 * @param bookNo     需要还的书的no
	 * @param isreturned Boolean值
	 * @param returnTime 还书时间
	 */
	void updateBorrow(int bookNo, Boolean isreturned, Timestamp returnTime);

	/**
	 * 查找读者正借阅的所有书的数量
	 * 
	 * @param readerNo
	 * @return 正在借阅的书籍数量
	 */
	int getLenCount(String readerNo);

	/**
	 * 取得读者所有的正在借阅的书籍的列表
	 * 
	 * @param readerNo
	 * @return 正在借阅的书籍的列表
	 */
	List<Borrow> getlenBorrows(String readerNo);

	/**
	 * 取得所有的已还书籍的数量
	 * 
	 * @param readerNo
	 * @return 已还书籍数量
	 */
	int getFinCount(String readerNo);

	/**
	 * 取得所有的已还的借阅记录列表
	 * 
	 * @param readerNo
	 * @return 已还的书籍的借阅列表
	 */
	List<Borrow> getFinBorrows(String readerNo);

	/**
	 * 取得所有的逾期未还的借阅记录(每一条借阅记录中包括罚金)
	 * 
	 * @param readerNo
	 * @param dayFine
	 * @return 读者所有的逾期借阅记录列表
	 */
	List<Borrow> getOverTimeBorrows(String readerNo, int dayFine);

	/**
	 * 取得读者所有的借阅记录
	 * 
	 * @param readerNo
	 * @return 借阅记录列表
	 */
	List<Borrow> getAllBorrows(String readerNo);

	/**
	 * 依据页码和指定页面大小，返回借阅记录列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	PaginationSupport<Borrow> findPage(int pageNo, int pageSize);
}
