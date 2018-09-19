package com.faq.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.faq.service.ConnectionService;
import com.faq.service.ContentService;
import com.mycompany.vo.connection;
import com.mycompany.vo.content;
import com.mycompany.vo.contentPreview;
import com.mycompany.vo.faqContent;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	private ConnectionService connectionService;
	@Autowired
	private ContentService contentService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, @RequestParam(value="search_word", required = false) String search_word) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );

		List<connection> connectionList = connectionService.getConnectionList();
		model.addAttribute("connectionList", connectionList);
		System.out.println("connectionList size :"+connectionList.size());
		
		System.out.println("search_word : "+search_word);
		List<faqContent> faqContent =  contentService.getFaqContentList(search_word);
		for(int i=0; i<faqContent.size(); i++){
			System.out.println("faqContent size:"+faqContent.get(i));
		}
		System.out.println(faqContent.size());
		if(search_word != null){
			model.addAttribute("search_word", search_word);
		}
		model.addAttribute("faqContent", faqContent);
		return "home";
//		return "editor";
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
