
<%@ page import="com.firefly.ui.PaperHandler" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'paperHandler.label', default: 'PaperHandler')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-paperHandler" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-paperHandler" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<th><g:message code="paperHandler.account.label" default="Account" /></th>
					
						<th><g:message code="paperHandler.paper.label" default="Paper" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${paperHandlerInstanceList}" status="i" var="paperHandlerInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${paperHandlerInstance.id}">${fieldValue(bean: paperHandlerInstance, field: "account")}</g:link></td>
					
						<td>${fieldValue(bean: paperHandlerInstance, field: "paper")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${paperHandlerInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
