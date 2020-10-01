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
/**
 * @author Mohan Kappadi
 *
 */

public class JavaMongoDBConnection {
	 
    public static void main(String[] args) {
    	MongoClient mongoClient = new MongoClient("localhost");
    	try {
		    	DBCollection collection = addDocument(mongoClient);
		    	//DBCollection collection = mongoClient.getDB("Examples").getCollection("people");
		    	
		        DBObject query = new BasicDBObject("_id", "jo");
		        DBCursor cur = collection.find(query);
		                
		        while(cur.hasNext()) {
		    	   DBObject dbo = cur.next();
		    	   System.out.println(dbo.get("nickname")==null ? "No NickName Specified" : dbo.get("nickname"));
		        }
		        System.out.println("done");
    	}catch(Exception e) {
    		System.out.println("Exception Caught");
    		e.printStackTrace();
    	}
		mongoClient.close();
         
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
