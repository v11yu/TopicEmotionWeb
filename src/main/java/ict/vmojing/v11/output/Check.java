package ict.vmojing.v11.output;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import java.util.*;

import ict.vmojing.v11.algorithm.nlp.EmotionProcessor;
import ict.vmojing.v11.dao.WeiboDao;
import ict.vmojing.v11.service.TopicService;
import ict.vmojing.v11.service.TopicServiceImpl;
import ict.vmojing.v11.utils.WriteFile;

public class Check {
	public static void main(String[] args) {
		WeiboDao wdao = new WeiboDao("sign_weibo");
		TopicService topicService = new TopicServiceImpl(wdao);
		DBCursor cursor = topicService.getTopicCursor("hahe");
		EmotionProcessor ep = EmotionProcessor.getUniqueProcess();
		int sum = cursor.count();
		int ok = 0;
		List<String> ls = new ArrayList<String>();
		int pos = 0 ,pp=0;
		int neg = 0 ,nn=0;
		int no = 0,oo=0;
		
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			String type;
			String t = (String) obj.get("text");
			String tt = (String) obj.get("es") + " "+ t;
			ls.add(tt);
			if(Integer.parseInt((String) obj.get("es")) == -1){
				neg ++;
			}
			else if(Integer.parseInt((String) obj.get("es")) == 1){
				pos ++;
			}
			else no++;
			if(ep.getEmotionRankByNgram(t) == -1){
				nn ++;
			}
			else if(ep.getEmotionRankByNgram(t) == 1){
				pp ++;
			}
			else oo++;
			if(ep.getEmotionRankByNgram(t) == Integer.parseInt((String) obj.get("es")))
				ok++;
		}
		System.out.println("手工标识结果 支持:"+pos+" 反对:"+neg+" 中立:"+no);
		System.out.println("系统结果 支持:"+pp+" 反对:"+nn+" 中立:"+oo);
		WriteFile.write(ls, "./src/main/resources/sign_weibo.txt");
		System.out.println("命中："+ok+" 总计："+sum);
		System.out.println("准确率 "+1.0*ok/sum);
	}
	
}
