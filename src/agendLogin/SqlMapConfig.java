package agendLogin;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlMapConfig {

	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		
		String resource = "agendaLogin/loginconfig.xml";
		
		InputStream inputStream = null;
		
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		return sqlSessionFactory;
	}
	
	
}
