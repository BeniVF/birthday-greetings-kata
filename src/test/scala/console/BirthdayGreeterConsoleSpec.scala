package console

import org.scalatest.{Inspectors, Matchers, FlatSpec}
import wiser.WiserServerSpec

class BirthdayGreeterConsoleSpec extends FlatSpec with Matchers with Inspectors with WiserServerSpec {

  it should "not send an email with a wrong number of arguments" in {
    BirthdayGreeterConsole.main(Array("employees_data.txt", emailServerHost, emailServerPort.toString ))
    receivedMessages should have size 0
  }

  val birthdayDate = "2015/03/11"
  it should s"send a greetings email to all employees whose birthday is $birthdayDate" in {
    val sender = "birthday-greeter@foobar.com"
    BirthdayGreeterConsole.main(Array("employees_data.txt", emailServerHost, emailServerPort.toString, sender, birthdayDate ))
    receivedMessages should have size 4
    forAll(receivedMessages) {
      message =>
        message.getEnvelopeSender shouldBe sender
    }
  }


}
