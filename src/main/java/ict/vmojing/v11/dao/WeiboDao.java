package ict.vmojing.v11.dao;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

import ict.vmojing.v11.utils.*;

public class WeiboDao extends BasicDao{
	public WeiboDao(){
		this.Collection = MongoDBManager.getUniqueMongoDBManager().getColl(MongoConfig.getValue("weibo"));
	}
	public WeiboDao(String dbname){

		this.Collection = MongoDBManager.getUniqueMongoDBManager().getColl(dbname);
	}
	
}
