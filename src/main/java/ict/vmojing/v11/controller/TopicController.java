package ict.vmojing.v11.controller;

import ict.vmojing.v11.dao.MongoDBManager;
import ict.vmojing.v11.model.Weibo;
import ict.vmojing.v11.service.TopicService;
import ict.vmojing.v11.service.TopicServiceImpl;

import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/")
public class TopicController {
	private static final Logger log = Logger.getLogger(TopicController.class);
	@Autowired
	private TopicService topicService;
	
	@RequestMapping("readTagCloud")
	@ResponseBody
	public String readTagCloud(String topicId,Integer emotion){
		JSONArray words = new JSONArray();
		for(int i = 0;i<10;i++){
			JSONObject word = new JSONObject();
			try {
				word.put("text", i+"");
				word.put("weight", i/2);
				word.put("link", "none");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			words.put(word);
			
		}
		return words.toString();
	}
	@RequestMapping("tagCloud")
	public String showTagCloud(Model model,String topicId){
		model.addAttribute("topicId", topicId);
		return "tagCloud";
		
	}
	@RequestMapping("weiboList")
	public String showWeiboList(String topicId,Model model){
		topicService = new TopicServiceImpl();
		List<Weibo> weibos = topicService.addDBCursorWithEmotion(topicService.getTopicCursor(topicId));
		model.addAttribute("weibos",weibos);
		return "topic";
	}
}