package sample;

import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Mail implements Sample {
    
    private String subject;
    private Map<String, String> headers;
    private String body;
    
    
    public String getSubject() {
        return subject;
    }



    public Map<String, String> getHeaders() {
        return headers;
    }



    public String getBody() {
        return body;
    }



    public Mail(String subject, Map<String, String> headers, String body) {
        super();
        this.subject = subject;
        this.headers = headers;
        this.body = body;
    }



    public DBObject toMongo() {
        //headers
        BasicDBObject h = new BasicDBObject(headers);

        BasicDBObject obj = new BasicDBObject();
        obj.put("subject", this.subject);
        obj.put("body", body);
        obj.put("headers", h);

        return obj;
        
    }

}
