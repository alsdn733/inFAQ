package com.faq.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faq.service.ConnectionService;
import com.faq.service.ContentService;
import com.mycompany.vo.content;
import com.mycompany.vo.contentPreview;
import com.mycompany.vo.faqContent;

@Controller
public class HomeController {
	
	@Autowired
	private ConnectionService connectionService;
	@Autowired
	private ContentService contentService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, 
			@RequestParam(value="search_word", required = false) String search_word) {
		List<faqContent> faqContent =  contentService.getFaqContentList(search_word);
		if(search_word == null) search_word ="";
		model.addAttribute("search_word", search_word);
		model.addAttribute("faqContent", faqContent);
		return "home";
	}
	
	@RequestMapping(value ="/admin", method = RequestMethod.GET)
	public String admin(Model model,
			@RequestParam(value="search_word", required = false) String search_word) {
		List<faqContent> faqContent =  contentService.getFaqContentList(search_word);
		if(search_word == null) search_word ="";
		model.addAttribute("search_word", search_word);
		model.addAttribute("faqContent", faqContent);
		return "admin";
	}
	
	@RequestMapping(value ="/edit/{no}", method = RequestMethod.GET)
	public String editPage(Model model, @PathVariable("no") int no) {
		List<faqContent> faqContent =  contentService.getFaqContentList(String.valueOf(no)); // no 값을 String으로 변환
		faqContent.get(0).setTitle(faqContent.get(0).getTitle().replace("<br />", "\r\n")); // title의 <br />을 \r\n으로 바꾸어준다.
		faqContent.get(0).setContent(faqContent.get(0).getContent().replace("<br />", "\r\n")); // content의 <br />을 \r\n으로 바꾸어준다.
		model.addAttribute("faqContent", faqContent);
		return "edit";
	}
	
	@RequestMapping(value="/editContent/{no}", method = RequestMethod.POST)
	public String editPageContent(Model model, @PathVariable("no") int no, @ModelAttribute faqContent faqContent){
		faqContent.setTitle(faqContent.getTitle().replace("\r\n", "<br />"));
		faqContent.setContent(faqContent.getContent().replace("\r\n", "<br />"));
		contentService.contentUpdate(faqContent); // 게시물 수정
		return "redirect:/admin";
	}
	
	@ResponseBody
	@RequestMapping(value = "/hitUpdate", method = RequestMethod.GET)
	public int hitUpdate(@RequestParam int noParam, @RequestParam String companyParam) {
		return contentService.hitUpdate(noParam, companyParam);
	}
	@RequestMapping(value = "/getTags", method = RequestMethod.GET)
	public @ResponseBody List<contentPreview> getTags(@RequestParam String tagName) {
		return simulateSearchResult(tagName);
	}
	private List<contentPreview> simulateSearchResult(String tagName) {
		List<content> getContent = connectionService.getContentList();
		List<contentPreview> data = new ArrayList<contentPreview>();
		for(int i=0; i<getContent.size(); i++){
			data.add(new contentPreview(i+1, getContent.get(i).getTitle()));
		}
		List<contentPreview> result = new ArrayList<contentPreview>();
		try{
			for(contentPreview contentPreview : data){
				if(contentPreview.getTitle().contains(tagName)){
					result.add(contentPreview);
				}
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		return result;
	}

}
