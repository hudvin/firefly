
<%@ page import="com.firefly.ui.Paper" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'paper.label', default: 'Paper')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-paper" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-paper" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list paper">
			
				<g:if test="${paperInstance?.gfsId}">
				<li class="fieldcontain">
					<span id="gfsId-label" class="property-label"><g:message code="paper.gfsId.label" default="Gfs Id" /></span>
					
						<span class="property-value" aria-labelledby="gfsId-label"><g:fieldValue bean="${paperInstance}" field="gfsId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${paperInstance?.filename}">
				<li class="fieldcontain">
					<span id="filename-label" class="property-label"><g:message code="paper.filename.label" default="Filename" /></span>
					
						<span class="property-value" aria-labelledby="filename-label"><g:fieldValue bean="${paperInstance}" field="filename"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${paperInstance?.filesize}">
				<li class="fieldcontain">
					<span id="filesize-label" class="property-label"><g:message code="paper.filesize.label" default="Filesize" /></span>
					
						<span class="property-value" aria-labelledby="filesize-label"><g:fieldValue bean="${paperInstance}" field="filesize"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${paperInstance?.id}" />
					<g:link class="edit" action="edit" id="${paperInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
