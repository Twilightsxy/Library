package library.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.jdbc.core.JdbcTemplate;

import library.config.DataConfig;

/**
 * 展示书籍数量
 * 
 * @author dyf
 * @version 1.0
 *
 */
public class BookStatusTag extends SimpleTagSupport {

	DataConfig dataConfig = new DataConfig();
	JdbcTemplate jdbcTemplate = dataConfig.jdbcTemplate(dataConfig.dataSource());

	public void doTag() throws JspException, IOException {
		try {
			JspWriter out = getJspContext().getOut();
			out.print(jdbcTemplate.queryForInt("select count(*) from book where status = 2"));
		} catch (java.io.IOException e) {
			throw new JspTagException(e.getMessage());
		}
	}
}
