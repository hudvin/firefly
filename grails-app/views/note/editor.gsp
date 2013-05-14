<%--
  Created by IntelliJ IDEA.
  User: hudvin
  Date: 5/13/13
  Time: 8:47 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Note Editor</title>
    <meta name="layout" content="main"/>
    <g:javascript src="tiny_mce/tinymce.min.js"/>

</head>

<body>

<div class="row-fluid">
    <div class="span10" style="padding-top: 60px">

        <script type="text/javascript">
            tinyMCE.init({
                editor_selector: 'textarea',
                selector: "textarea",
                theme: "modern",
                plugins: [
                    "fullpage advlist save autolink lists link image charmap print preview hr anchor pagebreak",
                    "searchreplace wordcount visualblocks visualchars code fullscreen",
                    "insertdatetime media nonbreaking save table contextmenu directionality",
                    "emoticons template paste", "save"
                ],
                save_enablewhendirty: true,
                save_onsavecallback: function () {
                    var content = tinyMCE.activeEditor.getContent();

                    var saveData = $.ajax({
                        type: 'POST',
                        url: "${createLink(controller: 'note',action: 'save')}",
                        data: {text: content, "paperHandlerId": ${paperHandlerId}},
                        dataType: "text",
                        success: function (resultData) {
                            console.log("saved on server")
                        }
                    });


                },
                toolbar: "save undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",
                oninit: function () {

                    $.ajax({
                        url: "${createLink(controller: 'note', action: 'getContent', params: [paperHandlerId:paperHandlerId])}",
                        type: 'GET',
                        dataType: 'text',
                        success: function (data) {
                            tinyMCE.get('content').setContent(data);
                        }
                    });
                }
            });
        </script>

        <form method="post" action="somepage">
            <textarea name="content" style="width:100%;height: 100%"></textarea>
        </form>

    </div>
</div>

</body>
</html>