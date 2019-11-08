package db;

import java.util.List;

import domain.Librarian;
import web.PaginationSupport;

/**
 * 管理员数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface LibrarianRepository {

	/**
	 * 通过用户名查找该管理员用户名是否存在
	 * 
	 * @param userName
	 * @return 存在返回true,不存在返回false
	 */
	boolean isEsxit(String userName);

	/**
	 * 取得所有的管理员的数量
	 * 
	 * @return
	 */
	int getLibrarianCount();

	/**
	 * 添加一个管理员
	 * 
	 * @param librarian
	 */
	void addLibrarian(Librarian librarian);

	/**
	 * 更新管理员信息
	 * 
	 * @param librarian
	 */
	void updateLibrarian(Librarian librarian);

	/**
	 * 删除管理员，把删除标记设为1
	 * 
	 * @param userName
	 */
	void deleteLibrarian(String userName);

	/**
	 * 通过用户名查找所有的管理员
	 * 
	 * @param userName
	 * @return
	 */
	Librarian getLibrarian(String userName);

	/**
	 * 取得所有的管理员
	 * 
	 * @return
	 */
	List<Librarian> getLibrarians();

	/**
	 * 依据页码和指定页面大小，返回管理员列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	PaginationSupport<Librarian> findPage(int pageNo, int pageSize);

}
