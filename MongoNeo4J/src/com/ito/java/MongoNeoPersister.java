/**
 * 
 */
package com.ito.java;

import com.mongodb.MongoClient;

/**
 * @author Mohan Kappadi
 *
 */
public class MongoNeoPersister {
	private static String book;
	private static String author;
	private static int id;
	private static String database;
	private static MongoClient mongoClient ;
	private static String abRelation;
	private static String baRelation;
	private static boolean twoWay;
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		init();
		JavaMongoDBConnection.createLibraryRecordOnMongo(id, book, author, mongoClient, database);
		JavaNeo4JConnector.createLibraryRecordRelationOnNeo(book, author, abRelation, twoWay, baRelation);
	}

    private static void init() {
    	book = "abc";
    	author = "mohan";
    	id =1;
    	database = "library";
    	mongoClient = new MongoClient("localhost");
    	abRelation = ":HAS_WRITTEN";
    	baRelation = ":WRITTEN_BY";
    	twoWay = true;
	}
}
