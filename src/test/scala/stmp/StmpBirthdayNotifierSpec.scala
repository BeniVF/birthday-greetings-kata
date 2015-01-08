package stmp

import core.BirthdayNotifier.BirthdayMessage
import org.scalatest.{Matchers, FlatSpec}
import wiser.WiserServerSpec
import org.subethamail.wiser.WiserMessage

class StmpBirthdayNotifierSpec extends FlatSpec with Matchers with WiserServerSpec {

  it should "send an email" in {
    val sender = new StmpBirthdayNotifier(emailServerHost, emailServerPort)

    val message = BirthdayMessage("notreply@foobar.com", "ann@foobar.com", "Subject message", "Body Message")
    sender.sendMessage(message)

    hasReceivedOnlyTheMessage(message)
  }

  def hasReceivedOnlyTheMessage(sentMessage: BirthdayMessage) {
    receivedMessages should have size 1
    assertSameMessage(sentMessage, receivedMessages.head)
  }

  protected def assertSameMessage(sentMessage: BirthdayMessage, receivedMessage: WiserMessage) {
    receivedMessage.getEnvelopeSender shouldBe sentMessage.envelopeSender
    receivedMessage.getEnvelopeReceiver shouldBe sentMessage.envelopeReceiver
    receivedMessage.getMimeMessage.getSubject shouldBe sentMessage.subject
    receivedMessage.getMimeMessage.getContent.toString should include(sentMessage.body)
  }
}