package library.db;

import java.util.List;

import library.domain.Location;
import library.web.PaginationSupport;

/**
 * 地址数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface LocationRepository {

	/**
	 * 返回所有的地址的数量
	 * 
	 * @return
	 */
	int getCount();

	/**
	 * 添加地址
	 * 
	 * @param location
	 */
	void addLocation(Location location);

	/**
	 * 更新location信息
	 * 
	 * @param location 新的location
	 * @param locationName 被更新的locationName
	 */
	void updateLocation(Location location,String locationName);

	/**
	 * 通过locationNo删除location
	 * 
	 * @param locationNo
	 */
	void deleteLocation(int locationNo);

	/**
	 * 通过locationNo取得location信息
	 * 
	 * @param locationNo
	 * @return
	 */
	Location getLocation(int locationNo);

	/**
	 * 依据页码和指定页面大小，返回地址列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	PaginationSupport<Location> findPage(int pageNo, int pageSize);

	/**
	 * 取得所有的location列表
	 * 
	 * @return
	 */
	List<Location> getAllLocations();
}
