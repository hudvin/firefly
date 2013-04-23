
<%@ page import="com.firefly.ui.List" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'list.label', default: 'List')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-list" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-list" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="name" title="${message(code: 'list.name.label', default: 'Name')}" />
					
						<g:sortableColumn property="description" title="${message(code: 'list.description.label', default: 'Description')}" />
					
						<th><g:message code="list.account.label" default="Account" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${listInstanceList}" status="i" var="listInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${listInstance.id}">${fieldValue(bean: listInstance, field: "name")}</g:link></td>
					
						<td>${fieldValue(bean: listInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: listInstance, field: "account")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${listInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
