
<%@ page import="org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'registrationCode.label', default: 'RegistrationCode')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-registrationCode" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-registrationCode" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list registrationCode">
			
				<g:if test="${registrationCodeInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="registrationCode.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${registrationCodeInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${registrationCodeInstance?.token}">
				<li class="fieldcontain">
					<span id="token-label" class="property-label"><g:message code="registrationCode.token.label" default="Token" /></span>
					
						<span class="property-value" aria-labelledby="token-label"><g:fieldValue bean="${registrationCodeInstance}" field="token"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${registrationCodeInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="registrationCode.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${registrationCodeInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${registrationCodeInstance?.id}" />
					<g:link class="edit" action="edit" id="${registrationCodeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
