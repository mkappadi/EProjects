/**
 * 
 */
package com.ito.java;

import java.util.Arrays;
import java.util.List;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
/**
 * @author Mohan Kappadi
 *
 */

public class MongoDBAtlasClient {
	 
    public static void main(String[] args) {
    	MongoClientURI uri = new MongoClientURI("mongodb+srv://mdbuser1:mdbuser1@mongouscluster-jxryg.mongodb.net/poc?retryWrites=true&w=majority&isSocketKeepAlive=true&connectTimeoutMS=30000&socketTimeoutMS=30000");
    	try (MongoClient mongoClient = new MongoClient(uri)){
		    	DBCollection collection = addDocument(mongoClient);
		    	//DBCollection collection = mongoClient.getDB("POC").getCollection("Books");
		    	
		        DBObject query = new BasicDBObject("_id", "1");
		        DBCursor cur = collection.find(query);
		        System.out.println("ResultSet size = "+cur.size());
		        while(cur.hasNext()) {
		    	   DBObject dbo = cur.next();
		    	   System.out.println(dbo.get("nickname")==null ? "No NickName Specified" : dbo.get("nickname"));
		        }
		        System.out.println("done");
    	}         
    }

	@SuppressWarnings({ "unused", "deprecation" })
	private static DBCollection addDocument(MongoClient mongoClient) {
		// TODO Auto-generated method stub
        //List<Integer> books = Arrays.asList(64672, 548747);
        DBObject book = new BasicDBObject("_id", "1")
                                    .append("name", "Monk Who Sold His Ferrari")
                                    .append("year", "2010")
                                    .append("nickname", "MWSHF");
        
        //MongoClient mongoClient = new MongoClient();
        DB database = mongoClient.getDB("poc");
        DBCollection collection = database.getCollection("books");
        collection.insert(book);
		return collection;
	}
}
