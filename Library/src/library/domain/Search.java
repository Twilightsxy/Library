package library.domain;

import java.sql.Timestamp;

/**
 * 搜索类
 * 
 * @author zbl
 * @version 1.0
 */
public class Search {

	private String category;
	private String searchLimit;
	private String searchContent;
	
	
	public Search() {
		super();
	}
	/**
	 * 构造方法
	 * 
	 * @param category     分类
	 * @param searchLimit   限制
	 * @param searchContent 搜索内容
	 */
	public Search( String category, String searchLimit,String searchContent) {
		this.category = category;
		this.searchLimit = searchLimit;
		this.searchContent = searchContent;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSearchLimit() {
		return searchLimit;
	}

	public void setSearchLimit(String searchLimit) {
		this.searchLimit = searchLimit;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	
}
