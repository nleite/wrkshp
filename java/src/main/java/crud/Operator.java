package crud;

import java.util.ArrayList;
import java.util.List;

import sample.Sample;
import sample.SampleParser;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.mongodb.WriteConcern;
import com.mongodb.WriteResult;

public class Operator {
    
    
    private DBCollection collection;
    private WriteConcern concern = WriteConcern.JOURNAL_SAFE;
    
    public DBCollection getCollection() {
        return collection;
    }

    public Operator(DBCollection collection) {
        this.collection = collection;
    }
    
    public boolean insert( Sample sample ){
        WriteResult res = this.collection.insert(sample.toMongo(), concern);
        
        boolean ok = (res.getN() == 1);
        
        if(!ok){
            System.out.println("NOT ok: " + res); 
        }
        
        return ok;
    }
    
    
    
    public boolean deleteBySubject(final String subject){
        BasicDBObject query = new BasicDBObject("subject", subject );
        WriteResult res = collection.remove(query);
        
        return res.getN() > 0;
    }
    
    public List<Sample> findKeyword(final String keyword){
        DBObject query = QueryBuilder.start().text(keyword).get();
        DBCursor c = collection.find(query);
        
        System.out.println(query);
        
        List<Sample> samples = new ArrayList<Sample>();
        for ( DBObject o : c){
            System.out.println(  o );
        }
        
        return samples;
        
    }
    

}
