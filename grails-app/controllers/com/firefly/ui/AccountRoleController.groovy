package com.firefly.ui

import org.springframework.dao.DataIntegrityViolationException

class AccountRoleController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [accountRoleInstanceList: AccountRole.list(params), accountRoleInstanceTotal: AccountRole.count()]
    }

    def create() {
        [accountRoleInstance: new AccountRole(params)]
    }

    def save() {
        def accountRoleInstance = new AccountRole(params)
        if (!accountRoleInstance.save(flush: true)) {
            render(view: "create", model: [accountRoleInstance: accountRoleInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'accountRole.label', default: 'AccountRole'), accountRoleInstance.id])
        redirect(action: "show", id: accountRoleInstance.id)
    }

    def show(Long id) {
        def accountRoleInstance = AccountRole.get(id)
        if (!accountRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'accountRole.label', default: 'AccountRole'), id])
            redirect(action: "list")
            return
        }

        [accountRoleInstance: accountRoleInstance]
    }

    def edit(Long id) {
        def accountRoleInstance = AccountRole.get(id)
        if (!accountRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'accountRole.label', default: 'AccountRole'), id])
            redirect(action: "list")
            return
        }

        [accountRoleInstance: accountRoleInstance]
    }

    def update(Long id, Long version) {
        def accountRoleInstance = AccountRole.get(id)
        if (!accountRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'accountRole.label', default: 'AccountRole'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (accountRoleInstance.version > version) {
                accountRoleInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'accountRole.label', default: 'AccountRole')] as Object[],
                          "Another user has updated this AccountRole while you were editing")
                render(view: "edit", model: [accountRoleInstance: accountRoleInstance])
                return
            }
        }

        accountRoleInstance.properties = params

        if (!accountRoleInstance.save(flush: true)) {
            render(view: "edit", model: [accountRoleInstance: accountRoleInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'accountRole.label', default: 'AccountRole'), accountRoleInstance.id])
        redirect(action: "show", id: accountRoleInstance.id)
    }

    def delete(Long id) {
        def accountRoleInstance = AccountRole.get(id)
        if (!accountRoleInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'accountRole.label', default: 'AccountRole'), id])
            redirect(action: "list")
            return
        }

        try {
            accountRoleInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'accountRole.label', default: 'AccountRole'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'accountRole.label', default: 'AccountRole'), id])
            redirect(action: "show", id: id)
        }
    }
}
