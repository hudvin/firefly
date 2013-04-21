#!/usr/bin/env python
import sys
import httplib

connection =  httplib.HTTPConnection('localhost:9200')
connection.request('DELETE', '/firefly', "")
result = connection.getresponse()

print result.read()
print result.reason
