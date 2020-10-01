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
    	MongoClientURI uri = new MongoClientURI("mongodb+srv://mdbuser1:mdbuser1@mongouscluster-jxryg.mongodb.net/test?retryWrites=true&w=majority&connectTimeoutMS=30000&socketTimeoutMS=30000");
    	try (MongoClient mongoClient = new MongoClient(uri)){
		    	//DBCollection collection = addDocument(mongoClient);
		    	DBCollection collection = mongoClient.getDB("Examples").getCollection("people");
		    	
		        DBObject query = new BasicDBObject("_id", "do");
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
        List<Integer> books = Arrays.asList(64672, 548747);
        DBObject person = new BasicDBObject("_id", "do")
                                    .append("name", "Do jocks")
                                    .append("address", new BasicDBObject("street", "987 Fake St")
                                                                 .append("city", "Faketon")
                                                                 .append("state", "AM")
                                                                 .append("zip", 67890))
                                    .append("books", books)
                                    .append("nickname", "dj");
        
        //MongoClient mongoClient = new MongoClient();
        DB database = mongoClient.getDB("Examples");
        DBCollection collection = database.getCollection("people");
        collection.insert(person);
		return collection;
	}
}
