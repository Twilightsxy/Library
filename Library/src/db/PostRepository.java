package db;

import java.util.List;

import domain.Post;
import web.PaginationSupport;


/**
 * 公告数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface PostRepository {

	/**
	 * 取得所有的公告数目
	 * 
	 * @return count
	 */
	int postCount();

	/**
	 * 取得post表中最大的no
	 * 
	 * @return postno
	 */
	int getMaxNo();

	/**
	 * 添加post
	 */
	void addPost();

	/**
	 * 通过 postNo 删除post
	 * 
	 * @param postNo
	 */
	void delete(int postNo);
	
	/**
	 * 更新post
	 * 
	 * @param post 新的公告信息
	 */
	void updatePost(Post post);

	/**
	 * 通过postNo 取得post
	 * 
	 * @param postNo
	 * @return post
	 */
	Post getPost(int postNo);

	/**
	 * 通过选择的排序类型和输入的内容搜索公告
	 * 
	 * @param sort    排序的依据
	 * @param content 搜索题目的内容
	 * @return 搜索的公告列表
	 */
	List<Post> getPosts(String sortType, String content);

	/**
	 * 取得所有的post用于初始化页面
	 * 
	 * @return 公告列表
	 */
	List<Post> getAllPosts();

	/**
	 * 依据页码和指定页面大小，返回公告列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	PaginationSupport<Post> findPage(int pageNo, int pageSize);
}
