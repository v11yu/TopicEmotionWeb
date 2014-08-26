package ict.vmojing.v11.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("topic")
public class TopicController {
	@Autowired
	private String topicId;
	@RequestMapping("weiboList")
	public String showWeiboList() {
		System.out.println(topicId);
		return "Topic";
	}

}