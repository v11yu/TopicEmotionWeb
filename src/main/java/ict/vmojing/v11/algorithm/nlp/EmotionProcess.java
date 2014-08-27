package ict.vmojing.v11.algorithm.nlp;

import ict.vmojing.v11.algorithm.nlp.word.WordSplit;
import ict.vmojing.v11.utils.WordManager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.sound.midi.MidiDevice.Info;

import com.mongodb.util.UniqueList;

/**
 * 情感分类处理
 * 单例模式
 * @author v11
 * @date 2014年8月26日
 * @version 1.0
 */
public class EmotionProcess {
	/*
	 * 词典
	 */
	private HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
	/*
	 * 表情词典
	 */
	private HashMap<String, Integer> symbolMap = new HashMap<String, Integer>();
	
	private static EmotionProcess uniqueEmotionProcess;
	public static synchronized EmotionProcess getUniqueProcess(){
		if(uniqueEmotionProcess == null){
			uniqueEmotionProcess = new EmotionProcess();
		}
		return uniqueEmotionProcess;
	}
	/**
	 * 初始化词典
	 */
	private void init(){
		wordMap = WordManager.getUniqueDictionary().getWords();

	}
	private EmotionProcess(){
		init();
	}
	public Double getScore(String content){
		Double score;
		List<String> ls = WordSplit.splitByNGrams(content, 1, 3);
		int result = 0;
		int count = 0;
		for(String str : ls){
			if(wordMap.containsKey(str)){
				result += wordMap.get(str);
				count++;
			}
		}
		if(count == 0) return 0.0;
		return 1.0*result / count;
	}
	/**
	 * 判断是否存在表情符号
	 * @param content 微博文本
	 * @return -2 不存在
	 * 			1 正面
	 * 			0 中性
	 * 			-1 负面
	 */
	public Integer checkSymbol(String content){
		Iterator<Entry<String, Integer>> iter = symbolMap.entrySet().iterator();
		while(iter.hasNext()){
			Entry<String, Integer> en = iter.next();
			if(content.contains(en.getKey()))
				return en.getValue();
		}
		return -2;
	}

	/**
	 * 获取一段文本的情感倾向
	 * @param rawText
	 * @return 1为正面，0为中性，-1为负面
	 */
	public Integer getEmotionRankByNgram(String content,double high,double low){

		double res = getScore(content);
		if(res > high) return 1;
		else if(res < low) return -1;
		else return 0;
	}
	/**
	 * 获取一段文本的情感倾向
	 * @param rawText
	 * @return 1为正面，0为中性，-1为负面
	 */
	public Integer getEmotionRankByNgram(String content){
		int check = checkSymbol(content);
		if(check != -2) return check;
		double high = 2;
		double low = -0.6;
		double res = getScore(content);
		if(res > high) return 1;
		else if(res < low) return -1;
		else return 0;
	}
}
