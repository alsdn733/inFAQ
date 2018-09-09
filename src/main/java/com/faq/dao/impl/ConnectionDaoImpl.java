package com.faq.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.faq.dao.ConnectionDao;
import com.mycompany.vo.connection;
import com.mycompany.vo.content;

@Repository("ConnectionDao")
public class ConnectionDaoImpl implements ConnectionDao{

//	private final String NS = "com.mycompany.myapp";
	
	@Autowired
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession){
		this.sqlSession = sqlSession;
	}
	@Override
	public List<connection> selectConnection(Map<String, Object> map) {
//		return sqlSession.selectList(NS+".selectConnection");
		return sqlSession.selectList("selectConnection", map);
	}

	@Override
	public List<content> selectContent(Map<String, Object> map) {
//		return sqlSession.selectList(NS+".selectContent");
		return sqlSession.selectList("selectContent", map);
	}
	
}
