
// Use H2Driver to connect to an H2 database

import scala.slick.driver.H2Driver.simple._

// Use the implicit threadLocalSession

//import Database.threadLocalSession


/*

def removeUrlFromDomain(url: Url) {
    withTransaction {
      implicit tx => {
        domainService.removeUrlFromDomainInTx(url)
      }
    }
  }

def createDomainIfNeededInTx(domain: Domain)(implicit tx: TitanTransaction): Vertex = {

 def withTransaction[T](f: TitanTransaction => T)(implicit implGraph: TitanGraph): T = {
    val tx = implGraph.startTransaction()
    try {
      val result = f(tx)
      tx.stopTransaction(Conclusion.SUCCESS)
      result
    }
    catch {
      case ex: Throwable => {
        ex.printStackTrace()
        tx.abort()
        throw ex
      }
    }
  }



 */

trait TransactionSupport {

  def withTransaction[T](f: => T): T = {
    Database.forURL("jdbc:h2:mem:test1", driver = "org.h2.Driver") withSession {
      f
    }
  }

}


class Account(val id: Option[Int], val name: String, val email: String, val password: String, val isConfirmed: Boolean)

class AccountService() extends TransactionSupport {

  def addAccount(account: Account)(implicit session: Session): Unit = {
    AccountTable.insert(account.id, account.name, account.email, account.password, account.isConfirmed)
    println("insert")
    //  }
  }

  def getAccountById(id: Int)(implicit session: Session): Unit = {
    // Database.forURL("jdbc:h2:mem:test1", driver = "org.h2.Driver") withSession {
    println("====")
    Console println AccountTable.filter(_.id === id).list
    println("====")
    //  }
  }

  def getAccountByCredentials(email: String, password: String)(implicit session: Session): Unit = {
    println("****")
    Console println AccountTable.filter(a => a.email === email && a.password === password).list
    println("****")
  }


}

abstract class PaperService {


}

abstract class SearchService {


}

abstract class Service {


  //def validateAccount()

  def getAccount()

  def saveAccount()

  def getTags()

  def getPapers()

  def getPapersWithTag()

  def setTagsForPaper()

  def savePaper()


}


/*
  name
  email
  isConfirmed
  id

 */
object AccountTable extends Table[(Option[Int], String, String, String, Boolean)]("ACCOUNTS") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("NAME")

  def email = column[String]("EMAIL")

  def password = column[String]("PASSWORD")

  def isConfirmed = column[Boolean]("IS_CONFIRMED")

  def * = id.? ~ name ~ email ~ password ~ isConfirmed



}


//UI - ?  Playframework/Bootstrap
//admin area?
//one project?
//sbt
//scalatest


//linked papers

class ESUploader{

}

class ESQuery{

}


class PaperComments{

}

class ElasticSearchAPI{
  //storage
  //query

}

class MetadataStorage {


  /*
     hash
     size
     id
     title
     authors
     pages


   */

}


class FileStorageStorage {
  /*
    put
    get
   */
}

/*

id
mongoId



 */
object PaperTable extends Table[(Option[Int])]("PAPERS") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def * = id.?

}

/*

 id
 creator_id
 name

 */
object TagTable extends Table[(Option[Int])]("TAGS") {

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def * = id.?

}

// Definition of the SUPPLIERS table
object Suppliers extends Table[(Int, String, String, String, String, String)]("SUPPLIERS") {
  def id = column[Int]("SUP_ID", O.PrimaryKey)

  // This is the primary key column
  def name = column[String]("SUP_NAME")

  def street = column[String]("STREET")

  def city = column[String]("CITY")

  def state = column[String]("STATE")

  def zip = column[String]("ZIP")

  // Every table needs a * projection with the same type as the table's type parameter
  def * = id ~ name ~ street ~ city ~ state ~ zip
}

// Definition of the COFFEES table
object Coffees extends Table[(String, Int, Double, Int, Int)]("COFFEES") {
  def name = column[String]("COF_NAME", O.PrimaryKey)

  def supID = column[Int]("SUP_ID")

  def price = column[Double]("PRICE")

  def sales = column[Int]("SALES")

  def total = column[Int]("TOTAL")

  def * = name ~ supID ~ price ~ sales ~ total

  // A reified foreign key relation that can be navigated to create a join
  def supplier = foreignKey("SUP_FK", supID, Suppliers)(_.id)
}

object Hi {


  def main(args: Array[String]) = {

    Database.forURL("jdbc:h2:mem:test1", driver = "org.h2.Driver") withSession {
      implicit session: Session =>

      // The session is never named explicitly. It is bound to the current
      // thread as the threadLocalSession that we imported
      // Create the tables, including primary and foreign keys
        (Suppliers.ddl ++ Coffees.ddl ++ AccountTable.ddl).create

        AccountTable.insert(None, "Vadym", "hudvin@gmail.com", "password", true)


        // Insert some suppliers
        Suppliers.insert(101, "Acme, Inc.", "99 Market Street", "Groundsville", "CA", "95199")
        Suppliers.insert(49, "Superior Coffee", "1 Party Place", "Mendocino", "CA", "95460")
        Suppliers.insert(150, "The High Ground", "100 Coffee Lane", "Meadows", "CA", "93966")

        // Insert some coffees (using JDBC's batch insert feature, if supported by the DB)
        Coffees.insertAll(
          ("Colombian", 101, 7.99, 0, 0),
          ("French_Roast", 49, 8.99, 0, 0),
          ("Espresso", 150, 9.99, 0, 0),
          ("Colombian_Decaf", 101, 8.99, 0, 0),
          ("French_Roast_Decaf", 49, 9.99, 0, 0)
        )

        Query(Coffees) foreach {
          case (name, supID, price, sales, total) =>
            println("  " + name + "\t" + supID + "\t" + price + "\t" + sales + "\t" + total)
        }

        // Why not let the database do the string conversion and concatenation?
        val q1 = for (c <- Coffees) // Coffees lifted automatically to a Query
        yield ConstColumn("  ") ++ c.name ++ "\t" ++ c.supID.asColumnOf[String] ++
            "\t" ++ c.price.asColumnOf[String] ++ "\t" ++ c.sales.asColumnOf[String] ++
            "\t" ++ c.total.asColumnOf[String]
        // The first string constant needs to be lifted manually to a ConstColumn
        // so that the proper ++ operator is found
        q1 foreach println

        val q2 = for {
          c <- Coffees if c.price < 9.0
          s <- Suppliers if s.id === c.supID
        } yield (c.name, s.name)
        Console println q2.list()

        val a = for {
          t <- AccountTable
        } yield t

        Console println a.list()


        println("Hi!")

        val accountService = new AccountService
        val account = new Account(None, "hudvin", "hudvin@gmail.com", "qwerty", true)
        accountService.addAccount(account)
        accountService.getAccountById(2)
        accountService.getAccountByCredentials("hudvin@gmail.com", "qwerty")

        val b = for {
          t <- AccountTable
        } yield t

        Console println b.list()


    }


  }

}
