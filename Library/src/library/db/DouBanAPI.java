package library.db;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import library.domain.Book;

/**
 * 豆瓣API接口
 * 
 * @author lhb
 * @version 1.0
 */
public interface DouBanAPI {
	/**
	 * 根据ISBN查书
	 * @param isbn 待查的ISBN
	 * 
	 * @return Book
	 */
	Book searchByISBN(String isbn) throws ClientProtocolException, IOException;
	
}