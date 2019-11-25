package library.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import library.db.DouBanAPI;
import library.domain.Book;
import library.web.JsonUtils;

/**
 * DouBanAPI资源库接口的实现类
 * 
 * @author lhb
 * @version v1.0
 */

@Repository
public class JdbcDouBanAPI implements DouBanAPI {

	private JdbcTemplate jdbc;

	@Autowired
	public JdbcDouBanAPI(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	/**
	 * 根据ISBN查书
	 * @param isbn 待查的ISBN
	 * 
	 * @return Book
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public Book searchByISBN(String isbn) throws ClientProtocolException, IOException {
		Book book = new Book();
		String url = "https://douban.uieee.com/v2/book/isbn/" + isbn;
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		HttpEntity entity = response.getEntity();
		String json = EntityUtils.toString(entity);
        //System.out.println(json);
		// 用来根据ISBN加书
		JsonUtils jsonUtils = JsonUtils.getInstance(json);
		book = jsonUtils.getBook();
		
		return book;
	}

}
