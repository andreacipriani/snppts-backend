package com.snppts.backend

import org.scalatra._
import com.mongodb.casbah.Imports._

class MongoController(mongoColl: MongoCollection) extends ScalatraServlet {

  /**
   * Insert a new object into the database. You can use the following from your console to try it out:
   * curl -i -H "Accept: application/json" -X POST -d "key=super&value=duper" http://localhost:8080/mongo
   */
  post("/") {
    val key = params("key")
    val value = params("value")
    val newObj = MongoDBObject(key -> value)
    mongoColl += newObj
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