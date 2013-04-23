package com.firefly.ui



import org.junit.*
import grails.test.mixin.*

@TestFor(PaperController)
@Mock(Paper)
class PaperControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/paper/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.paperInstanceList.size() == 0
        assert model.paperInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.paperInstance != null
    }

    void testSave() {
        controller.save()

        assert model.paperInstance != null
        assert view == '/paper/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/paper/show/1'
        assert controller.flash.message != null
        assert Paper.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/paper/list'

        populateValidParams(params)
        def paper = new Paper(params)

        assert paper.save() != null

        params.id = paper.id

        def model = controller.show()

        assert model.paperInstance == paper
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/paper/list'

        populateValidParams(params)
        def paper = new Paper(params)

        assert paper.save() != null

        params.id = paper.id

        def model = controller.edit()

        assert model.paperInstance == paper
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/paper/list'

        response.reset()

        populateValidParams(params)
        def paper = new Paper(params)

        assert paper.save() != null

        // test invalid parameters in update
        params.id = paper.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/paper/edit"
        assert model.paperInstance != null

        paper.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/paper/show/$paper.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        paper.clearErrors()

        populateValidParams(params)
        params.id = paper.id
        params.version = -1
        controller.update()

        assert view == "/paper/edit"
        assert model.paperInstance != null
        assert model.paperInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/paper/list'

        response.reset()

        populateValidParams(params)
        def paper = new Paper(params)

        assert paper.save() != null
        assert Paper.count() == 1

        params.id = paper.id

        controller.delete()

        assert Paper.count() == 0
        assert Paper.get(paper.id) == null
        assert response.redirectedUrl == '/paper/list'
    }
}
