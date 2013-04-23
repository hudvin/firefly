package ca.redtoad.eventlog



import org.junit.*
import grails.test.mixin.*

@TestFor(SpringSecurityEventController)
@Mock(SpringSecurityEvent)
class SpringSecurityEventControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/springSecurityEvent/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.springSecurityEventInstanceList.size() == 0
        assert model.springSecurityEventInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.springSecurityEventInstance != null
    }

    void testSave() {
        controller.save()

        assert model.springSecurityEventInstance != null
        assert view == '/springSecurityEvent/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/springSecurityEvent/show/1'
        assert controller.flash.message != null
        assert SpringSecurityEvent.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/springSecurityEvent/list'

        populateValidParams(params)
        def springSecurityEvent = new SpringSecurityEvent(params)

        assert springSecurityEvent.save() != null

        params.id = springSecurityEvent.id

        def model = controller.show()

        assert model.springSecurityEventInstance == springSecurityEvent
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/springSecurityEvent/list'

        populateValidParams(params)
        def springSecurityEvent = new SpringSecurityEvent(params)

        assert springSecurityEvent.save() != null

        params.id = springSecurityEvent.id

        def model = controller.edit()

        assert model.springSecurityEventInstance == springSecurityEvent
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/springSecurityEvent/list'

        response.reset()

        populateValidParams(params)
        def springSecurityEvent = new SpringSecurityEvent(params)

        assert springSecurityEvent.save() != null

        // test invalid parameters in update
        params.id = springSecurityEvent.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/springSecurityEvent/edit"
        assert model.springSecurityEventInstance != null

        springSecurityEvent.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/springSecurityEvent/show/$springSecurityEvent.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        springSecurityEvent.clearErrors()

        populateValidParams(params)
        params.id = springSecurityEvent.id
        params.version = -1
        controller.update()

        assert view == "/springSecurityEvent/edit"
        assert model.springSecurityEventInstance != null
        assert model.springSecurityEventInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/springSecurityEvent/list'

        response.reset()

        populateValidParams(params)
        def springSecurityEvent = new SpringSecurityEvent(params)

        assert springSecurityEvent.save() != null
        assert SpringSecurityEvent.count() == 1

        params.id = springSecurityEvent.id

        controller.delete()

        assert SpringSecurityEvent.count() == 0
        assert SpringSecurityEvent.get(springSecurityEvent.id) == null
        assert response.redirectedUrl == '/springSecurityEvent/list'
    }
}
