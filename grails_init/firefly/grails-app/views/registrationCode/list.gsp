
<%@ page import="org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'registrationCode.label', default: 'RegistrationCode')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-registrationCode" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-registrationCode" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="dateCreated" title="${message(code: 'registrationCode.dateCreated.label', default: 'Date Created')}" />
					
						<g:sortableColumn property="token" title="${message(code: 'registrationCode.token.label', default: 'Token')}" />
					
						<g:sortableColumn property="username" title="${message(code: 'registrationCode.username.label', default: 'Username')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${registrationCodeInstanceList}" status="i" var="registrationCodeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${registrationCodeInstance.id}">${fieldValue(bean: registrationCodeInstance, field: "dateCreated")}</g:link></td>
					
						<td>${fieldValue(bean: registrationCodeInstance, field: "token")}</td>
					
						<td>${fieldValue(bean: registrationCodeInstance, field: "username")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${registrationCodeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
