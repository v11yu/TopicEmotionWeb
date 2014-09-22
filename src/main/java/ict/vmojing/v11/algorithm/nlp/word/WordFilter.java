package ict.vmojing.v11.algorithm.nlp.word;
import ict.vmojing.v11.utils.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

/**
 * 对生成的词 或 现有的词典
 * 进行过滤
 * 生成过滤后的词典
 * @author v11
 *
 */
public class WordFilter {
	private static final Logger log = Logger.getLogger(WordFilter.class);
	/**
	 * 根据停用词过滤词典
	 * @param map 原词典
	 * @return 过滤后的词典
	 */
	public static HashMap<String, Integer> filterStopWords(HashMap<String, Integer> map){
		log.info("map object id :"+map.toString());
		Iterator<Entry<String, Integer>> iter = map.entrySet().iterator();
		List<String> stops = WordManager.getUniqueDictionary().getStops();
		while(iter.hasNext()){
			Entry<String, Integer> en = iter.next();
			boolean haveStopWord = false;
			for(String stop : stops){
				if(en.getKey().contains(stop)){
					haveStopWord = true;
					break;
				}
			}
			if(haveStopWord || en.getKey().length()<2){
				iter.remove();
			}
		}
		return map;
	}
	/**
	 * 创建规则
	 * @param content 文本
	 * @return 1 pos
	 * -1 neg
	 * 0 中性
	 * -2 没有存在规则
	 */
	public static Integer judgeByRule(String content){
		List<String> rules = WordManager.getUniqueDictionary().getRules();
		for(String rule : rules){
			String tmp[] = rule.split(" ");
			int v = Integer.parseInt(tmp[1]);
			tmp = tmp[0].split("&");
			boolean ok = true;
			for(String key:tmp){
				if(!content.contains(key)){
					ok = false;
					break;
				}
			}
			if(ok){
				if(v ==0) return v;
				return v>0?1:-1;
			}
		}
		return -2;
	}
	public static void main(String[] args) {
		HashMap<String, Integer> res = WordManager.getUniqueDictionary().getWords();
		res = filterStopWords(res);
		WriteFileTool.write(res, "./src/main/resources/new_word");
	}
}
