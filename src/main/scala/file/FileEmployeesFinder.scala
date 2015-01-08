package file

import scala.io.Source
import java.io.File
import java.text.SimpleDateFormat
import java.util.{Calendar, Date}
import core.EmployeesFinder
import java.util.Calendar._

class FileEmployeesFinder(filePath: String) extends EmployeesFinder {

  import FileEmployeesFinder._
  import EmployeesFinder._

  def findEmployeesBornOn(date: Date): List[Employee] =
    findAllEmployees.filter(employee => sameDay(employee.birthday, date))

  def findAllEmployees: List[Employee] =
    parseFile.map(toEmployee)

  private def parseFile: List[Vector[String]] = {
    def parseLine: (String) => Vector[String] = {
      _.split(",").map(_.trim).toVector
    }
    val lines = Source.fromFile(new File(filePath)).getLines().toList
    lines.tail.map(parseLine)
  }

  private def toEmployee(source: Vector[String]): Employee =
    EmployeeBuilder(source(emailIndex)).
      withFirstName(source(firstNameIndex)).
      withLastName(source(lastNameIndex)).
      withBirthday(new SimpleDateFormat(dateFormat).parse(source(dateBirthIndex))).build

  private def sameDay(firstDate: Date, secondDate: Date) =
    getDayOfMonth(firstDate) == getDayOfMonth(secondDate) && getMoth(firstDate) == getMoth(secondDate)

  private def getDayOfMonth(date: Date) = {
    val calendar = Calendar.getInstance()
    calendar.setTime(date)
    calendar.get(DAY_OF_MONTH)
  }

  private def getMoth(date: Date) = {
    val calendar = Calendar.getInstance()
    calendar.setTime(date)
    calendar.get(MONTH)
  }

}

object FileEmployeesFinder {
  private val dateFormat = "yyyy/MM/dd"
  private val lastNameIndex = 0
  private val firstNameIndex = 1
  private val dateBirthIndex = 2
  private val emailIndex = 3
}
