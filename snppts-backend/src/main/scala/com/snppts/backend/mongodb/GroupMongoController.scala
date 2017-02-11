package com.snppts.backend

import org.scalatra._
import com.mongodb.casbah.Imports._
import scala.util.control.Exception._

class GroupMongoController(mongoCollection: MongoCollection) extends ScalatraServlet {

val groupId = "group_id"

  /**
   * Insert a new group into the database.
   * curl -i -H "Accept: application/json" -X POST -d "name=Ale" http://localhost:8080/api/v1/group
   */

  post("/") {
    val name = params("name")
    val maxGroupId = mongoMaxGroupId() + 1
    val group = MongoDBObject(groupId -> maxGroupId, "name" -> name)
    mongoCollection += group
  }

  def mongoMaxGroupId() : Int = {
    val fields = MongoDBObject(groupId -> 1)
    val orderByAsc = -1
    val orderBy = MongoDBObject(groupId -> orderByAsc)
    val result = mongoCollection.findOne(fields = fields, orderBy = orderBy)
    val firstId = 0

    if (result.isDefined) {
      return result.get.getAs[Int](groupId).get
    }
    return firstId
  }

  /**
   * Get all the groups
  http://localhost:8080/api/v1/group/
   */

  get("/") {
    mongoCollection.find()
    for { x <- mongoCollection} yield x
  }

  /**
   * Get a group from its id
  http://localhost:8080/api/v1/group/1
   */

  get("/:group_id") {
    val optGroupId = catching(classOf[NumberFormatException]) opt params(groupId).toInt
    val query = MongoDBObject(groupId -> optGroupId)
    for ( x <- mongoCollection.findOne(query) ) yield x
  }

}