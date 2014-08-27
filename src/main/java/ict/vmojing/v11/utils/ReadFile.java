package ict.vmojing.v11.utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.io.*;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文件读写类
 * @author v11
 *
 */
public class ReadFile {

	/**
	 * 读取文件转化为hashMap
	 * @param fileName
	 * @return
	 */
	public static HashMap<String, Integer> readFileToMap(String fileName){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if(is == null) MyLog.logDebug("read file is null");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		try {
			while((line = reader.readLine()) != null){
				map.put(line.split(" ")[0], Integer.parseInt(line.split(" ")[1]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 读取文件转化为hashMap
	 * @param fileName
	 * @param weight 正负权重 1,or -1
	 * @return
	 */
	public static HashMap<String, Integer> readFileToMap(String fileName,Integer weight){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if(is == null) MyLog.logDebug("read file is null");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		try {
			while((line = reader.readLine()) != null){
				map.put(line.split(" ")[0], weight*Integer.parseInt(line.split(" ")[1]));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 读取文件，转化为List类型
	 * @param fileName
	 * @return
	 */
	public static List<String> readFileToList(String fileName){
		List<String> ls = new ArrayList<String>();
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
		if(is == null) MyLog.logDebug("read file is null");
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader reader = new BufferedReader(isr);
		String line = null;
		try {
			while((line = reader.readLine()) != null){
				ls.add(line);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ls;
	}

}
