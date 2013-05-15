package com.firefly.ui

class Account {

    transient springSecurityService

    String username
    String password
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    String interests

    static hasMany = [paperHandler: PaperHandler]


    static constraints = {
        username blank: false, unique: true
        password blank: false
        interests widget: 'textarea'
    }

    static mapping = {
        password column: '`password`'
        interests type: 'text'
    }

    Set<Role> getAuthorities() {
        AccountRole.findAllByAccount(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }

    String toString() {
        return username
    }
}
