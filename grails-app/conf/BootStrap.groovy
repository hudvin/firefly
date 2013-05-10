import com.firefly.ui.Account
import com.firefly.ui.AccountRole
import com.firefly.ui.Role
import com.firefly.ui.Tag

class BootStrap {


    def init = { servletContext ->

        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)

        def adminUser = Account.findByUsername('admin') ?: new Account(
                username: 'admin',
                password: 'admin',
                enabled: true).save(failOnError: true)

        adminRole = Role.findByAuthority('ROLE_ADMIN')
        for (account in Account.findAll()) {
            if (!AccountRole.findByAccountAndRole(account, adminRole)) {
                AccountRole.create account, adminRole
            }
        }

        def tag = new Tag(createdBy: adminUser, label: "new").save(failOnError: true)

    }
    def destroy = {
    }
}
