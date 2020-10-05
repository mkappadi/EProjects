package com.ito.java;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Result;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

public class MongoNeoAPOC implements AutoCloseable
{
    private final Driver driver;

    public MongoNeoAPOC( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }
    
    @Override
    public void close() throws Exception
    {
        driver.close();
    }
    
	public static void main(String[] args) throws Exception {
		createLibraryRecordRelationViaAPOC("Nagas", "Amish Tripati", ":WRITTEN_BY", ":WROTE");
	}
	
    public static void createLibraryRecordRelationViaAPOC(String book, String author, String abRelation, String baRelation) throws Exception
    {
        try ( MongoNeoAPOC apoc = new MongoNeoAPOC( "bolt://localhost:7687", "neo4j", "password" ) )
        {
            apoc.insertNodesAndRelation(book,author,abRelation,baRelation);
        }
    }
	
	@SuppressWarnings("unused")
	public void insertNodesAndRelation(String book, String author, String abRelation, String baRelation) {
        try ( Session session = driver.session() )
        {
            String node = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    @SuppressWarnings("unused")
					Result result = tx.run( "CALL apoc.graph.fromDocument(\""
											+ "{'id': 1,"
											+ "'type': 'book',"
											+ "'name':'"+book+"',"
											+ "'"+baRelation+"': ["
												  + "{'type': 'author',"
												  + "'id': 1,"
												  + "'name': '"+author+"'}"
												  + "]"
												  + "}\","
											+ "{write:true,"
											+ "idField:\"name\"}"
											+ ")" 
											);
		                    result = tx.run( "CALL apoc.graph.fromDocument(\""
									+ "{'id': 1,"
									+ "'type': 'author',"
									+ "'name':'"+author+"',"
									+ "'"+abRelation+"': ["
										  + "{'type': 'book',"
										  + "'id': 1,"
										  + "'name': '"+book+"'}"
										  + "]"
										  + "}\","
									+ "{write:true,"
									+ "idField:\"name\"}"
									+ ")" 
									);
                    return "Triples Created Via APOC";
                }
            } );
            System.out.println( node );
        }
	}

}
