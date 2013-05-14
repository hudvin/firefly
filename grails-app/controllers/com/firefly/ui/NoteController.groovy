package com.firefly.ui

class NoteController {

    def index() {}

    def editor() {
        def paperHandlerId = params.paperHandlerId
        [paperHandlerId: paperHandlerId, content: PaperHandler.get(paperHandlerId).note]
    }

    def getContent() {
        def paperHandlerId = params.paperHandlerId
        def paperHandler = PaperHandler.get(paperHandlerId)
        println(paperHandler.note)
        render paperHandler.note
    }

    def save() {
        def text = params.text
        println("saved " + text)
        def paperHandlerId = params.paperHandlerId
        def paperHandler = PaperHandler.get(paperHandlerId)
        paperHandler.setNote(text)
        paperHandler.save(failOnError: true)
        render(status: 200)
    }
}
