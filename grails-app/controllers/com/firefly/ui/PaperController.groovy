package com.firefly.ui

import org.springframework.dao.DataIntegrityViolationException

class PaperController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [paperInstanceList: Paper.list(params), paperInstanceTotal: Paper.count()]
    }

    def create() {
        [paperInstance: new Paper(params)]
    }

    def save() {
        def paperInstance = new Paper(params)
        if (!paperInstance.save(flush: true)) {
            render(view: "create", model: [paperInstance: paperInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'paper.label', default: 'Paper'), paperInstance.id])
        redirect(action: "show", id: paperInstance.id)
    }

    def show(Long id) {
        def paperInstance = Paper.get(id)
        if (!paperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'paper.label', default: 'Paper'), id])
            redirect(action: "list")
            return
        }

        [paperInstance: paperInstance]
    }

    def edit(Long id) {
        def paperInstance = Paper.get(id)
        if (!paperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'paper.label', default: 'Paper'), id])
            redirect(action: "list")
            return
        }

        [paperInstance: paperInstance]
    }

    def update(Long id, Long version) {
        def paperInstance = Paper.get(id)
        if (!paperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'paper.label', default: 'Paper'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (paperInstance.version > version) {
                paperInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'paper.label', default: 'Paper')] as Object[],
                          "Another user has updated this Paper while you were editing")
                render(view: "edit", model: [paperInstance: paperInstance])
                return
            }
        }

        paperInstance.properties = params

        if (!paperInstance.save(flush: true)) {
            render(view: "edit", model: [paperInstance: paperInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'paper.label', default: 'Paper'), paperInstance.id])
        redirect(action: "show", id: paperInstance.id)
    }

    def delete(Long id) {
        def paperInstance = Paper.get(id)
        if (!paperInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'paper.label', default: 'Paper'), id])
            redirect(action: "list")
            return
        }

        try {
            paperInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'paper.label', default: 'Paper'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'paper.label', default: 'Paper'), id])
            redirect(action: "show", id: id)
        }
    }
}
