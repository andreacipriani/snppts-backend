package com.snppts.backend

import org.scalatra._
import org.scalatra.json._
import com.snppts.backend.models._
import org.json4s.{DefaultFormats, Formats}


class SnippetController extends ScalatraServlet with JacksonJsonSupport {

  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    val snippet = new Snippet(1, "_foo", "Ale papa'", "1000")
    snippet
    //compact(render(parse(""" { "abbreviation" : "_test" } """)))
  }

}
