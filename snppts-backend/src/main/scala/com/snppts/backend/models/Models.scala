package com.snppts.backend.models

case class Snippet(id: Integer, abbreviation: String, body: String, groupId: String)
case class Group(id: String, name: String)
