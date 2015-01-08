package core

import java.util.Date

trait EmployeesFinder {
  import EmployeesFinder._
  def findEmployeesBornOn(date: Date): List[Employee]
}

object EmployeesFinder {
  case class Employee(email: String, firstName: String, lastName: String, birthday: Date)

  case class EmployeeBuilder(email: String, firstName: String = "", lastName: String = "", birthday: Date = new Date) {
    def withFirstName(firstName: String) = this.copy(firstName = firstName)

    def withLastName(lastName: String) = this.copy(lastName = lastName)

    def withBirthday(birthday: Date) = this.copy(birthday = birthday)

    def build = Employee(email, firstName, lastName, birthday)
  }

}