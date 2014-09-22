package ict.vmojing.v11.utils;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.text.html.parser.Entity;




import org.apache.log4j.Logger;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
public class WriteFileTool {
	private static final Logger log = Logger.getLogger(WriteFileTool.class);
	/**
	 * 将List类型，写入文件
	 * @param content
	 * @param fileName 写入文件的路径
	 */
	public static void write(List<String> content,String fileName){
		try {
			FileWriter wr = new FileWriter(fileName);
			wr.write("num"+content.size()+"\n");
			for(String str :content){
				wr.write(str+"\n");
			}
			wr.close();
		} catch (Exception e) {
			log.error("can not wirte file");
		}
	}
	/**
	 * 将Map<String, Integer>类型，写入文件
	 * @param map
	 * @param fileName 写入文件的路径
	 */
	public static void write(Map<String, Integer> map,String fileName){
		try {
			FileWriter wr = new FileWriter(fileName);
			Iterator<Entry<String,Integer>> iter = map.entrySet().iterator();
			Entry<String,Integer> entry = null;
			while(iter.hasNext()){
				entry = iter.next();
				wr.write(entry.getKey()+" "+entry.getValue()+"\n");
			}
			wr.close();
		} catch (Exception e) {
			log.error("can not wirte file");
		}
	}
	/**
	 * 将Map<String, Integer>类型，写入文件
	 * @param map
	 * @param threshold 阈值
	 * @param fileName 写入文件的路径
	 */
	public static void write(Map<String, Integer> map,String fileName,Integer threshold){
		try {
			FileWriter wr = new FileWriter(fileName);
			Iterator<Entry<String,Integer>> iter = map.entrySet().iterator();
			Entry<String,Integer> entry = null;
			while(iter.hasNext()){
				entry = iter.next();
				if(Math.abs(entry.getValue())>threshold)
					wr.write(entry.getKey()+" "+entry.getValue()+"\n");
			}
			wr.close();
		} catch (Exception e) {
			log.error("can not wirte file");
		}
	}
	/**
	 * 将DBCursor(Model.WordScore)类型，写入文件
	 * @param map
	 * @param fileName 写入文件的路径
	 */
	public static void write(DBCursor cursor,String fileName){
		try {
			FileWriter wr = new FileWriter(fileName);
			int i = 0;
			while(cursor.hasNext()){
				i++;
				DBObject obj = cursor.next();
				wr.write((String) obj.get("word") + " "+(3-(i/100))+"\n");
			}
			wr.close();
		} catch (Exception e) {
			log.error("can not wirte file");
		}
	}
	/**
	 * 写入词典
	 * @param key
	 * @param value
	 * @param outputName
	 */
	public static void addWord(String key,Integer value,String outputName){
		try {
			FileWriter writer = new FileWriter(outputName, true);
			writer.write(key + " " + value + "\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
