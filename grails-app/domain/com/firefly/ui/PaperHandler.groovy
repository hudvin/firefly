package com.firefly.ui

class PaperHandler {

    static belongsTo = [account: Account, paper: Paper]

    static hasMany = [tags: Tag]

    static constraints = {
        note widget: 'textarea'
    }

    String note

    static mapping = {
        note type: 'text'
    }

    String toString() {
        return "account: $account, paper: $paper"
    }

}
