package com.firefly.ui

class Tag {

    String label

    static belongsTo = [createdBy: Account]

    static constraints = {
    }

    String toString() {
        return label
    }
}
