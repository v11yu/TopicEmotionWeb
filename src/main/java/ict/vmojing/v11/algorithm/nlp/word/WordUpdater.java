package ict.vmojing.v11.algorithm.nlp.word;

import ict.vmojing.v11.algorithm.nlp.EmotionProcessor;
import ict.vmojing.v11.service.TopicService;
import ict.vmojing.v11.service.TopicServiceImpl;
import ict.vmojing.v11.utils.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;








import org.apache.log4j.Logger;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class WordUpdater {
	private static final Logger log = Logger.getLogger(WordUpdater.class);
	private HashMap<String, Integer> updateMap = new HashMap<String, Integer>();
	private final double pos_score = 2.9;
	private final double neg_score = -1.9;
	private final String _content = "text";
	private EmotionProcessor ep = EmotionProcessor.getUniqueProcess();
	/*
	 * 加入词典的最低频率阈值
	 */
	private Integer threshold_count;
	/**
	 * 判断一定为Positive的微博
	 * @param content 微博文本
	 * @return true Positive
	 * 		   false 不确定
	 */
	private boolean getPos(String content){
		int type = ep.checkSymbol(content);
		if(type == 1) return true;
		double score = ep.getScore(content);
		if(score >= pos_score) return true;
		return false;
	}
	/**
	 * 判断一定为negative的微博
	 * @param content 微博文本
	 * @return true negative
	 * 		   false 不确定
	 */
	private boolean getNeg(String content){
		int type = ep.checkSymbol(content);
		if(type == -1) return true;
		double score = ep.getScore(content);
		if(score <= neg_score) return true;
		return false;
	}
	/**
	 * 对于新话题，建立更新词典信息
	 * @param cursor
	 */
	public void setUp(DBCursor cursor,String output){
		setThreshold(cursor.count());
		buildMap(cursor);
		updateMap = WordFilter.filterStopWords(updateMap);
		/*
		 * 输出
		 */
		//addMapWord();
		WriteFileTool.write(MapTool.sortMap(updateMap), output,1);
	}
	/**
	 * 将获取的新词加入词典中
	 */
	private synchronized void addMapWord(String output){
		HashMap<String, Integer> dic = WordManager.getUniqueDictionary().getWords();
		Iterator<Entry<String, Integer>> iter = updateMap.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, Integer> entry = iter.next();
			String keyString = entry.getKey();
			Integer valueInteger = entry.getValue();
			if(!dic.containsKey(keyString) && Math.abs(valueInteger) >= threshold_count){
				log.info("新词项："+keyString+" "+valueInteger);
				dic.put(keyString, valueInteger);
				Integer score = getWordScore(valueInteger);
				WriteFileTool.addWord(keyString,score,output);
			}
			
		}
	}
	/**
	 * 获取词典权重
	 * @param value
	 * @return
	 */
	private Integer getWordScore(Integer value){
		return value/threshold_count;
	}
	/**
	 * 设定词频阈值
	 * @param sum 微博总数
	 */
	private void setThreshold(Integer sum){
		threshold_count = sum / 10;
		log.info("threshold "+threshold_count);
	}
	/**
	 * 构建词典
	 * @param cursor
	 */
	private void buildMap(DBCursor cursor) {
		while (cursor.hasNext()) {
			DBObject obj = cursor.next();
			String content = (String) obj.get(_content);
			log.info(content);
			if (getPos(content)) {
				List<String> words = WordSpliter.splitByNGrams(content, 1, 3);
				for (String word : words) {
					MapTool.setCountToMap(updateMap, word, 1);
				}
			} else if (getNeg(content)) {
				List<String> words = WordSpliter.splitByNGrams(content, 1, 3);
				for (String word : words) {
					MapTool.setCountToMap(updateMap, word, -1);
				}
			}
		}
	}
	public static void main(String[] args) {
		WordUpdater update = new WordUpdater();
		String topicId = "53eb680080cd34525537e95d";
		TopicService tService = new TopicServiceImpl();
		update.setUp(tService.getTopicCursor(topicId),"./src/main/resources/dic_all");
	
		
	}
}
