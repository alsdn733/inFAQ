package com.faq.dao;

import java.util.List;
import java.util.Map;
import com.mycompany.vo.faqContent;

public interface ContentDao {
	public List<faqContent> selectFaqContent(Map<String, Object> map);
	public int hitUpdate(Map<String, Object> map);
	public int insertClientNeed(Map<String, Object> map);
}
