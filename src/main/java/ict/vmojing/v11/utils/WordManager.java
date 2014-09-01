package ict.vmojing.v11.utils;

import java.util.*;

/**
 * 分词过程中需要的词典类<br/>
 * 单例模式
 * @author v11
 * @date 2014年8月26日
 * @version 1.0
 */
public class WordManager {
	private List<String> stops ;
	private HashMap<String, Integer> words ;
	private HashMap<String, Integer> symbols;
	private static WordManager uniqueDictionary;
	private List<String> rules ;
	private final String _stop_word = "stop_words";
	private final String _words = "words";
	private final String _update_words = "update_words";
	private final String _user_words = "user_words";
	private final String _symbols = "symbols";
	private final String _rules = "rules";
	private WordManager(){
		stops = ReadFile.readFileToList(_stop_word);
		words = new HashMap<String, Integer>();
		words.putAll(ReadFile.readFileToMap(_words));
		words.putAll(ReadFile.readFileToMap(_update_words));
		words.putAll(ReadFile.readFileToMap(_user_words));
		symbols = ReadFile.readFileToMap(_symbols);
		rules = ReadFile.readFileToList(_rules);
	}
	public static WordManager getUniqueDictionary(){
		if(uniqueDictionary == null){
			uniqueDictionary = new WordManager();
		}
		return uniqueDictionary;
	}
	public List<String> getStops() {
		return stops;
	}
	public void setStops(List<String> stops) {
		this.stops = stops;
	}
	public HashMap<String, Integer> getWords() {
		return words;
	}
	public void setWords(HashMap<String, Integer> words) {
		this.words = words;
	}
	public HashMap<String, Integer> getSymbols() {
		return symbols;
	}
	public void setSymbols(HashMap<String, Integer> symbols) {
		this.symbols = symbols;
	}
	public List<String> getRules() {
		return rules;
	}
	public void setRules(List<String> rules) {
		this.rules = rules;
	}
	
}
