#!/usr/bin/env python
import sys
import httplib
from base64 import b64encode

json_template =  """
{"fields":["title"],"query":{"query_string":{"query":"%s"}},"highlight":{"fields":{"file":{}}}}
"""


query_txt  = sys.argv[1]

data =  json_template % (query_txt)
connection =  httplib.HTTPConnection('localhost:9200')
connection.request('GET', '/_search?pretty=1',data )
result = connection.getresponse()

print result.read()
print result.reason
