<%@ page import="com.firefly.ui.Tag" %>



<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'createdBy', 'error')} required">
	<label for="createdBy">
		<g:message code="tag.createdBy.label" default="Created By" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="createdBy" name="createdBy.id" from="${com.firefly.ui.Account.list()}" optionKey="id" required="" value="${tagInstance?.createdBy?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tagInstance, field: 'label', 'error')} ">
	<label for="label">
		<g:message code="tag.label.label" default="Label" />
		
	</label>
	<g:textField name="label" value="${tagInstance?.label}"/>
</div>

