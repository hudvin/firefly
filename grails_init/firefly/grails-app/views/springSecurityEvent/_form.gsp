<%@ page import="ca.redtoad.eventlog.SpringSecurityEvent" %>



<div class="fieldcontain ${hasErrors(bean: springSecurityEventInstance, field: 'username', 'error')} ">
	<label for="username">
		<g:message code="springSecurityEvent.username.label" default="Username" />
		
	</label>
	<g:textField name="username" value="${springSecurityEventInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: springSecurityEventInstance, field: 'sessionId', 'error')} ">
	<label for="sessionId">
		<g:message code="springSecurityEvent.sessionId.label" default="Session Id" />
		
	</label>
	<g:textField name="sessionId" value="${springSecurityEventInstance?.sessionId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: springSecurityEventInstance, field: 'eventName', 'error')} ">
	<label for="eventName">
		<g:message code="springSecurityEvent.eventName.label" default="Event Name" />
		
	</label>
	<g:textField name="eventName" value="${springSecurityEventInstance?.eventName}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: springSecurityEventInstance, field: 'remoteAddress', 'error')} ">
	<label for="remoteAddress">
		<g:message code="springSecurityEvent.remoteAddress.label" default="Remote Address" />
		
	</label>
	<g:textField name="remoteAddress" value="${springSecurityEventInstance?.remoteAddress}"/>
</div>

