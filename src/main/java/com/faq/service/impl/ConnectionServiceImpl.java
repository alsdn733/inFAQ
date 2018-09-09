package com.faq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faq.dao.ConnectionDao;
import com.faq.service.ConnectionService;
import com.mycompany.vo.connection;
import com.mycompany.vo.content;

@Service("ConnectionService")
public class ConnectionServiceImpl implements ConnectionService{
	
	@Resource(name="ConnectionDao")
	private ConnectionDao connectionDao;
	
	@Override
	public List<connection> getConnectionList() {
		Map<String, Object> map = new HashMap<String, Object>();
		return connectionDao.selectConnection(map);
	}

	@Override
	public List<content> getContentList() {
		Map<String, Object> map = new HashMap<String, Object>();
		return connectionDao.selectContent(map);
	}
}
