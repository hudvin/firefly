package com.firefly.ui

import firefly.FileService
import grails.converters.JSON
import org.springframework.web.multipart.MultipartHttpServletRequest

class UploaderController {

    FileService fileService

    def springSecurityService


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


    def removeTag(){
        println(params)
        def paperHandlerId = params.paperHandlerId
        def tagLabel = params.tag
        def paperHandler = PaperHandler.get(paperHandlerId)
        def savedTag = Tag.findByLabel(tagLabel)
        paperHandler.removeFromTags(savedTag)
        paperHandler.save(failOnError: true)
        render(status: 200)
    }

    def addTag() {
        println(params)
        def paperHandlerId = params.paperHandlerId
        def tagLabel = params.tag
        def paperHandler = PaperHandler.get(paperHandlerId)
        def savedTag = Tag.findByLabel(tagLabel)
        if (savedTag == null) {
            def currentAccount = springSecurityService.currentUser.asType(Account)
            savedTag = new Tag(label: tagLabel, createdBy: currentAccount).save(failOnError: true)
        }
        if (!paperHandler.getTags().contains(savedTag)) {
            paperHandler.addToTags(savedTag)
            paperHandler.save(failOnError: true)
        }
        render(status: 200)
    }

}
