package library.domain;

import javax.validation.constraints.NotNull;

/**
 * 书籍地址类
 * 
 * @author lhb
 * @version v1.0
 */
public class Location {
	
	int locationNo;
	@NotNull
	private String locationName;

	public Location() {
		super();	
	}
	
	/**
	 * 构造方法
	 * 
	 * @param locationNo
	 *           地址号
	 * @param locationName
	 *            地址名称
	 */
	public Location(int locationNo, String locationName) {
		super();
		this.locationNo = locationNo;
		this.locationName = locationName;
	}
	/**
	 * 取得地址号
	 * 
	 * @return
	 */
	public int getLocationNo() {
		return locationNo;
	}
	/**
	 * 设置地址号
	 * 
	 * @return
	 */
	public void setLocationNo(int locationNo) {
		this.locationNo = locationNo;
	}
	/**
	 * 取得地址名称
	 * 
	 * @return
	 */
	public String getLocationName() {
		return locationName;
	}
	/**
	 * 设置地址名称
	 * 
	 * @return
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

}

