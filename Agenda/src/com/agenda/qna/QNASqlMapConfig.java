package com.agenda.qna;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class QNASqlMapConfig {
	
	//모든 마이바티스 애플리케이션은 SqlSessionFactory 인스턴스를 사용한다
	private SqlSessionFactory sqlSessionFactory;
	
	public SqlSessionFactory getSqlSessionFactory() {
		
		//생성한 config 파일이 존재하는 경로 설정
		String resource ="com/agenda/qna/QNAconfig.xml";
		Reader reader = null;
		
		try {
			reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
			System.out.println("1. 빌드 연결");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("[ERROR] 빌드 연결 실패");
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
