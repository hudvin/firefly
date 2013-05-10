package com.firefly.ui

import org.springframework.dao.DataIntegrityViolationException

class PaperHandlerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [paperHandlerInstanceList: PaperHandler.list(params), paperHandlerInstanceTotal: PaperHandler.count()]
    }

    def create() {
        [paperHandlerInstance: new PaperHandler(params)]
    }

    def save() {
        def paperHandlerInstance = new PaperHandler(params)
        if (!paperHandlerInstance.save(flush: true)) {
            render(view: "create", model: [paperHandlerInstance: paperHandlerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'paperHandler.label', default: 'PaperHandler'), paperHandlerInstance.id])
        redirect(action: "show", id: paperHandlerInstance.id)
    }

    def show(Long id) {
        def paperHandlerInstance = PaperHandler.get(id)
        if (!paperHandlerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'paperHandler.label', default: 'PaperHandler'), id])
            redirect(action: "list")
            return
        }

        [paperHandlerInstance: paperHandlerInstance]
    }

    def edit(Long id) {
        def paperHandlerInstance = PaperHandler.get(id)
        if (!paperHandlerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'paperHandler.label', default: 'PaperHandler'), id])
            redirect(action: "list")
            return
        }

        [paperHandlerInstance: paperHandlerInstance]
    }

    def update(Long id, Long version) {
        def paperHandlerInstance = PaperHandler.get(id)
        if (!paperHandlerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'paperHandler.label', default: 'PaperHandler'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (paperHandlerInstance.version > version) {
                paperHandlerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'paperHandler.label', default: 'PaperHandler')] as Object[],
                          "Another user has updated this PaperHandler while you were editing")
                render(view: "edit", model: [paperHandlerInstance: paperHandlerInstance])
                return
            }
        }

        paperHandlerInstance.properties = params

        if (!paperHandlerInstance.save(flush: true)) {
            render(view: "edit", model: [paperHandlerInstance: paperHandlerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'paperHandler.label', default: 'PaperHandler'), paperHandlerInstance.id])
        redirect(action: "show", id: paperHandlerInstance.id)
    }

    def delete(Long id) {
        def paperHandlerInstance = PaperHandler.get(id)
        if (!paperHandlerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'paperHandler.label', default: 'PaperHandler'), id])
            redirect(action: "list")
            return
        }

        try {
            paperHandlerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'paperHandler.label', default: 'PaperHandler'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'paperHandler.label', default: 'PaperHandler'), id])
            redirect(action: "show", id: id)
        }
    }
}
