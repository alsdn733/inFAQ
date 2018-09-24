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

	@Override
	public int hitUpdate(int no, String company) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("no", no);
		map.put("company", company);
		contentDao.hitUpdate(map);
		List<faqContent> faqContent = contentDao.selectFaqContent(map); // 해당 no의 게시물을 가져온다. 
		map.put("title", faqContent.get(0).getTitle()); // 게시물의 질문을 가져온다
		return contentDao.insertClientNeed(map); // 문의 온 회사의 이름, 질문을 입력한다.
	}
}