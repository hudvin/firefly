
<%@ page import="com.firefly.ui.PaperHandler" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'paperHandler.label', default: 'PaperHandler')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-paperHandler" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-paperHandler" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list paperHandler">
			
				<g:if test="${paperHandlerInstance?.account}">
				<li class="fieldcontain">
					<span id="account-label" class="property-label"><g:message code="paperHandler.account.label" default="Account" /></span>
					
						<span class="property-value" aria-labelledby="account-label"><g:link controller="account" action="show" id="${paperHandlerInstance?.account?.id}">${paperHandlerInstance?.account?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${paperHandlerInstance?.note}">
				<li class="fieldcontain">
					<span id="note-label" class="property-label"><g:message code="paperHandler.note.label" default="Note" /></span>
					
						<span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${paperHandlerInstance}" field="note"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${paperHandlerInstance?.paper}">
				<li class="fieldcontain">
					<span id="paper-label" class="property-label"><g:message code="paperHandler.paper.label" default="Paper" /></span>
					
						<span class="property-value" aria-labelledby="paper-label"><g:link controller="paper" action="show" id="${paperHandlerInstance?.paper?.id}">${paperHandlerInstance?.paper?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${paperHandlerInstance?.tags}">
				<li class="fieldcontain">
					<span id="tags-label" class="property-label"><g:message code="paperHandler.tags.label" default="Tags" /></span>
					
						<g:each in="${paperHandlerInstance.tags}" var="t">
						<span class="property-value" aria-labelledby="tags-label"><g:link controller="tag" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${paperHandlerInstance?.id}" />
					<g:link class="edit" action="edit" id="${paperHandlerInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
