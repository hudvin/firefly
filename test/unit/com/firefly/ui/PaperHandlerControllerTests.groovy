package com.firefly.ui



import org.junit.*
import grails.test.mixin.*

@TestFor(PaperHandlerController)
@Mock(PaperHandler)
class PaperHandlerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/paperHandler/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.paperHandlerInstanceList.size() == 0
        assert model.paperHandlerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.paperHandlerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.paperHandlerInstance != null
        assert view == '/paperHandler/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/paperHandler/show/1'
        assert controller.flash.message != null
        assert PaperHandler.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/paperHandler/list'

        populateValidParams(params)
        def paperHandler = new PaperHandler(params)

        assert paperHandler.save() != null

        params.id = paperHandler.id

        def model = controller.show()

        assert model.paperHandlerInstance == paperHandler
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/paperHandler/list'

        populateValidParams(params)
        def paperHandler = new PaperHandler(params)

        assert paperHandler.save() != null

        params.id = paperHandler.id

        def model = controller.edit()

        assert model.paperHandlerInstance == paperHandler
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/paperHandler/list'

        response.reset()

        populateValidParams(params)
        def paperHandler = new PaperHandler(params)

        assert paperHandler.save() != null

        // test invalid parameters in update
        params.id = paperHandler.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/paperHandler/edit"
        assert model.paperHandlerInstance != null

        paperHandler.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/paperHandler/show/$paperHandler.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        paperHandler.clearErrors()

        populateValidParams(params)
        params.id = paperHandler.id
        params.version = -1
        controller.update()

        assert view == "/paperHandler/edit"
        assert model.paperHandlerInstance != null
        assert model.paperHandlerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/paperHandler/list'

        response.reset()

        populateValidParams(params)
        def paperHandler = new PaperHandler(params)

        assert paperHandler.save() != null
        assert PaperHandler.count() == 1

        params.id = paperHandler.id

        controller.delete()

        assert PaperHandler.count() == 0
        assert PaperHandler.get(paperHandler.id) == null
        assert response.redirectedUrl == '/paperHandler/list'
    }
}
