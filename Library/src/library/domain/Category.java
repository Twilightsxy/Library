package library.domain;

import javax.validation.constraints.NotNull;

/**
 * 书籍分类类
 * 
 * @author lhb
 * @version v1.0
 */
public class Category {
	int categoryNo;
	@NotNull
	private String categoryName;

	public Category() {
		super();	
	}
	
	/**
	 * 构造方法
	 * 
	 * @param categoryNo
	 *           分类号
	 * @param categoryName
	 *            分类名称
	 */
	public Category(int categoryNo, String categoryName) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
	}
	/**
	 * 取得分类号
	 * 
	 * @return
	 */
	public int getCategoryNo() {
		return categoryNo;
	}
	/**
	 * 设置分类号
	 * 
	 * @return
	 */
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	/**
	 * 取得分类名称
	 * 
	 * @return
	 */
	public String getCategoryName() {
		return categoryName;
	}
	/**
	 * 设置分类名称
	 * 
	 * @return
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}
