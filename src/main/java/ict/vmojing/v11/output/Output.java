package ict.vmojing.v11.output;

import ict.vmojing.v11.dao.WeiboDao;
import ict.vmojing.v11.model.Weibo;
import ict.vmojing.v11.service.TopicServiceImpl;

import ict.vmojing.v11.utils.WriteFileTool;

import java.util.*;

import org.apache.log4j.Logger;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class Output {
	private static final Logger log = Logger.getLogger(Output.class);
	public static void output(){
		TopicServiceImpl topicService = new TopicServiceImpl();
		DBCursor cursor = topicService.getTopicCursor("hahe");
		List<Weibo> weibos = WeiboDao.toList(cursor);
		List<String> pos = new ArrayList<String>();
		List<String> neg = new ArrayList<String>();
		List<String> no = new ArrayList<String>();
		for(Weibo w:weibos){
			String content = w.getContent();
			if(w.getEmotion() == -1)
				neg.add(content);
			else if(w.getEmotion() == 1)
				pos.add(content);
			else
				no.add(content);
		}
//		WriteFile.write(pos, "./src/main/resources/pos_hahe");
//		WriteFile.write(neg, "./src/main/resources/neg_hahe");
//		WriteFile.write(no, "./src/main/resources/no_hahe");
		log.info("支持个数:"+pos.size()+" 反对个数:"+neg.size()+" 中立个数:"+no.size());
	}
	public static void main(String[] args) {
		output();	
		
		
		DBObject obj = new BasicDBObject();
		obj.put("es",1);
		
		
	}
}
