package com.faq.dao;

import java.util.List;
import java.util.Map;

import com.mycompany.vo.connection;
import com.mycompany.vo.content;

public interface ConnectionDao {
	public List<connection> selectConnection(Map<String, Object> map);
	public List<content> selectContent(Map<String, Object> map);
}
