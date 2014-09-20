package ict.vmojing.v11.controller;

import ict.vmojing.v11.dao.WeiboDao;
import ict.vmojing.v11.model.Weibo;
import ict.vmojing.v11.service.TopicService;
import ict.vmojing.v11.service.TopicServiceImpl;
import ict.vmojing.v11.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

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
	
	private TopicService topicService;

	@RequestMapping("doGetTopicWeiboList")
	@ResponseBody
	public String showWeiboList(String topicId,Integer itemCount,Integer pageNum) {
		topicService = new TopicServiceImpl();
		if(itemCount == null) itemCount = 10;
		if(pageNum == null) pageNum = 1;
		String res = topicService.toJSONObject(topicService.getTopicCursor(topicId, itemCount, pageNum));
		MyLog.logInfo(res);
		return res;
	}
	@RequestMapping("readTagCloud")
	@ResponseBody
	public String readTagCloud(String topicId,Integer emotion){
		/*
		 * text
		 * weight
		 * link
		 */
		JSONArray words = new JSONArray();
		for(int i = 0;i<10;i++){
			JSONObject word = new JSONObject();
			try {
				word.put("text", i+"");
				word.put("weight", i/2);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			words.put(word);
			
		}
		return words.toString();
	}
	@RequestMapping("tagCloud")
	public String showTagCloud(String topicId){
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