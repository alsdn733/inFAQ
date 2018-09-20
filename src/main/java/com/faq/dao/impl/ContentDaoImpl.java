package com.faq.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.faq.dao.ContentDao;
import com.mycompany.vo.faqContent;

@Repository("ContentDao")
public class ContentDaoImpl implements ContentDao{
	@Autowired
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	@Override
	public List<faqContent> selectFaqContent(Map<String, Object> map) {
		System.out.println("daoimpl:"+map.get("search_word"));
		return sqlSession.selectList("selectFaqContent", map); // Content.xml에 있는 이름과 일치해야 한다.
	}
	
}
