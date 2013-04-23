package com.firefly.ui

class List {

    static belongsTo = [account: Account]

    String name
    String description

    static constraints = {
        name nullable: false, blank: false
        description nullable: true, blank: true
    }

    String toString() {
        return name
    }
}
