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
	WeiboDao wdao ;
	public TopicServiceImpl(){
		wdao = new WeiboDao();
	}
	public TopicServiceImpl(WeiboDao wdao){
		this.wdao = wdao;
	}
	/**
	 * DBCursor to list
	 * @param cursor
	 * @return
	 */
	private List<Weibo> toList(DBCursor cursor){
		List<Weibo> weibos = new ArrayList<Weibo>();
		EmotionProcessor eProcess = EmotionProcessor.getUniqueProcess();
		while(cursor.hasNext()){
			DBObject object = cursor.next();
			Weibo weibo = new Weibo(object);
			weibo.setEmotion(eProcess.getEmotionRankByNgram(weibo.getContent()));
			weibos.add(weibo);
		}
		return weibos;
	}
	public DBCursor getTopicCursor(String topicId){
		DBCursor cursor = wdao.findByValueEquals("cid", topicId);
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
		DBCursor cursor = getTopicCursor(topicId,itemCount,pageNum);
		return toList(cursor);
	}
	@Override
	public DBCursor getTopicCursor(String topicId, Integer itemCount,
			Integer pageNum) {
		DBCursor cursor = wdao.findByValueEquals("cid", topicId).skip(itemCount * pageNum).limit(itemCount);;
		return cursor;
	}
	@Override
	public List<Weibo> getTopicWeiboList(String topicId) {
		DBCursor cursor = getTopicCursor(topicId);
		return toList(cursor);
	}
	public static void main(String[] args) {
		TopicService topicService = new TopicServiceImpl();
		WeiboDao wdao = new WeiboDao("sign_weibo");
		DBCursor cursor = topicService.getTopicCursor("hahe",150,0);
	}

}
