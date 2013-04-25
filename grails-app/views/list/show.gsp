
<%@ page import="com.firefly.ui.List" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'list.label', default: 'List')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-list" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-list" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list list">
			
				<g:if test="${listInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="list.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${listInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${listInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="list.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${listInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${listInstance?.account}">
				<li class="fieldcontain">
					<span id="account-label" class="property-label"><g:message code="list.account.label" default="Account" /></span>
					
						<span class="property-value" aria-labelledby="account-label"><g:link controller="account" action="show" id="${listInstance?.account?.id}">${listInstance?.account?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${listInstance?.lists}">
				<li class="fieldcontain">
					<span id="lists-label" class="property-label"><g:message code="list.lists.label" default="Lists" /></span>
					
						<g:each in="${listInstance.lists}" var="l">
						<span class="property-value" aria-labelledby="lists-label"><g:link controller="paper" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${listInstance?.id}" />
					<g:link class="edit" action="edit" id="${listInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
