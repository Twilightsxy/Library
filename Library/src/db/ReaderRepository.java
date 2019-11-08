package db;

import java.util.List;

import domain.Reader;
import web.PaginationSupport;

/**
 * 读者数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface ReaderRepository {

	/**
	 * 添加一个读者
	 * 
	 * @param reader
	 */
	void addReader(Reader reader);

	/**
	 * 删除一个读者
	 * 
	 * @param reader
	 */
	void deleteReader(Reader reader);

	/**
	 * 更新读者信息
	 * 
	 * @param reader
	 * @param oldReaderNo
	 */
	void updateReader(Reader reader, String oldReaderNo);

	/**
	 * 更新读者的名字
	 * 
	 * @param readerNo
	 * @param name
	 */
	void updateReaderName(String readerNo, String name);

	/**
	 * 更新读者的邮箱信息
	 * 
	 * @param readerNo
	 * @param emial
	 */
	void updateReaderEmial(String readerNo, String emial);

	/**
	 * 更新读者的密码
	 * 
	 * @param readerNo
	 * @param password
	 */
	void updateReaderPassword(String readerNo, String password);

	/**
	 * 取得所有的读者的数量
	 * 
	 * @return
	 */
	int getReaderCount();

	/**
	 * 搜索用户名相似的读者
	 * 
	 * @param readerNo
	 * @return
	 */
	List<Reader> getReaders(String readerNo);

	/**
	 * 取得读者
	 * 
	 * @param readerNo
	 * @return
	 */
	Reader getReader(String readerNo);

	/**
	 * 依据页码和指定页面大小，返回读者列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	PaginationSupport<Reader> findPage(int pageNo, int pageSize);
}
