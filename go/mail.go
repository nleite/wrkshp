package main

import(
    "fmt"
    "time"
    "flag"
    "labix.org/v2/mgo"
    "labix.org/v2/mgo/bson"
)

type Operator struct{
    col *mgo.Collection
}

func NewOperator( col *mgo.Collection ) (*Operator){
    o := Operator{col}
    return &o
}

/*
func (o *Operator) FindKeywordScore(keyword string) {
    query := bson.M{"$text": bson.M{"$search": keyword}}
    meta := bson.M{"score": bson.M{"$meta": "textScore"}}
    fields := bson.M{"score": bson.M{"$meta": "textScore"}}
    iter := o.col.Find(query).Select(fields).SortByDocument(meta).Iter()

    res := struct{Score float64}{}
    for iter.Next(&res){
        fmt.Println( res.Score )
    }
}
*/

func (o *Operator) FindKeyword(keyword string) {
    fmt.Println(keyword)
    query := bson.M{"$text": bson.M{"$search": keyword}}
    iter := o.col.Find(query).Iter()

    res := struct{Body string}{}
    for iter.Next(&res){
        fmt.Println( res.Body )
    }
}


func main(){
    host := flag.String("host", "nair.local", "mongodb host name")  // "nair.local"
    dbname := flag.String("db", "enron", "database name")//"enron"
    colname := flag.String("col", "messages", "collection name")//"messages"
    keyword := flag.String("kw", "Sunday", "keyword to search")//"Sunday"
    flag.Parse()
    timeout := time.Duration(1) * time.Second
    session, _ := mgo.DialWithTimeout(*host, timeout )
    operator := NewOperator(session.DB(*dbname).C(*colname))
    operator.FindKeyword( *keyword )
}


