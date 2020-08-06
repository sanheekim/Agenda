package com.agenda.googlelogion;

import java.io.IOException;
import java.io.Reader;

import javax.sound.midi.Soundbank;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class googleLoginSqlMapConfig {
	
	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		
		String resource = "com/agenda/googleogin/googleLoginConfig.xml";
		Reader reader = null;
		
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			System.out.println("1. 빌드연결");
		} catch (IOException e) {
			
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sqlSessionFactory;
	}

}
