import com.mongodb.DB;
import com.mongodb.DBCollection;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

public class MongoDemo {

  public static void main(String[] args) {
    // open connection
    MongoClient mongoClient = new MongoClient("localhost", 27017);
    // get ref to database
    MongoDatabase db = mongoClient.getDatabase("MyDatabase");
    // get ref to collection
    MongoCollection<Document> myCollection = db.getCollection("myCollection");
    // create a new document

    Document doc = new Document("name", "MongoDB")
        .append("type", "database")
        .append("count", 1)
        .append("info", new Document("x", 203).append("y", 102));
    // insert document into collection
   // myCollection.insertOne(doc);
//
    // count all documents in collection
    System.out.println("Total Documents :" +  myCollection.count());

    // iterate some documents

//    List<Document> docs = myCollection.find().limit(100).into(new ArrayList<Document>());
//    for (Document object : docs){
//      System.out.println(object.getInteger("a"));
//      System.out.println(object.getString("b"));
//      System.out.println(object.getObjectId("_id"));// always unique per document
//    }
    // fetching a value from a search

    Document search = myCollection.find(eq("count", 1)).first();
    System.out.println(search.getObjectId("_id"));
    List<Document> results = myCollection.find(in("a",123,456)).into(new ArrayList<>());
    System.out.println(results.size());
//    // updating a value
    myCollection.updateOne(eq("a", 123F), new Document("$set", new Document("d", "new value")));
  }
}