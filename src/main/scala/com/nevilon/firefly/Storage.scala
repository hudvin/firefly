package com.nevilon.firefly

import com.mongodb.casbah.Imports._
import com.mongodb.casbah.gridfs.Imports._
import java.io.InputStream

/**
 * Created with IntelliJ IDEA.
 * User: hudvin
 * Date: 4/6/13
 * Time: 4:48 PM
 */


class FileStorage(val mongoDBConf: MongoDBConfig) {

  private var mongoClient: MongoClient = null
  private var gridfs: GridFS = null
  private var mongoDB: MongoDB = null

  connect()

  def getGridFS() = gridfs

  private def connect() {
    mongoClient = MongoClient(mongoDBConf.host, mongoDBConf.port)
    if (mongoDBConf.drop) {
      mongoClient.dropDatabase(mongoDBConf.dbName)
    }
    mongoDB = mongoClient(mongoDBConf.dbName)
    gridfs = GridFS(mongoDB)
  }


  def saveStream(is: InputStream, url: String, contentType: String, urlId: String): Option[String] = {
    gridfs(is) {
      file =>
        file.contentType = contentType
        file.filename = url
        file.put("urlId", urlId)
    }
    match {
      case None => None //throw exception or None?
      case Some(objectId) => {
        Some(objectId.asInstanceOf[ObjectId].toString)
      }
    }
  }

  def getFileStream(fileId: String): InputStream = {
    getGridFS().findOne(new ObjectId(fileId)) match {
      case None => throw new RuntimeException("wrong fileId")
      case Some(gridFsFile) => gridFsFile.inputStream

    }
  }


}


trait MongoDBConfig {

  def host: String

  def port: Int

  def dbName: String

  def drop: Boolean

}