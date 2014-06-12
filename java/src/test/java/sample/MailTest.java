package sample;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MailTest {
    
    Mail obj;
    String subject = "This is my subject";
    String body = " Oh yeah! a hell of a body, that's what it is!";
    Map<String, String> headers = new HashMap<String, String>();
    
    @Before
    public void setUp() throws Exception {
        headers.put("To", "hello@world.com");
        headers.put("From", "foo@bar.com");
        
        obj = new Mail(subject, headers, body);
    }

    @Test
    public void testMail() {
       assertNotNull(obj);
    }

    @Test
    public void testToMongo() {
        BasicDBObject expected = new BasicDBObject("subject", subject);
        expected.put("body", body);
        expected.put("headers", headers);
        
        DBObject actual = obj.toMongo();
        
        assertEquals(expected.get("subject"), actual.get("subject"));
        assertEquals(expected.get("body"), actual.get("body"));
        assertEquals(expected.get("headers"), actual.get("headers"));
        
        
        
    }

}
