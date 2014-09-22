package ict.vmojing.v11.algorithm.nlp.word;

import java.util.ArrayList;
import java.util.List;



public class WordSpliter {
	/**
	 * 利用N-gram切割文本
	 * @param content 文本
	 * @param n N-gram的N
	 * @return
	 */
	public static List<String> splitByNGram(String content,int n){
		List<String> ls = new ArrayList<String>();
		for(int i=0;i<content.length();i++){
			String tmp = "";
			for(int j = n-1;j>=0 ;j--)
				if(i-j>=0)
					tmp += content.charAt(i-j);
			ls.add(tmp);
		}
		return ls;
		
	}
	/**
	 * 利用Ngram分词切割
	 * @param content 文本
	 * @param begin N的最小值
	 * @param end N的最大值
	 * @return返回n=begin 到 n=end的所有结果
	 */
	public static List<String> splitByNGrams(String content,int begin,int end){
		List<String> ls = new ArrayList<String>();
		for(int i = begin ; i<=end;i++){
			ls.addAll(splitByNGram(content,i));
		}
		return ls;
	}

}
