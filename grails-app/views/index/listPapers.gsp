<%--
  Created by IntelliJ IDEA.
  User: hudvin
  Date: 5/10/13
  Time: 10:21 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>list papers</title>

</head>

<body>

<div class="row-fluid">
    <div class="span10" style="padding-top: 60px">
        <g:each in="${paperHandler}">
            <hr/>
            <div id="wrapper">
                      <div id="content">
                <form>
                <ul type="tag_input" id="${it.id}">
            <g:each var="tag" in="${it.tags}">
                <li>${tag.label}</li>
            </g:each>
            </ul>
         </form>


</div>

            <span class="label label-success">Filename:</span>

            <p class="label">${it.paper.filename}</p>
            </br>

            <span class="label label-success">size:</span>

            <p class="label">${formatNumber(number: it.paper.filesize / (1024 * 1024), maxFractionDigits: 2)} MB</p>
            </br>


            <span class="label label-success">Tags:</span>

            <p class="label">${it.tags}</p>

            <br/>
            <a href="${createLink(controller: 'uploader', action: 'file', params: [fileid: it.paper.gfsId])}"
               class="btn btn-small">Download</a>
            <a href="${createLink(controller: 'pdfViewer', action: 'index', params: [fileid: it.paper.gfsId])}"
               class="btn btn-small">View</a>


            <a href="${createLink(controller: 'note', action: 'editor', params: [paperHandlerId: it.id])}"
               class="btn btn-small"> ${it.note.isEmpty() ? 'Note(empty)':"Note"}</a>

        </g:each>

    </div>
    </div>
   </div>
    <script>


        function iterate(action) {
            var ulList = document.getElementsByTagName('ul');
            for (var i = 0; i < ulList.length; i++) {
                var ulElem = ulList[i];
                if (ulElem.getAttribute('type', 0) == "tag_input") {
                    action(ulElem)

                }
            }
        }

        iterate(function(ulElem){
            $('#' + ulElem.id).tagit({
                allowSpaces: true
            });
        })


        iterate(function(ulElem){
            $('#' + ulElem.id).tagit({
                allowSpaces: true,
                beforeTagRemoved: function (event, ui) {
                    var tag = ui.tagLabel;
                    //sent it to server
                    // do something special
                    console.log(ui.tagLabel);
                    var saveData = $.ajax({
                        type: 'GET',
                        url: "${createLink(controller: 'uploader',action: 'removeTag')}",
                        data: {tag: tag, "paperHandlerId": ulElem.id},
                        dataType: "text",
                        success: function (resultData) {
                            console.log("tag removed")
                        }
                    });
                },
                beforeTagAdded: function (event, ui) {
                    var tag = ui.tagLabel;
                    //sent it to server
                    // do something special
                    console.log(ui.tagLabel);
                    var saveData = $.ajax({
                        type: 'POST',
                        url: "${createLink(controller: 'uploader',action: 'addTag')}",
                        data: {tag: tag, "paperHandlerId": ulElem.id},
                        dataType: "text",
                        success: function (resultData) {
                            console.log("Save Complete")
                        }
                    });
                }
            });
        })


    </script>

    </body>
</html>