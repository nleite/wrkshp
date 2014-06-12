#!/bin/sh
DATAPATH=../dataset/enron.json
DB=enron
COLLECTION=messages
LIMIT=10000
/usr/local/bin/mongoexport -d $DB -c $COLLECTION -o $DATAPATH --limit $LIMIT
