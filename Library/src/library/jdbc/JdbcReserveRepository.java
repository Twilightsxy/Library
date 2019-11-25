package library.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import library.db.ReserveRepository;
import library.domain.Reserve;

/**
 * 预定数据库接口Jdbc实现
 * @author ozj
 *@version 1.0
 */
@Repository
public class JdbcReserveRepository implements ReserveRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcReserveRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public void addReserve(Reserve reserve) {
		jdbc.update(INSERT_RESERVE, reserve.getBookNo(), reserve.getReaderNo(), reserve.getReserveTime());

	}

	@Override
	public void deleteReserve(int bookNo) {
		jdbc.update(DELETE_RESERVE, bookNo);

	}

	@Override
	public boolean isReserve(int bookNo) {
		boolean isreserve = false;
		int total = jdbc.queryForInt("select count(bookno) from reserve where bookno = " + bookNo); 
		if(total >=1) {
			isreserve = true;
		}
		return isreserve;
	}

	@Override
	public List<Reserve> getReserves() {
		return jdbc.query(SELECT_RESERVE, new ReserveRowMapper());
	}

	private static class ReserveRowMapper implements RowMapper<Reserve> {
		public Reserve mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Reserve(rs.getInt("bookno"),rs.getString("readerno"),rs.getTimestamp("reservetime"));
		}
	}
	
	@Override
	public void execture() {
		/**
		 * 用于检测数据库失效订单并删除
		 * @DisallowConcurentExecution 这个注释标明上个任务未执行完不会执行下一个任务
		 */
		long EFFTIVE_TIME = 7200000;
		
		Queue<Reserve> queue = new LinkedList<Reserve>();
		
		ArrayList<Reserve> ingList = new ArrayList<Reserve>(this.getReserves());

		if(!ingList.isEmpty()) {
			for(Reserve o : ingList) {
				queue.offer(o);
			}
		}
		
		//获取队列的头元素，检测头订单是否失效
	    Reserve element = queue.peek();
	    while(element != null) {
	    	//获取时间差
	    	Long diff = this.checkReserveTime(element);
	    	if(diff != null && diff>= EFFTIVE_TIME) {
	    		int bookNo = element.getBookNo();
	    		this.deleteReserve(bookNo);
	    		//弹出队列
	    		queue.poll();
	    		//取下一个元素
	    		element = queue.peek();
	    	} else if (diff < EFFTIVE_TIME) {
	    		try {
					//等待检测
	    			Thread.sleep(EFFTIVE_TIME - diff);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
	    	}
	    	
	    }
	}

	@Override
	public Long checkReserveTime(Reserve reserve) {
		/**
		 * 获取下单时间和现在的时间差
		 */
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long diff = null;
		if(reserve != null) {
			Date createTime = reserve.getReserveTime();
			try {
				diff = dateFormat.parse(dateFormat.format(date)).getTime() - dateFormat.parse(dateFormat.format(createTime)).getTime();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//返回值为毫秒
		return diff;
	}
	
	private static final String SELECT_RESERVE = "select * from reserve order by reservetime";

	private static final String INSERT_RESERVE =  "insert into reserve (bookno,readerno,reservetime) values (?,?,?)";
	
	private static final String DELETE_RESERVE = "delete from reserve where bookno = ?";
}
