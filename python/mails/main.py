#!/usr/bin/python
import click
from pymongo import MongoClient
from operations import insert


@click.command()
@click.option('--host', default='localhost', help='mongodb server hostname')
@click.option('--dbname', default='blip', help='database name')
@click.option('--filename', default='localhost', help='emails file to be parsed')
def main(host, dbname, filename):
    col = MongoClient(host)[dbname]['msg']
    #let's parse the received file and call a insert operation


    query = { "$text": {"$search": "Sunday"} }

    cur = col.find(query)
    for c in cur:
        print(c)



if __name__ == '__main__':
    main()

