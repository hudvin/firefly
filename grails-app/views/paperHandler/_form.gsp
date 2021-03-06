<%@ page import="com.firefly.ui.PaperHandler" %>



<div class="fieldcontain ${hasErrors(bean: paperHandlerInstance, field: 'note', 'error')} ">
	<label for="note">
		<g:message code="paperHandler.note.label" default="Note" />
		
	</label>
	<g:textArea name="note" cols="40" rows="5" value="${paperHandlerInstance?.note}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: paperHandlerInstance, field: 'account', 'error')} required">
	<label for="account">
		<g:message code="paperHandler.account.label" default="Account" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="account" name="account.id" from="${com.firefly.ui.Account.list()}" optionKey="id" required="" value="${paperHandlerInstance?.account?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: paperHandlerInstance, field: 'paper', 'error')} required">
	<label for="paper">
		<g:message code="paperHandler.paper.label" default="Paper" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="paper" name="paper.id" from="${com.firefly.ui.Paper.list()}" optionKey="id" required="" value="${paperHandlerInstance?.paper?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: paperHandlerInstance, field: 'tags', 'error')} ">
	<label for="tags">
		<g:message code="paperHandler.tags.label" default="Tags" />
		
	</label>
	<g:select name="tags" from="${com.firefly.ui.Tag.list()}" multiple="multiple" optionKey="id" size="5" value="${paperHandlerInstance?.tags*.id}" class="many-to-many"/>
</div>

