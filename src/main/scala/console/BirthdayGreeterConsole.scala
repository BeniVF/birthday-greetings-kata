package console

import core.BirthdayGreeter
import file.FileEmployeesFinder
import stmp.StmpBirthdayNotifier
import java.text.SimpleDateFormat

object BirthdayGreeterConsole extends App {
  val dateFormat = "yyyy/MM/dd"
  val filePathIndex = 0
  val emailHostIndex = 1
  val emailPortIndex = 2
  val senderIndex = 3
  val birthdayDateIndex = 4

  if (args.length == 5) {
    implicit val employeesFinder = new FileEmployeesFinder(args(filePathIndex))
    implicit val birthdayNotifier = new StmpBirthdayNotifier(args(emailHostIndex), args(emailPortIndex).toInt)
    val birthdayDate = new SimpleDateFormat(dateFormat).parse(args(birthdayDateIndex))
    BirthdayGreeter(args(senderIndex)).sendGreetings(birthdayDate)
  }
}
