#!/usr/bin/env python
import sys
import httplib
from base64 import b64encode

json_template =  """{
    "file" : "%s"
}"""


attach_file  = sys.argv[1]

encoded = b64encode(open(attach_file, "rb").read())
data =  json_template % (encoded)
#print data
connection =  httplib.HTTPConnection('localhost:9200')
connection.request('POST', 'firefly/attachment/',data )
result = connection.getresponse()

print result.read()
print result.reason
