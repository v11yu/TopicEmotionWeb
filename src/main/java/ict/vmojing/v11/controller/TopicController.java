package ict.vmojing.v11.controller;

import ict.vmojing.v11.model.Weibo;
import ict.vmojing.v11.service.TopicService;
import ict.vmojing.v11.service.TopicServiceImpl;
import ict.vmojing.v11.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

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
		String res = topicService.getTopicListToString(topicId, itemCount, pageNum);
		MyLog.logInfo(res);
		return res;
	}
	@RequestMapping("showList")
	public String getList(String topicId,Model model){
		topicService = new TopicServiceImpl();
		List<Weibo> weibos = topicService.getTopicWeiboList(topicId);
		model.addAttribute("weibos",weibos);
		return "topic";
	}
}