#!/bin/sh

mongod --dbpath ./db --smallfiles --noprealloc --fork --logpath log --port 25025 --logappend
