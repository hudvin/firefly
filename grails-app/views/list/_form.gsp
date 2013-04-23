<%@ page import="com.firefly.ui.List" %>



<div class="fieldcontain ${hasErrors(bean: listInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="list.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${listInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: listInstance, field: 'description', 'error')} ">
	<label for="description">
		<g:message code="list.description.label" default="Description" />
		
	</label>
	<g:textField name="description" value="${listInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: listInstance, field: 'account', 'error')} required">
	<label for="account">
		<g:message code="list.account.label" default="Account" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="account" name="account.id" from="${com.firefly.ui.Account.list()}" optionKey="id" required="" value="${listInstance?.account?.id}" class="many-to-one"/>
</div>

