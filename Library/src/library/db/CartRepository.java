package library.db;

import java.util.List;

import library.domain.Cart;

/**
 * 购物车数据库接口
 * 
 * @author ozj
 * @version 1.0
 */
public interface CartRepository {

	/**
	 * 取得读者购物车信息
	 * 
	 * @param readerNo
	 * @return
	 */
	public List<Integer> getCarts(String readerNo);

	/**
	 * 判断是否在购物车里 在返回true 不在返回false
	 * 
	 * @param readerNo
	 * @param bookNo
	 * @return
	 */
	public boolean isInCart(String readerNo, int bookNo);

	/**
	 * 添加购物车
	 * 
	 * @param cart
	 */
	public void addCart(Cart cart);

	/**
	 * 删除购物车
	 * 
	 * @param cart
	 */
	public void deleteCart(Cart cart);
}
