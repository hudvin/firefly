<%--
  Created by IntelliJ IDEA.
  User: hudvin
  Date: 5/10/13
  Time: 10:21 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>list papers</title>
</head>

<body>

<g:each in="${paperHandler}">
    <p>Title: ${it.paper.filename}</p>
    <p>Tags: ${it.tags}</p>

    <g:link controller="pdfViewer" action="index" params="[fileid: it.paper.gfsId]" >View</g:link>
    <g:link controller="uploader" action="file" params="[fileid: it.paper.gfsId]" >Download</g:link>


</g:each>

</body>
</html>