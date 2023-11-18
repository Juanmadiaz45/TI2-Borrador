import org.scalatest.funsuite.AnyFunSuite

class TestProblemII extends AnyFunSuite {

  test("Majority Example Case") {
    val input = List(2, 3, 9, 2, 2)
    val result = ProblemII.majorityElement(5, input)
    assert(result == "1\n2 is the majority element")
  }

  test("No Majority Case") {
    val input = List(1, 2, 3, 4)
    val result = ProblemII.majorityElement(4, input)
    assert(result == "0\nThere is no majority element in this sequence")
  }

  test("Non-strict Majority") {
    val input = List(1, 2, 3, 1)
    val result = ProblemII.majorityElement(4, input)
    assert(result == "0\nThere is no majority element in this sequence")
  }

  test("Null Test") {
    val input = List()
    val result = ProblemII.majorityElement(0, input)
    assert(result == "0\nThere is no majority element in this sequence")
  }

  test("Long List with Majority") {
    val input = List.fill(5000)(1) ++ List.fill(4999)(2)
    val result = ProblemII.majorityElement(10000, input)
    assert(result == "1\n1 is the majority element")
  }
}
