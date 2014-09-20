package ict.vmojing.v11.service;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
















import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import ict.vmojing.v11.algorithm.nlp.EmotionProcessor;
import ict.vmojing.v11.dao.*;
import ict.vmojing.v11.model.Weibo;
import ict.vmojing.v11.utils.MyLog;
import ict.vmojing.v11.utils.WordManager;

@Service
public class TopicServiceImpl implements TopicService{
	private static final String ValueName = "tid";
	WeiboDao wdao ;
	public TopicServiceImpl(){
		wdao = new WeiboDao();
	}
	public TopicServiceImpl(WeiboDao wdao){
		this.wdao = wdao;
	}

	public DBCursor getTopicCursor(String topicId){
		DBCursor cursor = wdao.findByValueEquals(ValueName, new ObjectId(topicId));
		return cursor;
	}
	@Override
	public DBCursor getTopicCursor(String topicId, Integer itemCount,
			Integer pageNum) {
		DBCursor cursor = wdao.findByValueEquals(ValueName, topicId).skip(itemCount * pageNum).limit(itemCount);;
		return cursor;
	}
	@Override
	public void updateDic() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Weibo> addDBCursorWithEmotion(DBCursor weiboCursor) {
		// TODO Auto-generated method stub
		List<Weibo> weibos = new ArrayList<Weibo>();
		EmotionProcessor eProcess = EmotionProcessor.getUniqueProcess();
		while(weiboCursor.hasNext()){
			DBObject object = weiboCursor.next();
			Weibo weibo = new Weibo(object);
			weibo.setEmotion(eProcess.getEmotionRankByNgram(weibo.getContent()));
			weibos.add(weibo);
		}
		return weibos;
	}
	@Override
	public String toJSONObject(DBCursor weiboCursor) {
		// TODO Auto-generated method stub
		
		return null;
	}
	

	public static void main(String[] args) throws InterruptedException {
		TopicService topicService = new TopicServiceImpl();

		DBCursor cursor = topicService.getTopicCursor("5406d53a80cdd08b4dd5485b");
		System.out.println(cursor.count());
		
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			Weibo weibo = new Weibo(obj);
			System.out.println(weibo.toString());
			Thread.sleep(1000*10);
		}
	}
	

}
