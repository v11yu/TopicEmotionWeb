package ict.vmojing.v11.dao;

import java.util.ArrayList;
import java.util.List;




import org.bson.types.ObjectId;

import ict.vmojing.v11.utils.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

public class BasicDao {
protected DBCollection Collection;
	
	/**
	 * 查询全部记录
	 * @return DB iterator
	 */
	public DBCursor findByAll(){
		DBCursor cursor = Collection.find();
		
		return cursor;
	}
	/**
	 * 返回小于等于value信息
	 * @param valueName 字段名
	 * @param value 值
	 * @return 返回条件查询结果
	 */
	public DBCursor findByValueLessThanEquals(String valueName,Object value){
		DBObject query = QueryBuilder.start().put(valueName).lessThanEquals(value).get();
		DBCursor cursor = Collection.find(query);
		return cursor;
	}
	/**
	 * 返回大于等于value信息
	 * @param valueName 字段名
	 * @param value 值
	 * @return 返回条件查询结果
	 */
	public DBCursor findByValueGreaterThanEquals(String valueName,Object value){
		DBObject query = QueryBuilder.start().put(valueName).greaterThanEquals(value).get();
		DBCursor cursor = Collection.find(query);
		return cursor;
	}
	/**
	 * 获取相等
	 * @param valueName
	 * @param value
	 * @return
	 */
	public DBCursor findByValueEquals(String valueName,Object value){
		DBObject query = QueryBuilder.start().and(valueName).is(value).get();
		MyLog.logInfo(query.toString());
		DBCursor cursor = Collection.find(query);
		return cursor;
	}

	/**
	 * 保存记录
	 * @param obj  
	 */
	public void save(DBObject obj){
		Collection.save(obj);
	}
	/**
	 * 保存所以记录
	 * @param cursor
	 */
	public void saveAll(DBCursor cursor){
		while(cursor.hasNext()){
			DBObject obj = cursor.next();
			save(obj);
		}
	}
	/**
	 * 删除该表所有记录
	 */
	public void dropAll(){
		Collection.drop();
	}
	/**
	 * 更新数据
	 * @param obj完整的变动后的 obj
	 * @param keyName 变动的keyName
	 */
	public void update(DBObject obj,String keyName){
		DBObject query = new BasicDBObject("_id",obj.get("_id"));
        DBObject updateObj = new BasicDBObject(keyName,obj.get(keyName));
        Collection.update(query, new BasicDBObject("$set", updateObj));
		//Collection.update(query, updateObj);
	}
	public static void main(String[] args) {
		
	}
	
}
