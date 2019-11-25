package library.db;

import java.util.List;

import library.domain.Reserve;

/**
 * 预定数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface ReserveRepository {

	/**
	 * 添加预约记录
	 * 
	 * @param reserve
	 */
	void addReserve(Reserve reserve);

	/**
	 * 删除预约记录
	 * 
	 * @param reserve
	 */
	void deleteReserve(int bookNo);

	/**
	 * 判断书是否已经被预定
	 * 
	 * @param bookNo
	 * @return 已预定为true,未预定为false
	 */
	boolean isReserve(int bookNo);

	/**
	 * 取得所有的被预定的书籍列表
	 * 
	 * @return
	 */
	List<Reserve> getReserves();

	/**
	 * 检测数据库里的失效预定记录并删除
	 */
	void execture();

	Long checkReserveTime(Reserve reserve);
}
