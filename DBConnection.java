

import java.net.UnknownHostException;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


public class DBConnection {
    private static MongoClient mongoClient  = null;
    private static MongoDatabase db = null;
    private static String dbName="squirrel";  //default value

    public static void setDBName(String db) {
    	dbName = db;
    }
    static MongoDatabase getDB() throws UnknownHostException{
        if (mongoClient == null) {
        	mongoClient = MongoClients.create();
        }       
        if (db == null) {
        	System.err.println("getting database" + dbName);
    	   db = mongoClient.getDatabase(dbName);
        }
       return db;
    }
    
    static void close() {
    	mongoClient.close();
    }
}
