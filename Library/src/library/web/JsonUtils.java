package library.web;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import library.db.BookRepository;
import library.domain.Book;

public class JsonUtils {
	private Book book = new Book();
	@Autowired
	private BookRepository bookRepository;

	// 私有的构造函数，初始化的时候生成book
	private JsonUtils(String json) {
		JSONObject jsonObject = JSONObject.fromObject(json);
		Book book = new Book();

		try {
			book.setBookNo(bookRepository.getMaxNo() + 1);
			book.setTitle(jsonObject.getString("title"));

			// 这里的设置作者有问题，应该是从中括号里再取一层
			JSONArray jsonArray = jsonObject.getJSONArray("author");
			String author = (String) jsonArray.get(0);
			book.setAuthor(author);
			book.setBrief(jsonObject.getString("summary"));
			book.setISBN(jsonObject.getString("isbn13"));
			book.setPrice(jsonObject.getString("price"));
			book.setTime(jsonObject.getString("pubdate"));
			book.setPublish(jsonObject.getString("publisher"));
			this.book = book;
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("jason字符串未被正确解析!");
		}

	}

	public static JsonUtils getInstance(String json) {
		return new JsonUtils(json);
	}

	public Book getBook() {
		return this.book;
	}

}
