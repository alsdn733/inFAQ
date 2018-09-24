package com.faq.service;

import java.util.List;

import com.mycompany.vo.faqContent;

public interface ContentService {
	public List<faqContent> getFaqContentList(String search_word);
	public int hitUpdate(int noParam, String companyParam);
}
