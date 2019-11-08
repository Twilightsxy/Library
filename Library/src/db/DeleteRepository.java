package db;

import java.util.List;

import domain.Delete;
import web.PaginationSupport;

/**
 * 删除记录数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface DeleteRepository {

	/**
	 * 添加删除记录
	 * 
	 * @param delete
	 */
	void addDelete(Delete delete);

	/**
	 * 取得所有的删除记录
	 * 
	 * @return 删除记录列表
	 */
	List<Delete> getDeletes();

	/**
	 * 依据页码和指定页面大小，返回删除记录列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	PaginationSupport<Delete> findPage(int pageNo, int pageSize);

}
