package com.firefly.ui

import firefly.FileService
import grails.converters.JSON
import org.springframework.web.multipart.MultipartHttpServletRequest

class UploaderController {

    FileService fileService



    def upload = {
        Collection result = []
        MultipartHttpServletRequest mpr = (MultipartHttpServletRequest) request;
        mpr.getFileNames().each {
            //return file handler
            def file = fileService.saveFile(mpr.getFile(it))

            result << [url: "http://localhost:8080/firefly/uploader/file?fileid=" + file.id, name: file.filename,
                    size: file.length, delete_url: "http://localhost:8080/firefly/delete",
                    delete_type: "DELETE"]
        }
        def responseData = [
                'files': result
        ]
        println(responseData)
        render responseData as JSON
    }

    def file = {
        def filename = params.fileid
        println filename
        def file = fileService.retrieveFile(filename)
        if (file != null) {
            response.contentType = file.getContentType()
            response.setHeader "Content-disposition", "attachment; filename=\"${filename}\"";
            response.outputStream << file.getInputStream()
        } else render "File not found"
    }


    def index() {}


    def addTag(){
        println(params)
        render(status: 200)

    }

}
