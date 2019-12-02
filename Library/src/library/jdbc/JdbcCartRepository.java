package library.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.db.CartRepository;
import library.domain.Cart;

/**
 * 购物车数据库接口实现
 * 
 * @author ozj
 * @version 1.0
 */
@Repository
public class JdbcCartRepository implements CartRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcCartRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public List<Integer> getCarts(String readerNo) {
		List<Cart> carts;
		carts = jdbc.query(SELECT_CARTS, new CartRowMapper(), readerNo);
		List<Integer> books = new ArrayList<Integer>();
		for (Cart cart : carts) {
			books.add(cart.getBookNo());
		}
		return books;
	}

	@Override
	public boolean isInCart(String readerNo, int bookNo) {
		List<Cart> carts;
		carts = jdbc.query(SELECT_CARTS, new CartRowMapper(), readerNo);
		for (Cart cart : carts) {
			if (cart.getBookNo() == bookNo) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void addCart(Cart cart) {
		String a = cart.getReaderNo();
		int b = cart.getBookNo();
		System.out.println(a + "," + b);
		jdbc.update("Insert into cart(bookNo,readerNo) values("+ cart.getBookNo()+","+ cart.getReaderNo()+")");

	}

	@Override
	public void deleteCart(Cart cart) {
		jdbc.update(DELETE_CART, cart.getBookNo(), cart.getReaderNo());

	}

	private static class CartRowMapper implements RowMapper<Cart> {
		public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Cart(rs.getInt("bookNo"), rs.getString("readerNo"));
		}
	}

	private final static String SELECT_CARTS = "Select bookNo,readerNo from cart where readerNo = ? order by bookno asc;";

	private final static String INSERT_CART = "Insert into cart(bookNo,readerNo) values(?,?)";

	private final static String DELETE_CART = "delete from cart where bookNo = ? and readerNo = ?";

}
