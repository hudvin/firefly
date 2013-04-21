package firefly

import com.mongodb.Mongo
import com.mongodb.gridfs.GridFS
import com.mongodb.gridfs.GridFSFile
import org.springframework.beans.factory.InitializingBean

class FileService implements InitializingBean {

    def grailsApplication

    def mongoSettings

    def mongo

    def gridfs

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

        try {
            if (gridfs.findOne(filename) == null) {
                return save(inputStream, contentType, filename)
            } else {
                println "Removing old file and uploading new file"
                gridfs.remove(filename)
                return save(inputStream, contentType, filename)
            }
        } catch (Exception ex) {
            ex.printStackTrace()
            throw ex
        }
        return null
    }

    def GridFSFile save(inputStream, contentType, filename) {
        def inputFile = gridfs.createFile(inputStream)
        inputFile.setContentType(contentType)
        inputFile.setFilename(filename)
        inputFile.save()
        return inputFile
    }

    def retrieveFile(String filename) {
        return gridfs.findOne(filename)
    }

    def deleteFile(String filename) {
        gridfs.remove(filename)
    }

    def getFilesList() {
        def cursor = gridfs.getFileList()
        cursor.toArray()
    }

}
