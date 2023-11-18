import scala.annotation.tailrec
import scala.io.StdIn.readLine


object ProblemI {
  /**
   * This function performs a binary search in a sorted list.
   *
   * @param arr   The sorted list in which the search will be performed.
   * @param key   The value to search for in the list.
   * @param left  The left index of the current sublist.
   * @param right The right index of the current sublist.
   * @return The index where the key is found if it exists, or -1 if it is not found.
   */
  @tailrec
  def binarySearch(arr: List[Int], key: Int, left: Int, right: Int): Int = {
    if (left > right) {
      -1 // Key not found
    } else {
      val mid = left + (right - left) / 2
      if (arr(mid) == key) {
        mid // Key found at position 'mid'
      } else if (arr(mid) < key) {
        binarySearch(arr, key, mid + 1, right) // Search in the right half of the array
      } else {
        binarySearch(arr, key, left, mid - 1) // Search in the left half of the array
      }
    }
  }

  /**
   * This function searches for a list of keys in a sorted list.
   *
   * @param arr  The sorted list in which the search will be performed.
   * @param keys The list of keys to be searched in 'arr'.
   * @return A list of indices where the keys were found or a list of -2 if the number of expected keys does not match.
   */
  def searchKeys(arr: List[Int], keys: List[Int]): List[Int] = {
    /**
     * This function validates whether the expected number of keys matches the length of 'keys' and performs the search.
     *
     * @param numExpected  The expected number of keys.
     * @param keysToSearch The list of keys to search.
     * @return A list of indices where the keys were found or a list of -2 if the number of keys expected does not match.
     */
    def validateAndSearch(numExpected: Int, keysToSearch: List[Int]): List[Int] = {
      if (numExpected != keysToSearch.length) {
        List.fill(numExpected)(-2) // If it does not match, return a list of -2
      } else {
        keysToSearch.map(key => binarySearch(arr, key, 0, arr.length - 1)) // If it matches, perform the search
      }
    }

    keys match {
      case numExpected :: remainingKeys =>
        validateAndSearch(numExpected, remainingKeys)
      case _ =>
        List.empty // Handle the case when there are no more keys
    }
  }

  //AÑADIR MAIN, quizá sea esto lo que hace que no corra

}

