name:="sbt_demo"

version:="1.0"

scalaVersion:="2.10.0"

scalacOptions +="-deprecation"

libraryDependencies <+= (scalaVersion)("org.scala-lang" % "scala-compiler" % _)



libraryDependencies ++= List(
  "com.typesafe.slick" %% "slick" % "1.0.0",
  "mysql" % "mysql-connector-java" % "5.1.24",
  "postgresql" % "postgresql" % "9.1-901.jdbc4",
  "org.hsqldb" % "hsqldb" % "2.0.0",
  "org.apache.derby" % "derby" % "10.6.1.0",
  "org.xerial" % "sqlite-jdbc" % "3.6.20",
  "com.h2database" % "h2" % "1.3.170",
  "org.slf4j" % "slf4j-nop" % "1.6.4",
  "org.mongodb" % "casbah-core_2.10" % "2.5.0",
  "org.mongodb" % "casbah-gridfs_2.10" % "2.5.0",
  "org.elasticsearch" % "elasticsearch" % "0.20.6",
  "com.spatial4j" % "spatial4j" % "0.3"
)

libraryDependencies += "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"
