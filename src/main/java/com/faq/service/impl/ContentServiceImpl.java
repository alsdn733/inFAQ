package com.faq.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.faq.dao.ContentDao;
import com.faq.service.ContentService;
import com.mycompany.vo.faqContent;

@Service("ContentService")
public class ContentServiceImpl implements ContentService{
	@Resource(name="ContentDao")
	private ContentDao contentDao;

	@Override
	public List<faqContent> getFaqContentList(String search_word) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search_word", search_word);
		return contentDao.selectFaqContent(map);
	}
}
