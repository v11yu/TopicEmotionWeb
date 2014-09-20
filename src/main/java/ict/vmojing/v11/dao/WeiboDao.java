package ict.vmojing.v11.dao;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

import ict.vmojing.v11.model.Weibo;
import ict.vmojing.v11.utils.*;

public class WeiboDao extends BasicDao{
	public WeiboDao(){
		this.Collection = MongoDBManager.getUniqueMongoDBManager().getColl(MongoConfig.getValue("weibo"));
	}
	public WeiboDao(String dbname){

		this.Collection = MongoDBManager.getUniqueMongoDBManager().getColl(dbname);
	}
	public static List<Weibo> toList(DBCursor weiboCursor){
		List<Weibo> weibos = new ArrayList<Weibo>();
		while(weiboCursor.hasNext()){
			DBObject object = weiboCursor.next();
			Weibo weibo = new Weibo(object);
			weibos.add(weibo);
		}
		return weibos;
	}
	
}
