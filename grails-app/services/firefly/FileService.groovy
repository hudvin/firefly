package firefly

import com.firefly.ui.Account
import com.firefly.ui.Paper
import com.firefly.ui.PaperHandler
import com.firefly.ui.Tag
import com.mongodb.BasicDBObject
import com.mongodb.Mongo
import com.mongodb.gridfs.GridFS
import com.mongodb.gridfs.GridFSFile
import org.bson.types.ObjectId
import org.codehaus.groovy.grails.io.support.IOUtils
import org.springframework.beans.factory.InitializingBean

class FileService implements InitializingBean {

    def grailsApplication

    def springSecurityService

    def mongoSettings

    def mongo

    def GridFS gridfs

    void afterPropertiesSet() {
        this.mongoSettings = grailsApplication.config.mongodb
        mongo = new Mongo(mongoSettings.host.toString(), mongoSettings.port.intValue())
        if (mongoSettings.bucket == null) {
            gridfs = new GridFS(mongo.getDB(mongoSettings.dbName.toString()))
        } else {
            gridfs = new GridFS(mongo.getDB(mongoSettings.dbName.toString()), mongoSettings.bucket.toString())
        }
    }

    def GridFSFile saveFile(file) {
        def inputStream = file.getInputStream()
        def contentType = file.getContentType()
        def filename = file.getOriginalFilename()

        //create tmp file and save uploaded file to it
        def tmpFile = File.createTempFile("upload", null);
        IOUtils.copy(inputStream, new FileOutputStream(tmpFile))
        //  tmpFile.delete();
        //calculate md5 hash
        def fis = new FileInputStream(tmpFile)
        def md5 = org.apache.commons.codec.digest.DigestUtils.md5Hex(fis)

        inputStream = new FileInputStream(tmpFile)
        def gfsFile = gridfs.findOne(new BasicDBObject("md5", md5))
        try {
            if (gfsFile == null) {
                println("not found")
                gfsFile = save(inputStream, contentType, filename)
                def paper = new Paper(gfsId: gfsFile.getId().toString(), filename: gfsFile.filename, filesize: gfsFile.length)
                paper.save(failOnError: true)
            } else {

                println("found")
            }
            def defaultTag =  Tag.findByLabel("new")
            def currentAccount = springSecurityService.currentUser.asType(Account)
            def paper = Paper.findByGfsId(gfsFile.id.toString())
            def paperHandler =  PaperHandler.findByAccountAndPaper(currentAccount,paper)
            if(paperHandler==null){
                paperHandler = new PaperHandler(account: currentAccount, paper:paper)
                paperHandler.addToTags(defaultTag)
                paperHandler.save(failOnError: true)
            }
            println(paperHandler)

        //    Account.get(0).paperHandler.find

            return gfsFile
        } catch (Exception ex) {
            ex.printStackTrace()
            throw ex
        } finally {
            tmpFile.delete()
        }
    }

    def GridFSFile save(inputStream, contentType, filename) {
        def inputFile = gridfs.createFile(inputStream)
        inputFile.setContentType(contentType)
        inputFile.setFilename(filename)
        inputFile.save()
        return inputFile
    }

    def retrieveFile(String filename) {
        return gridfs.findOne(new ObjectId(filename))
    }

    def deleteFile(String filename) {
        gridfs.remove(filename)
    }

    def getFilesList() {
        def cursor = gridfs.getFileList()
        cursor.toArray()
    }

}
