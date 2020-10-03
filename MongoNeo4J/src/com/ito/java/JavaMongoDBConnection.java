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
    	createLibraryRecordOnMongo(1,"Nagas", "Amish Tripati",new MongoClient("localhost"),"library");
    }
    

	public static void createLibraryRecordOnMongo(int id,String book,String author, MongoClient mongoClient, String database) {
    	
    	try {
		    	DBCollection bookCollection = addBook(id, book, mongoClient, database);
		    	DBCollection authorCollection = addAuthor(id, author, mongoClient, database);
		    	
		        DBObject query = new BasicDBObject("_id", id);
		        DBCursor cur = bookCollection.find(query);              
		        while(cur.hasNext()) {
		    	   DBObject dbo = cur.next();
		    	   System.out.println(dbo.get("name")==null ? "No NickName Specified" : dbo.get("name")+" document created");
		        }
		        cur = authorCollection.find(query);              
		        while(cur.hasNext()) {
		    	   DBObject dbo = cur.next();
		    	   System.out.println(dbo.get("name")==null ? "No NickName Specified" : dbo.get("name")+" document created");
		        }
		        
		        System.out.println("done");
		        
    	}catch(Exception e) {
    		System.out.println("Exception Caught");
    		e.printStackTrace();
    	}
		mongoClient.close();
    }
    
	@SuppressWarnings({ "unused", "deprecation" })
	private static DBCollection addBook(int id, String book, MongoClient mongoClient, String database) {
        DBObject newBook = new BasicDBObject("_id", id)
                                    .append("name", book)
                                    .append("year", "2020")
                                    .append("createdby", "java");
        
        DB db = mongoClient.getDB(database);
        DBCollection collection = db.getCollection("book");
        //collection.insert(newBook);
        collection.update(new BasicDBObject("name",book), newBook,true,false);
		return collection;
	}
	
	@SuppressWarnings({ "unused", "deprecation" })
	private static DBCollection addAuthor(int id, String author, MongoClient mongoClient, String database) {
        DBObject newAuthor = new BasicDBObject("_id", id)
                                    .append("name", author)                                  
                                    .append("year", "2020")
                                    .append("createdby", "java");
        
        DB db = mongoClient.getDB(database);
        DBCollection collection = db.getCollection("author");
        collection.update(new BasicDBObject("name",author), newAuthor,true,false);
        //collection.insert(newAuthor);
		return collection;
	}
}
