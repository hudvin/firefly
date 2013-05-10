package com.firefly.ui

class RedirectController {

    def index() { }

    def goTo(){
        redirect(url:params.urlToGo)
    }

}
