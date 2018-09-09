package com.faq.service;

import java.util.List;

import com.mycompany.vo.connection;
import com.mycompany.vo.content;

public interface ConnectionService{
	public List<connection> getConnectionList();
	public List<content> getContentList();
}
