package crud;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import sample.Mail;
import sample.Sample;

import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class OperatorTest {

    Operator op;
    DBCollection collection;
    
    @Before
    public void setUp() throws Exception {
        //FIXME get this out of a properties file
        String host = "localhost";
        MongoClient mc = new MongoClient(host);
        collection = mc.getDB("blip").getCollection("msg");
        
        
        //ensure index on text
        BasicDBObject keys = new BasicDBObject("body", "text");
        collection.createIndex(keys);
        
        op = new Operator(collection);
    }

    @After
    public void tearDown() {
//        collection.drop();
    }
    
    public void testInsert() {
        Map<String, String> hs = new HashMap<String, String>();
        hs.put("To", "you");
        hs.put("From", "me");
        Mail sample = new Mail("some subject",hs, "some body");
        boolean expected = true;
        boolean actual = op.insert(sample);
        assertEquals(expected, actual);
        
    }
    
    @Test
    public void testDeleteBySubject(){
        String subject = "some subject";
        DBObject o = new BasicDBObject("subject", subject);
        collection.insert(o);
        assertTrue( op.deleteBySubject(subject) );
    }
    
    @Test
    public void testFindKeyword(){
        
        BasicDBObject[] objs = new BasicDBObject[3];
        objs[0] = new BasicDBObject("body", "some text to confuse");
        objs[1] = new BasicDBObject("body", "a bit more of confusing text");
        objs[2] = new BasicDBObject("body", "unique keyword to be found in text");
        
        BulkWriteOperation bulk = collection.initializeUnorderedBulkOperation();
        for ( BasicDBObject document : objs){
            bulk.insert(document);
        }
        bulk.execute();
        
        String keyword = "Sunday";
        
        List<Sample> c = op.findKeyword(keyword);
        
        assertTrue( c.size() == 1);
        
        
        
    }

}
