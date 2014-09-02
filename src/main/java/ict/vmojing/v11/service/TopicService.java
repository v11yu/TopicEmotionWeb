package ict.vmojing.v11.service;

import org.springframework.stereotype.Service;

import com.mongodb.DBCursor;

import java.util.*;

import ict.vmojing.v11.model.*;
public interface TopicService {
	/**
	 * 获取全部话题
	 * @param topicId
	 * @return DBCursor
	 */
	public DBCursor getTopicCursor(String topicId);
	/**
	 * 获取部分话题
	 * @param topicId 
	 * @param itemCount 每页个数
	 * @param pageNum 页数
	 * @return
	 */
	public DBCursor getTopicCursor(String topicId,Integer itemCount,Integer pageNum);
	/**
	 * 更新词典，《待开发》
	 */
	public void updateDic();
	/**
	 * 
	 * 获取部分话题
	 * @param topicId 
	 * @param itemCount 每页个数
	 * @param pageNum 页数
	 * @return
	 */
	public List<Weibo> getTopicWeiboList(String topicId,Integer itemCount,Integer pageNum);
	/**
	 * 获取全部话题
	 * @param topicId
	 * @return
	 */
	public List<Weibo> getTopicWeiboList(String topicId);
	/**
	 * 转化为json
	 * @param topicId
	 * @param itemCount
	 * @param pageNum
	 * @return
	 */
	public String getTopicListToString(String topicId,Integer itemCount,Integer pageNum);
}
