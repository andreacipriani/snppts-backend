package com.snppts.backend

import org.scalatra._
import com.mongodb.casbah.Imports._

class GroupMongoController(mongoCollection: MongoCollection) extends ScalatraServlet {

  /**
   * Insert a new group into the database.
   * curl -i -H "Accept: application/json" -X POST -d "name=Ale" http://localhost:8080/api/v1/group
   */

  post("/") {
    val name = params("name")
    val maxGroupId = mongoMaxGroupId() + 1
    val group = MongoDBObject("group_id" -> maxGroupId, "name" -> name)
    mongoCollection += group
  }

  def mongoMaxGroupId() : Int = {
    val fields = MongoDBObject("group_id" -> 1)
    val orderByAsc = -1
    val orderBy = MongoDBObject("group_id" -> orderByAsc)
    val result = mongoCollection.findOne(fields = fields, orderBy = orderBy)
    val group = result.get.getAs[Int]("group_id").get
    return group
  }

  /**
   * Get all the groups
   http://localhost:8080/api/v1/mongo/
   */

  get("/") {
    mongoCollection.find()
    for { x <- mongoCollection} yield x
  }

  /**
   * Get a group from its id
   */

  get("/query/:group_id") {
    val groupId = params("group_id")
    val query = MongoDBObject("group_id" -> groupId)
    for ( x <- mongoCollection.findOne(query) ) yield x
  }

}