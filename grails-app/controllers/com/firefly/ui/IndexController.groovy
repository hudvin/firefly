package com.firefly.ui

class IndexController {

    def springSecurityService

    def index() {
        //render "this is index"
    }


    def listPapers() {
        def currentAccount = springSecurityService.currentUser.asType(Account)
        [paperHandler: PaperHandler.findAllByAccount(currentAccount)]
        //pass current user
    }
}
