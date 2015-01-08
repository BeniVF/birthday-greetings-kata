package wiser

import org.scalatest.{FlatSpecLike, BeforeAndAfterAll}
import org.subethamail.wiser.{WiserMessage, Wiser}


trait WiserServerSpec extends FlatSpecLike with BeforeAndAfterAll {

  val emailServerHost = "localhost"
  val emailServerPort = 3025
  protected val fakeEmailService = new Wiser {
    setPort(emailServerPort)
    setHostname(emailServerHost)
  }

  override def beforeAll() {
    fakeEmailService.start()
  }

  override def afterAll() {
    fakeEmailService.stop()
  }

  import scala.collection.JavaConversions._

  def receivedMessages: Seq[WiserMessage] = fakeEmailService.getMessages
}
