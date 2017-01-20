import com.snppts.backend._
import org.scalatra._
import javax.servlet.ServletContext
import com.mongodb.casbah.Imports._

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    //context.mount(new HelloServlet, "/")
    context.mount(new SnippetController, "/snippet")

    
    val mongoClient =  MongoClient()
    val mongoColl = mongoClient("casbah_test")("test_data")
    context.mount(new MongoController(mongoColl), "/mongo")
  }
}
