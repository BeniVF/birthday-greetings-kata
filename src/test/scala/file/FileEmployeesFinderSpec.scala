package file

import org.scalatest.{Inspectors, Matchers, FlatSpec}
import java.text.SimpleDateFormat

class FileEmployeesFinderSpec extends FlatSpec with Matchers with Inspectors {
  val fileName = "employees_data.txt"
  it should "not find employees born on a date" in {
    val fileEmployeesFinder = new FileEmployeesFinder(fileName)

    val employeesBornOnDate = fileEmployeesFinder.findEmployeesBornOn(date("1999/01/06"))

    employeesBornOnDate should have size 0
  }

  it should "find employees born on a date" in {
    val fileEmployeesFinder = new FileEmployeesFinder(fileName)

    val employeesBornOnDate = fileEmployeesFinder.findEmployeesBornOn(date("2011/03/11"))

    employeesBornOnDate should have size 4
    forAtLeast(1, employeesBornOnDate) {
      employee =>
        employee.email should include("mary.ann")
        employee.firstName shouldBe "Mary"
        employee.lastName shouldBe "Ann"
    }
  }

  def date(source: String) = new SimpleDateFormat("yyyy/MM/dd").parse(source)
}


