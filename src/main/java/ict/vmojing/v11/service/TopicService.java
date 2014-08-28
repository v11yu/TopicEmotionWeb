package ict.vmojing.v11.service;

import org.springframework.stereotype.Service;

import com.mongodb.DBCursor;
import java.util.*;
import ict.vmojing.v11.model.*;
public interface TopicService {
	public void updateDic();
	public String getTopicListToString(String topicId,Integer itemCount,Integer pageNum);
	public List<Weibo> getTopicWeiboList(String topicId,Integer itemCount,Integer pageNum);
}
