package library.config;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * 数据库配置
 * 
 * @author ozj
 * @version v1.0
 */
@Configuration
public class DataConfig {

	/**
	 * 数据源设置，采用spring自带的内嵌数据库h2
	 * 
	 * @return
	 */
	@Bean
	public DataSource dataSource() {
		@SuppressWarnings("resource")
		ApplicationContext ctx = new ClassPathXmlApplicationContext("postgresql.xml");
        DataSource dataSource = ctx.getBean("dataSource",DataSource.class);
        return dataSource;
	}

	/**
	 * jdbc模板配置，采用spring默认的JdbcTemplate模板
	 * 
	 * @param dataSource
	 *            数据源
	 * @return
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}
