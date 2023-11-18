import org.scalatest.funsuite.AnyFunSuite

class TestsProblemI extends AnyFunSuite {


  test("All elements exists and first int is the correct number of keys") {
    val case1Input1 = List(2, 3, 9, 2, 2)
    val case1Input2 = List(5, 2, 3, 9, 2, 2)
    val case1Result = searchKeys(case1Input1, case1Input2)
    assert(case1Result == List(0,0,0,0,0), "Caso 1 Falló")
  }

  test("Search for a single element in a list with one element") {
    val arr = List(5)
    val keys = List(5)
    val result = ProblemI.searchKeys(arr, keys)
    assert(result == List(0), "Caso 1 Falló")
  }

  test("Search for a non-existent element in a list with multiple elements") {
    val arr = List(1, 2, 3, 4, 5)
    val keys = List(6)
    val result = ProblemI.searchKeys(arr, keys)
    assert(result == List(-2), "Caso 2 Falló")
  }

  test("Search for multiple elements in a list with duplicates") {
    val arr = List(1, 2, 2, 3, 3, 3, 4, 5, 5, 5, 5)
    val keys = List(2, 3, 5)
    val result = ProblemI.searchKeys(arr, keys)
    assert(result == List(1, 2, 3), "Caso 3 Falló")
  }

  test("Search for elements in a sorted list in descending order") {
    val arr = List(5, 4, 3, 2, 1)
    val keys = List(3, 1, 5)
    val result = ProblemI.searchKeys(arr, keys)
    assert(result == List(0, 0, 0), "Caso 4 Falló")
  }

  test("Search for elements in an empty list") {
    val arr = List.empty[Int]
    val keys = List(1, 2, 3)
    val result = ProblemI.searchKeys(arr, keys)
    assert(result == List(-2, -2, -2), "Caso 5 Falló")
  }

  test("Search for elements when keys list is empty") {
    val arr = List(1, 2, 3, 4, 5)
    val keys = List.empty[Int]
    val result = ProblemI.searchKeys(arr, keys)
    assert(result == List.empty[Int], "Caso 6 Falló")
  }
  
}