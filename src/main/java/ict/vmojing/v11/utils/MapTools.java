package ict.vmojing.v11.utils;

import java.util.HashMap;

public class MapTools {

	/**
	 * 将信息累加保存至Map
	 * @param map 
	 * @param word 关键字
	 * @param score 需累加值
	 */
	public static void setCountToMap(HashMap<String, Integer> map,String word,Integer score){
		//System.out.println(word+" "+type);
		int cc = 0;
		if(map.containsKey(word)){
			cc = map.get(word);
		}
		map.put(word, cc+score);
	}
}
