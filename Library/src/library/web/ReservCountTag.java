package library.web;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.jdbc.core.JdbcTemplate;

import library.config.DataConfig;


public class ReservCountTag extends SimpleTagSupport{

	
	DataConfig dataConfig = new DataConfig();
	JdbcTemplate jdbcTemplate = dataConfig.jdbcTemplate(dataConfig.dataSource());

	public void doTag() throws JspException, IOException {
		try {
			JspWriter out = getJspContext().getOut();
			out.print(jdbcTemplate.queryForInt("select count(*) from reserve;"));
		} catch (java.io.IOException e) {
			throw new JspTagException(e.getMessage());
		}
	}
}
