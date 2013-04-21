#!/usr/bin/env python
import sys
import httplib

json = """
{
  "settings" : { "index" : { "number_of_shards" : 1, "number_of_replicas" : 0 }}
}'
"""

connection =  httplib.HTTPConnection('localhost:9200')
print json
connection.request('PUT', '/firefly/', json)
result = connection.getresponse()

print result.read()
print result.reason
