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
/**
 * 对生成的词 或 现有的词典
 * 进行过滤
 * 生成过滤后的词典
 * @author v11
 *
 */
public class WordFilter {

	/**
	 * 根据停用词过滤词典
	 * @param map 原词典
	 * @return 过滤后的词典
	 */
	public static HashMap<String, Integer> filterStopWords(HashMap<String, Integer> map){
		MyLog.logInfo("map object id :"+map.toString());
		Iterator<Entry<String, Integer>> iter = map.entrySet().iterator();
		Set<String> stops = WordManager.getUniqueDictionary().getStops();
		while(iter.hasNext()){
			Entry<String, Integer> en = iter.next();
			if(stops.contains(en.getKey()) || en.getKey().length()<2){
				iter.remove();
			}
		}
		return map;
	}

}
