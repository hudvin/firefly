package com.firefly.ui

import firefly.FileService
import firefly.TagsService
import grails.converters.JSON
import org.springframework.web.multipart.MultipartHttpServletRequest

class UploaderController {

    def FileService fileService
    def springSecurityService
    def TagsService tagsService
    def grailsLinkGenerator


    def upload = {
        Collection result = []
        MultipartHttpServletRequest mpr = (MultipartHttpServletRequest) request;
        def currentAccount = springSecurityService.currentUser.asType(Account)
        def tags = request.getParameterValues("tags")
        if (tags.length == 0) {
            tags << "new"
        }

        mpr.getFileNames().each {
            //return file handler
            def file = fileService.saveFile(mpr.getFile(it))
            //TODO migration on absolute link!!!

            def fileLink = grailsLinkGenerator.link(controller: 'uploader', action: 'file', params: ['fileid': file.id], absolute: true)
            def deleteLink = grailsLinkGenerator.link(controller: 'uploader', action: '/', params: ['fileid': file.id], absolute: true)
            result << [url: fileLink, name: file.filename,
                    size: file.length, delete_url: deleteLink,
                    delete_type: "DELETE"]

            def paper = Paper.findByGfsId(file.id.toString())
            def paperHandler = PaperHandler.findByAccountAndPaper(currentAccount, paper)

            tags.each {
                tagsService.addTag(paperHandler.id, it)
            }

        }


        def responseData = [
                'files': result
        ]
        log.info(responseData)
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


    def removeTag() {
        log.info(params)
        def paperHandlerId = params.paperHandlerId
        def tagLabel = params.tag
        tagsService.removeTag(paperHandlerId, tagLabel)
        render(status: 200)
    }

    def addTag() {
        log.info(params)
        def paperHandlerId = params.paperHandlerId
        def tagLabel = params.tag
        tagsService.addTag(paperHandlerId, tagLabel)
        render(status: 200)
    }


}
