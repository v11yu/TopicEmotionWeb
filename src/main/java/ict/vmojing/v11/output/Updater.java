package ict.vmojing.v11.output;

import ict.vmojing.v11.algorithm.nlp.EmotionProcessor;
import ict.vmojing.v11.dao.WeiboDao;
import ict.vmojing.v11.service.TopicService;
import ict.vmojing.v11.service.TopicServiceImpl;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Updater {
	public static void test(){
		WeiboDao wdao = new WeiboDao("sign_weibo");
		TopicService topicService = new TopicServiceImpl(wdao);
		DBCursor cursor = topicService.getTopicCursor("hahe");
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			obj.put("es", "heihei");
			wdao.update(obj, "es");
		}
	}
	public static void main(String[] args) {
		WeiboDao wdao = new WeiboDao();
		TopicService topicService = new TopicServiceImpl(wdao);
		DBCursor cursor = topicService.getTopicCursor("hahe");
		EmotionProcessor ep = EmotionProcessor.getUniqueProcess();
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			String content = (String) obj.get("text");
			obj.put("es",ep.getEmotionRankByNgram(content));
			wdao.update(obj, "es");
		}
	}
}
