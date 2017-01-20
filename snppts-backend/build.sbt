import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import ScalateKeys._

val ScalatraVersion = "2.5.0"

ScalatraPlugin.scalatraSettings

scalateSettings

organization := "snppts"

name := "snppts-backend"

version := "0.1.0"

scalaVersion := "2.12.1"

resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalate" % ScalatraVersion,
  "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
  "org.json4s"   %% "json4s-jackson" % "3.5.0",
  "org.scalatra" %% "scalatra-json" % "2.5.0",
  "ch.qos.logback" % "logback-classic" % "1.1.5" % "runtime",
  "org.eclipse.jetty" % "jetty-webapp" % "9.2.15.v20160210" % "container",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  //"org.json4s" %% "json4s-mongo" % "3.5.0"
  "org.mongodb" %% "casbah" % "3.1.1"
)

scalateTemplateConfig in Compile := {
  val base = (sourceDirectory in Compile).value
  Seq(
    TemplateConfig(
      base / "webapp" / "WEB-INF" / "templates",
      Seq.empty,  /* default imports should be added here */
      Seq(
        Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
      ),  /* add extra bindings here */
      Some("templates")
    )
  )
}

enablePlugins(JettyPlugin)
