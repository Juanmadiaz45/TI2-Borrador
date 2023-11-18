import scala.annotation.tailrec
import scala.io.StdIn.readLine

/**
 * The MajorityElement object contains methods to find the majority element in a list.
 */
object ProblemII {

  /**
   * This method finds the majority element in a list.
   * @param n The number of elements in the list.
   * @param input The list of integers.
   * @return A string indicating whether a majority element exists and what it is.
   */
  def majorityElement(n: Int, input: List[Int]): String = {

    /**
     * This method counts the occurrences of an element in a list.
     * @param input The list of integers.
     * @param element The element to count.
     * @param count The current count (default is 0).
     * @return The final count of the element in the list.
     */
    @tailrec
    def countElement(input: List[Int], element: Int, count: Int = 0): Int = {

      input match
        case Nil => count
        case h::t =>
          if (h == element) countElement(t, element, count + 1)
          else countElement(t, element, count)

    }

    /**
     * This method finds the majority element in a list.
     * @param input The list of integers.
     * @return The majority element or -1 if no majority element exists.
     */
    def findMajority(input: List[Int]): Int = {

      if (input.isEmpty) return -1

      val sorted = input.sorted
      val mid = sorted(sorted.length / 2)
      if (countElement(sorted, mid) > sorted.length / 2) mid else -1

    }

    val majority = findMajority(input)
    if (majority == -1) "0\nThere is no majority element in this sequence"
    else s"1\n$majority is the majority element"

  }

  def main(args: Array[String]): Unit = {
    println("Enter the number of elements:")
    val n = readLine().toInt

    println("Enter the elements separated by spaces:")
    val input = readLine().split(" ").map(_.toInt).toList

    println(majorityElement(n, input))
  }

}