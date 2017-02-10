import com.snppts.backend._
import org.scalatra._
import javax.servlet.ServletContext
import com.mongodb.casbah.Imports._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {

    context.mount(new SnippetController, "/snippettest")

    val mongoClient =  MongoClient()
    val databaseName = "snpptsdb"

    val groupsMongoClient = mongoClient(databaseName)("group")
    context.mount(new GroupMongoController(groupsMongoClient), "/group")

    val snippetsMongoClient = mongoClient(databaseName)("snippet")
    context.mount(new MongoController(snippetsMongoClient), "/snippet")
  }
}
