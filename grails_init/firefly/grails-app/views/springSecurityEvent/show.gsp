
<%@ page import="ca.redtoad.eventlog.SpringSecurityEvent" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'springSecurityEvent.label', default: 'SpringSecurityEvent')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-springSecurityEvent" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-springSecurityEvent" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list springSecurityEvent">
			
				<g:if test="${springSecurityEventInstance?.username}">
				<li class="fieldcontain">
					<span id="username-label" class="property-label"><g:message code="springSecurityEvent.username.label" default="Username" /></span>
					
						<span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${springSecurityEventInstance}" field="username"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${springSecurityEventInstance?.sessionId}">
				<li class="fieldcontain">
					<span id="sessionId-label" class="property-label"><g:message code="springSecurityEvent.sessionId.label" default="Session Id" /></span>
					
						<span class="property-value" aria-labelledby="sessionId-label"><g:fieldValue bean="${springSecurityEventInstance}" field="sessionId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${springSecurityEventInstance?.eventName}">
				<li class="fieldcontain">
					<span id="eventName-label" class="property-label"><g:message code="springSecurityEvent.eventName.label" default="Event Name" /></span>
					
						<span class="property-value" aria-labelledby="eventName-label"><g:fieldValue bean="${springSecurityEventInstance}" field="eventName"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${springSecurityEventInstance?.remoteAddress}">
				<li class="fieldcontain">
					<span id="remoteAddress-label" class="property-label"><g:message code="springSecurityEvent.remoteAddress.label" default="Remote Address" /></span>
					
						<span class="property-value" aria-labelledby="remoteAddress-label"><g:fieldValue bean="${springSecurityEventInstance}" field="remoteAddress"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${springSecurityEventInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="springSecurityEvent.dateCreated.label" default="Date Created" /></span>
					
						<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${springSecurityEventInstance?.dateCreated}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${springSecurityEventInstance?.id}" />
					<g:link class="edit" action="edit" id="${springSecurityEventInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
