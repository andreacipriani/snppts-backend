package com.snppts.backend

import org.scalatra._
import com.mongodb.casbah.Imports._

class GroupMongoController(mongoColl: MongoCollection) extends ScalatraServlet {

  /**
   * Insert a new object into the database. You can use the following from your console to try it out:
   * curl -i -H "Accept: application/json" -X POST -d "name=The1stgroupever" http://localhost:8080/group
   */
  post("/") {
    val name = params("name")
    val group = MongoDBObject("name" -> name)
    mongoColl += group
  }

  /**
   * Retrieve everything in the MongoDb collection we're currently using.
   http://localhost:8080/mongo/
   */
  get("/") {
    mongoColl.find()
    for { x <- mongoColl} yield x
  }

  /**
   * Query for the first object which matches the values given. If you copy/pasted the insert example above,
   * try http://localhost:8080/mongo/query/super/duper in your browser.
   */
  get("/query/:key/:value") {
    val q = MongoDBObject(params("key") -> params("value"))
    for ( x <- mongoColl.findOne(q) ) yield x
  }

}