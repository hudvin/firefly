package com.firefly.ui

class Paper {

    String gfsId
    String filename
    long filesize



    static constraints = {
        gfsId nullable: false, blank: false, size: 24..24
        filename nullable: false, blank: false
        filesize nullable: false, min: 0l
    }

    String toString() {
        return "filename: $filename"
    }
}
