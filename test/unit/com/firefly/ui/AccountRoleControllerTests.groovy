package com.firefly.ui



import org.junit.*
import grails.test.mixin.*

@TestFor(AccountRoleController)
@Mock(AccountRole)
class AccountRoleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/accountRole/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.accountRoleInstanceList.size() == 0
        assert model.accountRoleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.accountRoleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.accountRoleInstance != null
        assert view == '/accountRole/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/accountRole/show/1'
        assert controller.flash.message != null
        assert AccountRole.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/accountRole/list'

        populateValidParams(params)
        def accountRole = new AccountRole(params)

        assert accountRole.save() != null

        params.id = accountRole.id

        def model = controller.show()

        assert model.accountRoleInstance == accountRole
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/accountRole/list'

        populateValidParams(params)
        def accountRole = new AccountRole(params)

        assert accountRole.save() != null

        params.id = accountRole.id

        def model = controller.edit()

        assert model.accountRoleInstance == accountRole
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/accountRole/list'

        response.reset()

        populateValidParams(params)
        def accountRole = new AccountRole(params)

        assert accountRole.save() != null

        // test invalid parameters in update
        params.id = accountRole.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/accountRole/edit"
        assert model.accountRoleInstance != null

        accountRole.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/accountRole/show/$accountRole.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        accountRole.clearErrors()

        populateValidParams(params)
        params.id = accountRole.id
        params.version = -1
        controller.update()

        assert view == "/accountRole/edit"
        assert model.accountRoleInstance != null
        assert model.accountRoleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/accountRole/list'

        response.reset()

        populateValidParams(params)
        def accountRole = new AccountRole(params)

        assert accountRole.save() != null
        assert AccountRole.count() == 1

        params.id = accountRole.id

        controller.delete()

        assert AccountRole.count() == 0
        assert AccountRole.get(accountRole.id) == null
        assert response.redirectedUrl == '/accountRole/list'
    }
}
