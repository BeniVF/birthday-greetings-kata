package core

import org.scalatest.{Inspectors, Matchers, FlatSpec}
import java.util.Date
import scala.language.reflectiveCalls

class BirthdayGreeterSpec extends FlatSpec with Matchers with Inspectors {
  import BirthdayNotifier._
  import EmployeesFinder._
  val today = new Date

  implicit val emailSender = new Object with BirthdayNotifier {
    var messages: List[BirthdayMessage] = List()
    def sendMessage(message: BirthdayMessage) {
      messages :+= message
    }
  }

  it should "not sends a greetings email if there are not employees whose birthday is today" in  {
    val birthdayGreeter = hasBirthdayGreeter()

    birthdayGreeter.sendGreetings(today)

    emailSender.messages should have size 0
  }

  it should "sends a greetings email to all employees whose birthday is today" in  {

    val birthdayGreeter = hasBirthdayGreeter(
      EmployeeBuilder("john.doe@foobar.com").withFirstName("John").withBirthday(today),
      EmployeeBuilder("sam.smith@foobar.com").withFirstName("Sam").withBirthday(today)
    )

    birthdayGreeter.sendGreetings(today)

    emailSender.messages should have size 2
    forAtLeast(1, emailSender.messages) {
      message =>
        message.envelopeReceiver == "john.doe@foobar.com"
        message.body should include ("dear John")
    }

  }

  def hasBirthdayGreeter(employeeBuilders: EmployeeBuilder*): BirthdayGreeter = {
    emailSender.messages = List()
    implicit val employeeFinder = new Object with EmployeesFinder {
      def findEmployeesBornOn(date: Date) = employeeBuilders.map(_.build).toList
    }
    val birthdayGreeter = BirthdayGreeter("sender@foobar.com")
    birthdayGreeter
  }
}
