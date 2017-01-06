package com.snppts.backend

import org.scalatra._
import org.scalatra.json._
import org.json4s.jackson.JsonMethods._

class SnippetServlet extends SnpptsbackendStack {

  get("/") {
    compact(render(parse(""" { "abbreviation" : "_test" } """)))
  }

}
