
<%@ page import="ca.redtoad.eventlog.SpringSecurityEvent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'springSecurityEvent.label', default: 'SpringSecurityEvent')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-springSecurityEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-springSecurityEvent" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="username" title="${message(code: 'springSecurityEvent.username.label', default: 'Username')}" />
					
						<g:sortableColumn property="sessionId" title="${message(code: 'springSecurityEvent.sessionId.label', default: 'Session Id')}" />
					
						<g:sortableColumn property="eventName" title="${message(code: 'springSecurityEvent.eventName.label', default: 'Event Name')}" />
					
						<g:sortableColumn property="remoteAddress" title="${message(code: 'springSecurityEvent.remoteAddress.label', default: 'Remote Address')}" />
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'springSecurityEvent.dateCreated.label', default: 'Date Created')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${springSecurityEventInstanceList}" status="i" var="springSecurityEventInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${springSecurityEventInstance.id}">${fieldValue(bean: springSecurityEventInstance, field: "username")}</g:link></td>
					
						<td>${fieldValue(bean: springSecurityEventInstance, field: "sessionId")}</td>
					
						<td>${fieldValue(bean: springSecurityEventInstance, field: "eventName")}</td>
					
						<td>${fieldValue(bean: springSecurityEventInstance, field: "remoteAddress")}</td>
					
						<td><g:formatDate date="${springSecurityEventInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${springSecurityEventInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
