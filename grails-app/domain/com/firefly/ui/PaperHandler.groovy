package com.firefly.ui

class PaperHandler {

    static belongsTo = [account: Account, paper: Paper]

    static hasMany = [tags: Tag]

    static constraints = {
    }



    String toString() {
        return "account: $account, paper: $paper"
    }

}
