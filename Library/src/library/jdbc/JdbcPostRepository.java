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

import library.db.PostRepository;
import library.domain.Post;
import library.web.PaginationSupport;
/**
 * 公告数据库接口Jdbc实现
 * @author ozj
 *@version 1.0
 */
@Repository
public class JdbcPostRepository implements PostRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcPostRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public int postCount() {
		return jdbc.queryForInt("select count(*) from post");
	}

	@Override
	public int getMaxNo() {
		return jdbc.queryForInt("select max(postno) from post");
	}

	@Override
	public void addPost(Post post) {
		int no = getMaxNo() + 1;
		jdbc.update(INSERT_POST, no, post.getPosttime(), post.getTitle(), post.getContent(), post.getLibrarianno());

	}

	@Override
	public void delete(int postNo) {
		jdbc.update(DELETE_POST, postNo);

	}

	@Override
	public void updatePost(Post post) {
		jdbc.update(UPDATE_POST,post.getPosttime(),post.getTitle(),post.getContent(),post.getLibrarianno(),post.getPostno());

	}

	@Override
	public Post getPost(int postNo) {
		Post post = null;
		try {
			post = jdbc.queryForObject(SELECT_POST + "where postno = ? ", new PostRowMapper(),postNo);
		}catch(DataAccessException e) {
			
		}
		return post;
	}

	@Override
	public List<Post> getPosts(String sortType, String content) {
		if(sortType.equals("序号优先")) {
			return jdbc.query(FIND_POST + "order by postno asc", new PostRowMapper(),content,content);
		}else if (sortType.equals("发布时间优先")) {
			return jdbc.query(FIND_POST + "order by posttime asc", new PostRowMapper(),content,content);
		}else {
			return jdbc.query(FIND_POST + "order by posttime desc", new PostRowMapper(),content,content);
		}
	}

	@Override
	public List<Post> getAllPosts() {
		return jdbc.query(SELECT_POST, new PostRowMapper());
	}

	@Override
	public PaginationSupport<Post> findPage(int pageNo, int pageSize) {
		int totalCount = postCount();
		int startIndex = PaginationSupport.convertFromPageToStartIndex(pageNo, pageSize);
		if (totalCount < 1)
			return new PaginationSupport<Post>(new ArrayList<Post>(0), 0);

		List<Post> items = jdbc.query(SELECT_PAGE_POSTS, new PostRowMapper(), pageSize, startIndex);
		PaginationSupport<Post> rs = new PaginationSupport<Post>(items, totalCount, pageSize, startIndex);
		return rs;
	}
	
	private static class PostRowMapper implements RowMapper<Post> {
		public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Post(rs.getInt("postno"), rs.getTimestamp("posttime"),rs.getString("title"),rs.getString("content"),rs.getString("librarianno"));
		}
	}


	private static final String SELECT_POST = "select * from post ";
	
	private static final String FIND_POST = "select * from post where title like ? or content like ? ";
	
	private static final String INSERT_POST = "insert into post (postno, posttime, title, content, librarianno) values (?,?,?,?,?)";
	
	private static final String UPDATE_POST = "update post set posttime = ? , title = ? , content = ? , librarianno = ? where postno = ? ";
	
	private static final String DELETE_POST = "delete from post where postno = ? ";
	
	private static final String SELECT_PAGE_POSTS = SELECT_POST + "order by postno desc limit ? offset  ?";
}
