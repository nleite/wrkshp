
def insert(mail, collection, wconcern=1):
    print(collection.insert(mail, w=wconcern))
