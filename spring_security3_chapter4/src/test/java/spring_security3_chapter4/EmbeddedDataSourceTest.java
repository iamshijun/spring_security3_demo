package spring_security3_chapter4;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=EmbeddedDataSourceTest.TestContext.class)
public class EmbeddedDataSourceTest {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Before
	public void setUp(){}
	
	@Test
	public void testDummy(){}
	
	@Test
	public void testQueryAllUsers(){
		MappingSqlQuery<User> sqlQuery = new MappingSqlQuery<User>(dataSource,"select * from users"){
			@Override
			protected User mapRow(ResultSet rs, int rowNum) throws SQLException {
				//return new org.springframework.security.core.userdetails.User(rs.getString(2), rs.getString(3), Collections.<GrantedAuthority>emptyList());
				return new User(rs.getString("username"),rs.getString("password"),rs.getBoolean("enabled"),rs.getString("salt"));
			}
		};
		sqlQuery.setRowsExpected(4);
		
		List<User> users = sqlQuery.execute();
		for(User user : users){
			System.out.println(user.username + " , salt : " + user.salt);
		}
	}
	
	static class User{
		String username;
		String password;
		boolean enabled;
		String salt;
		public User(String username, String password,
				boolean enabled, String salt) {
			super();
			this.username = username;
			this.password = password;
			this.enabled = enabled;
			this.salt = salt;
		}
		
		
	}
	
	
	@Configuration
	static class TestContext{
		@Bean
		public DataSource embeddedDataSource(){
			EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
			databaseBuilder.setType(EmbeddedDatabaseType.HSQL);
			
			databaseBuilder.addScript("security-schema.sql");
			databaseBuilder.addScript("test-users-groups-data.sql");
			
			//databaseBuilder.addScript("test-data.sql");
			return databaseBuilder.build();
		}
		
		@Bean
		public JdbcTemplate jdbcTemplate(){
			return new JdbcTemplate(embeddedDataSource());
		}
	}
}
