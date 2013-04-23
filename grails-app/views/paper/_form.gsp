<%@ page import="com.firefly.ui.Paper" %>



<div class="fieldcontain ${hasErrors(bean: paperInstance, field: 'gfsId', 'error')} required">
	<label for="gfsId">
		<g:message code="paper.gfsId.label" default="Gfs Id" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="gfsId" maxlength="24" required="" value="${paperInstance?.gfsId}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: paperInstance, field: 'filename', 'error')} required">
	<label for="filename">
		<g:message code="paper.filename.label" default="Filename" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="filename" required="" value="${paperInstance?.filename}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: paperInstance, field: 'filesize', 'error')} required">
	<label for="filesize">
		<g:message code="paper.filesize.label" default="Filesize" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="filesize" type="number" min="0" value="${paperInstance.filesize}" required=""/>
</div>

