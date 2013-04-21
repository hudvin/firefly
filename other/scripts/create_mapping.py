#!/usr/bin/env python
import sys
import httplib
json_file = open('create_mapping.json')
lines = json_file.read()
json_file.close()

connection =  httplib.HTTPConnection('localhost:9200')
print lines
connection.request('PUT', '/firefly/attachment/_mapping', lines)
result = connection.getresponse()

print result.read()
print result.reason
