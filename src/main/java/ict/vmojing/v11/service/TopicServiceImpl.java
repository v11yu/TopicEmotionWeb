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

	public DBCursor getTopicCursor(String topicId){
		WeiboDao wdao = new WeiboDao();
		DBCursor cursor = wdao.findByValueEquals("tid", new ObjectId(topicId));
		return cursor;
	}
	public void updateDic() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getTopicListToString(String topicId,Integer itemCount,Integer pageNum) {
		// TODO Auto-generated method stub
		try {
			DBCursor cursor = getTopicCursor(topicId);
			Integer pageCount = (int) Math.ceil((double) cursor.count()
					/ itemCount);
			JSONObject res = new JSONObject();
			res.put("pageCount", pageCount);
			JSONArray wbList = new JSONArray();
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				wbList.put(obj);
			}
			cursor.close();
			res.put("weiboList", wbList);
			return res.toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Weibo> getTopicWeiboList(String topicId, Integer itemCount,
			Integer pageNum) {
		// TODO Auto-generated method stub
		List<Weibo> weibos = new ArrayList<Weibo>();
		DBCursor cursor = getTopicCursor(topicId);
		EmotionProcessor eProcess = EmotionProcessor.getUniqueProcess();
		while(cursor.hasNext()){
			DBObject object = cursor.next();
			Weibo weibo = new Weibo(object);
			weibo.setEmotion(eProcess.getEmotionRankByNgram(weibo.getContent()));
			weibos.add(weibo);
		}
		return weibos;
	}

}
