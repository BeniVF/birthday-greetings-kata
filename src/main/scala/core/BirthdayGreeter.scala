package core

import java.util.Date


trait BirthdayGreeter {
  def sendGreetings(date: Date)
}

object BirthdayGreeter {
  def apply(sender: String)(implicit employeeFinder: EmployeesFinder, birthdayNotifier: BirthdayNotifier) = new Object with BirthdayGreeter {

    import core.BirthdayNotifier.BirthdayMessage

    val subject = "Happy birthday!"

    def sendGreetings(date: Date) {
      val employees = employeeFinder.findEmployeesBornOn(date)
      employees.foreach {
        employee =>
          val body = s"Happy Birthday, dear ${employee.firstName}"
          birthdayNotifier.sendMessage(BirthdayMessage(sender, employee.email, subject, body))
      }
    }
  }
}