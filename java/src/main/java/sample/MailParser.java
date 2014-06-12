package sample;

import com.mongodb.DBObject;

public class MailParser implements SampleParser<DBObject, Mail> {

    public DBObject parse(Mail o) {
        DBObject parsed = o.toMongo();
        return parsed;
    }

    public Mail render(DBObject o) {
        // TODO Auto-generated method stub
        return null;
    }

}
