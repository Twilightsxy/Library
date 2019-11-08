package db;

import java.util.Map;

/**
 * 取得满足发邮件条件数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface EmialRepository {

	/**
	 * 取得满足发邮件的map
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	Map emialAndBookNo();

}
