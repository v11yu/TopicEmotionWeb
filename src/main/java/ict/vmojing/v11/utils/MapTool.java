package ict.vmojing.v11.utils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


public class MapTool {

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
	/**
	 * map排序
	 * @param map
	 * @return
	 */
	public static TreeMap<String, Integer> sortMap(HashMap<String, Integer> map){
		ValueComparator bvp = new ValueComparator(map);
		TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>(bvp);
		sortedMap.putAll(map);
		return sortedMap;
	}
}
class ValueComparator implements Comparator<String>{
	Map<String, Integer> base;
	public ValueComparator(Map<String,Integer> base){
		this.base = base;
		
	}
	public int compare(String o1, String o2) {
		// TODO Auto-generated method stub
		if(base.get(o1) > base.get(o2)){
			return -1;
		}
		else return 1;
		//return base.get(o1) - base.get(o2);
	}
	
}
