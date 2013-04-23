package ca.redtoad.eventlog

import org.springframework.dao.DataIntegrityViolationException

class SpringSecurityEventController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [springSecurityEventInstanceList: SpringSecurityEvent.list(params), springSecurityEventInstanceTotal: SpringSecurityEvent.count()]
    }

    def create() {
        [springSecurityEventInstance: new SpringSecurityEvent(params)]
    }

    def save() {
        def springSecurityEventInstance = new SpringSecurityEvent(params)
        if (!springSecurityEventInstance.save(flush: true)) {
            render(view: "create", model: [springSecurityEventInstance: springSecurityEventInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'springSecurityEvent.label', default: 'SpringSecurityEvent'), springSecurityEventInstance.id])
        redirect(action: "show", id: springSecurityEventInstance.id)
    }

    def show(Long id) {
        def springSecurityEventInstance = SpringSecurityEvent.get(id)
        if (!springSecurityEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'springSecurityEvent.label', default: 'SpringSecurityEvent'), id])
            redirect(action: "list")
            return
        }

        [springSecurityEventInstance: springSecurityEventInstance]
    }

    def edit(Long id) {
        def springSecurityEventInstance = SpringSecurityEvent.get(id)
        if (!springSecurityEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'springSecurityEvent.label', default: 'SpringSecurityEvent'), id])
            redirect(action: "list")
            return
        }

        [springSecurityEventInstance: springSecurityEventInstance]
    }

    def update(Long id, Long version) {
        def springSecurityEventInstance = SpringSecurityEvent.get(id)
        if (!springSecurityEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'springSecurityEvent.label', default: 'SpringSecurityEvent'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (springSecurityEventInstance.version > version) {
                springSecurityEventInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'springSecurityEvent.label', default: 'SpringSecurityEvent')] as Object[],
                          "Another user has updated this SpringSecurityEvent while you were editing")
                render(view: "edit", model: [springSecurityEventInstance: springSecurityEventInstance])
                return
            }
        }

        springSecurityEventInstance.properties = params

        if (!springSecurityEventInstance.save(flush: true)) {
            render(view: "edit", model: [springSecurityEventInstance: springSecurityEventInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'springSecurityEvent.label', default: 'SpringSecurityEvent'), springSecurityEventInstance.id])
        redirect(action: "show", id: springSecurityEventInstance.id)
    }

    def delete(Long id) {
        def springSecurityEventInstance = SpringSecurityEvent.get(id)
        if (!springSecurityEventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'springSecurityEvent.label', default: 'SpringSecurityEvent'), id])
            redirect(action: "list")
            return
        }

        try {
            springSecurityEventInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'springSecurityEvent.label', default: 'SpringSecurityEvent'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'springSecurityEvent.label', default: 'SpringSecurityEvent'), id])
            redirect(action: "show", id: id)
        }
    }
}
