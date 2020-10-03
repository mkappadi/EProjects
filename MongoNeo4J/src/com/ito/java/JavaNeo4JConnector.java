package com.ito.java;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Result;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;
import static org.neo4j.driver.Values.parameters;

public class JavaNeo4JConnector implements AutoCloseable
{
    private final Driver driver;

    public JavaNeo4JConnector( String uri, String user, String password )
    {
        driver = GraphDatabase.driver( uri, AuthTokens.basic( user, password ) );
    }

    @Override
    public void close() throws Exception
    {
        driver.close();
    }

    public void insertNode( final String message )
    {
        try ( Session session = driver.session() )
        {
            String node = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    Result result = tx.run( "CREATE (a:author) " +
                                                     "SET a.name = $message " +
                                                     "RETURN a.name + ', from node ' + id(a)",
                            parameters( "message", message ) );
                    return result.single().get( 0 ).asString();
                }
            } );
            System.out.println( node );
        }
    }
    
    public void insertRelation( String sub, String obj, String relation )
    {
        try ( Session session = driver.session() )
        {
            String node = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    @SuppressWarnings("unused")
					Result result = tx.run( "MATCH(n:book{name:'"+ sub +"'}), (m:author{name:'"+ obj +"'}) "
                    								+ "MERGE (n)-["+relation+"]->(m) ",
                            parameters( "relation", relation ) );
                    return "Relationsip Created";
                }
            } );
            System.out.println( node );
        }
    }

	private void insertTriples(String book, String author, String abRelation, String baRelation) {
        try ( Session session = driver.session() )
        {
            String node = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    @SuppressWarnings("unused")
					Result result = tx.run( "MERGE (a:book {name:'"+book+"'}) " +     
											"MERGE (b:author {name:'"+author+"'}) " +  
											"MERGE (a)-["+abRelation+"]->(b) "  +
											"MERGE (b)-["+baRelation+"]->(a) " 
											);
                    return "Triples Created";
                }
            } );
            System.out.println( node );
        }
	}
	
	private void insertBookAuthorTriples(String book, String author, String relation) {
        try ( Session session = driver.session() )
        {
            String node = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    @SuppressWarnings("unused")
					Result result = tx.run( "MERGE (a:book {name:'"+book+"'}) " +     
											"MERGE (b:author {name:'"+author+"'}) " +  
											"MERGE (a)-["+relation+"]->(b) " 
											);
                    return "Triples Created";
                }
            } );
            System.out.println( node );
        }
	}
	
	private void insertAuthorBookTriples(String author, String book, String relation) {
        try ( Session session = driver.session() )
        {
            String node = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
                    @SuppressWarnings("unused")
					Result result = tx.run( "MERGE (a:author {name:'"+author+"'}) " + 
											"MERGE (b:book {name:'"+book+"'}) " +     
											"MERGE (a)-["+relation+"]->(b) "  
											);
                    return "Triples Created";
                }
            } );
            System.out.println( node );
        }
	}
	
	private boolean findBook(String book) {
        try ( Session session = driver.session() )
        {
            String node = session.writeTransaction( new TransactionWork<String>()
            {
                @Override
                public String execute( Transaction tx )
                {
					Result result = tx.run( "MATCH(a:book{name:'"+book+"'}) return a, a.name");
					
					return result!=null?"true":"false";
                }
            } );
            System.out.println( node );
            return new Boolean(node);
        }
	}
	
	
    public static void createLibraryRecordRelationOnNeo(String book, String author, String abRelation, boolean twoWay, String baRelation) throws Exception
    {
        try ( JavaNeo4JConnector neo = new JavaNeo4JConnector( "bolt://localhost:7687", "neo4j", "password" ) )
        {
            neo.insertTriples(book,author,abRelation,baRelation);
        }
    }
    
    public static void main(String[] args) {
    	try {
			createLibraryRecordRelationOnNeo("Nagas", "Amish Tripati", ":WRITTEN_BY", true, ":WROTE");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


}