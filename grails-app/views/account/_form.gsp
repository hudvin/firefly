<%@ page import="com.firefly.ui.Account" %>



<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="account.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${accountInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="account.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${accountInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="account.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${accountInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="account.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${accountInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="account.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${accountInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'paperHandler', 'error')} ">
	<label for="paperHandler">
		<g:message code="account.paperHandler.label" default="Paper Handler" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${accountInstance?.paperHandler?}" var="p">
    <li><g:link controller="paperHandler" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="paperHandler" action="create" params="['account.id': accountInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'paperHandler.label', default: 'PaperHandler')])}</g:link>
</li>
</ul>

</div>

<div class="fieldcontain ${hasErrors(bean: accountInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="account.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${accountInstance?.passwordExpired}" />
</div>

