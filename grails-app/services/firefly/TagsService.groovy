package firefly

import com.firefly.ui.Account
import com.firefly.ui.PaperHandler
import com.firefly.ui.Tag

class TagsService {

    def springSecurityService


    def removeTag(paperHandlerId,tagLabel){
        def paperHandler = PaperHandler.get(paperHandlerId)
        def savedTag = Tag.findByLabel(tagLabel)
        paperHandler.removeFromTags(savedTag)
        paperHandler.merge(failOnError: true)
    }

    def addTag(paperHandlerId, tagLabel) {
        def paperHandler = PaperHandler.get(paperHandlerId)
        def savedTag = Tag.findByLabel(tagLabel)
        if (savedTag == null) {
            def currentAccount = springSecurityService.currentUser.asType(Account)
            savedTag = new Tag(label: tagLabel, createdBy: currentAccount).save(failOnError: true)
        }
        if (!paperHandler.getTags().contains(savedTag)) {
            paperHandler.addToTags(savedTag)
            paperHandler.merge(failOnError: true)
        }
    }


}
