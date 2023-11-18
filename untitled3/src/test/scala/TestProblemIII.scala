import org.scalatest.funsuite.AnyFunSuite

class TestProblemIII extends AnyFunSuite {

  test("Problem 3 Organizing Lottery Example Case") {
    val segments = List((2, 3), (0, 5), (7, 10))
    val points = List(1, 6, 11)
    val result = ProblemIII.organizeLottery(segments, points)
    assert(result == List(1, 0, 0))
  }

  test("Problem 3 Organizing Lottery High Case") {
    val segments = List((3, 4), (0, 7), (1, 10), (8,12))
    val points = List(6, 1, 14, 9)
    val result = ProblemIII.organizeLottery(segments, points)
    assert(result == List(2, 2, 0, 2))
  }

  test("Problem 3 Organizing Lottery No Cases") {
    val segments = List((1, 3), (2, 5), (3, 6))
    val points = List(0,0,0)
    val result = ProblemIII.organizeLottery(segments, points)
    assert(result == List(0,0,0))
  }

  test("Problem 3 Organizing Lottery One Range") {
    val segments = List((1, 3), (3, 8))
    val points = List(0, 4, 9)
    val result = ProblemIII.organizeLottery(segments, points)
    assert(result == List(0, 1, 0))
  }

  test("Problem 3 Check Constraints Invalid Case 1") {
    val segments = List((-100000000, 100000000), (0, 50000000), (1, 100000000), (80000000, 120000000))
    val points = List(60000000, 100000000, 140000000, 90000000)
    val validConstraints = ProblemIII.checkConstraints(segments, points)
    assert(!validConstraints)
  }
  test("Problem 3 Check Constraints - Invalid Case 2") {
    val segments = List((-100000000, 100000000), (-100000000, 0), (0, 100000000), (80000000, 120000000))
    val points = List(0, 100000000, -100000000, 90000000)
    val validConstraints = ProblemIII.checkConstraints(segments, points)
    assert(!validConstraints)
  }

}

