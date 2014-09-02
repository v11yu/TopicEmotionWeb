package ict.vmojing.v11.output;

import java.util.Scanner;

import ict.vmojing.v11.algorithm.nlp.EmotionProcessor;
import ict.vmojing.v11.dao.WeiboDao;
import ict.vmojing.v11.service.TopicService;
import ict.vmojing.v11.service.TopicServiceImpl;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * 用于数据手工标记
 * @author v11
 * @date 2014年9月2日
 * @version 1.0
 */
public class Signer {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in);
		TopicService topicService = new TopicServiceImpl();
		WeiboDao wdao = new WeiboDao("sign_weibo");
		DBCursor cursor = topicService.getTopicCursor("hahe",150,1);
		int i=0;
		EmotionProcessor ep = EmotionProcessor.getUniqueProcess();
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			String type;
			String t = (String) obj.get("text");
			System.out.println(++i+" "+ep.getEmotionRankByNgram(t)+" "+obj.get("text"));
			type = cin.next();
			obj.put("es",type);
			wdao.save(obj);
		}
		
	}
}
