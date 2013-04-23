
<%@ page import="com.firefly.ui.Paper" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'paper.label', default: 'Paper')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-paper" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-paper" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="gfsId" title="${message(code: 'paper.gfsId.label', default: 'Gfs Id')}" />
					
						<g:sortableColumn property="filename" title="${message(code: 'paper.filename.label', default: 'Filename')}" />
					
						<g:sortableColumn property="filesize" title="${message(code: 'paper.filesize.label', default: 'Filesize')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${paperInstanceList}" status="i" var="paperInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${paperInstance.id}">${fieldValue(bean: paperInstance, field: "gfsId")}</g:link></td>
					
						<td>${fieldValue(bean: paperInstance, field: "filename")}</td>
					
						<td>${fieldValue(bean: paperInstance, field: "filesize")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${paperInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
