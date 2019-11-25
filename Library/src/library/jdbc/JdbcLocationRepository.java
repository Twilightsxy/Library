package library.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.db.LocationRepository;
import library.domain.Location;
import library.web.PaginationSupport;

/**
 * 书籍地址资源库接口的jdbc实现类
 * 
 * @author lhb
 * @version v1.0
 */

@Repository
public class JdbcLocationRepository implements LocationRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcLocationRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	/**
	 * 添加一个地址
	 * 
	 * @param location
	 */
	@Override
	public void addLocation(Location location) {
		jdbc.update(INSERT_LOCATION, location.getLocationNo(), location.getLocationName());

	}

	/**
	 * 更新地址信息
	 * 
	 * @param location
	 */
	@Override
	public void updateLocation(Location location,String locationName) {
		jdbc.update("update Location set locationno=" + location.getLocationNo() + " , locationname ='"
				+ location.getLocationName()+"' where locationname='"+locationName+"'");
	}

	/**
	 * 返回所有的地址的数量
	 * 
	 * @return
	 */
	@Override
	public int getCount() {
		return (int) jdbc.queryForLong("select count(locationno) from Location");
	}

	/**
	 * 通过locationNo删除location
	 * 
	 * @param locationNo
	 */
	@Override
	public void deleteLocation(int locationNo) {
		jdbc.update("delete from location where locationno=" + locationNo);
	}

	/**
	 * 通过locationno查找location
	 * 
	 * @param locationNo
	 * @return location
	 */
	@Override
	public Location getLocation(int locationNo) {
		Location location = null;
		try {
			location = jdbc.queryForObject(SELECT_LOCATION + " where locationno=?", new LocationRowMapper(),
					locationNo);
		} catch (DataAccessException e) {
		}
		return location;
	}

	/**
	 * 取得所有的location列表
	 * 
	 * @return
	 */
	@Override
	public List<Location> getAllLocations() {
		return jdbc.query(SELECT_LOCATION + " order by locationno", new LocationRowMapper());
	}

	/**
	 * 依据页码和指定页面大小，返回地址列表
	 * 
	 * @param pageNo   起始位置
	 * @param pageSize 每页数量
	 * @return 分页对象
	 */
	@Override
	public PaginationSupport<Location> findPage(int pageNo, int pageSize) {
		int totalCount = (int) getCount();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Location>(new ArrayList<Location>(0), 0);

		List<Location> items = jdbc.query(SELECT_PAGE_LOCATION, new LocationRowMapper(), pageSize, startIndex);
		PaginationSupport<Location> ps = new PaginationSupport<Location>(items, totalCount, pageSize, startIndex);
		return ps;
	}

	private static class LocationRowMapper implements RowMapper<Location> {
		public Location mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Location(rs.getInt("locationno"), rs.getString("locationname"));
		}
	}

	private static final String INSERT_LOCATION = "insert into Location (locationno, locationname) values (?, ?)";

	private static final String SELECT_LOCATION = "select locationno,locationname from Location";

	private static final String SELECT_PAGE_LOCATION = SELECT_LOCATION + "  limit ? offset  ?";
}
