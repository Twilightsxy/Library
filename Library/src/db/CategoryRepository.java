package db;

import java.util.List;

import domain.Category;
import web.PaginationSupport;

/**
 * 类别数据库接口
 * 
 * @author ozj 
 * @version 1.0
 */
public interface CategoryRepository {

	/**
	 * 取得所有的分类的数量
	 * 
	 * @return count
	 */
	int categoryCount();

	/**
	 * 添加分类
	 * 
	 * @param category
	 */
	void addCategory(Category category);

	/**
	 * 通过分类的No删除分类
	 * 
	 * @param categoryNo
	 */
	void deleteCategory(int categoryNo);

	/**
	 * 更新分类信息
	 * 
	 * @param category
	 */
	void updateCategory(Category category);

	/**
	 * 通过分类的no取得分类
	 * 
	 * @param categoryNo
	 * @return
	 */
	Category getCategory(int categoryNo);

	/**
	 * 取得所有的category
	 * 
	 * @return 分类的列表
	 */
	List<Category> getCategorys();

	/**
	 * 依据页码和指定页面大小，返回分类列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	PaginationSupport<Category> findPage(int pageNo, int pageSize);

}
