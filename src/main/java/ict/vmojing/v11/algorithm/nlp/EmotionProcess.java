package ict.vmojing.v11.algorithm.nlp;

import ict.vmojing.v11.algorithm.nlp.word.WordSplit;
import ict.vmojing.v11.utils.MyLog;
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
	private final Double threshold_low = -0.6;
	private final Double threshold_high = 2.0;
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
		symbolMap = WordManager.getUniqueDictionary().getSymbols();

	}
	private EmotionProcess(){
		init();
	}
	/**
	 * 计算文本得分
	 * @param content
	 * @return
	 */
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
	private Integer getEmotionRankByNgram(String content,double high,double low){
		int check = checkSymbol(content);
		if(check != -2) return check;
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
		return getEmotionRankByNgram(content,threshold_high,threshold_low);
	}
	public static void main(String[] args) {
		String content = "农夫山泉免费送水啦，2瓶4升的。在支付宝钱包里，找到“服务”，手写输入添加“农夫山泉”。然后点“我要订水”-“家庭订水”，按着操作进行，到最后选购产品时，价格就是0，也没有送货费。快来领吧！给我32个赞";
		
		EmotionProcess eProcess = getUniqueProcess();
		MyLog.logInfo(eProcess.getEmotionRankByNgram(content)+" "+content);
	}
}
