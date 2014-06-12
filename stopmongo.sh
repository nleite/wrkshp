#!/bin/sh
mongo admin --port 25025 --quiet --eval 'printjson(db.shutdownServer())'
