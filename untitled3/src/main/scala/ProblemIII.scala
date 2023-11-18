import scala.annotation.tailrec
import scala.io.StdIn.readLine

object ProblemIII {
  def main(args: Array[String]): Unit = {
    //Enter the list of segments s and point p
    val input = readLine().split(" ").map(_.toInt)
    val s = input(0)
    val p = input(1)

     //Reads 's' lines of input, where each line represents a segment with start and end
     // and converts them into a list of tuples.
     // Returns a list of tuples [Int,Int]
    val segments = (1 to s).map { _ =>
      val segment = readLine().split(" ").map(_.toInt)
      (segment(0), segment(1))
    }.toList

    val points = readLine().split(" ").map(_.toInt).toList

    println(organizeLottery(segments, points).mkString(" "))

  }

  /**
   * Checks if the input segments s and points p satisfy the following constraints:
   * 1 ≤ 𝑠, 𝑝 ≤ 50000
   * -10^8 ≤ ai ≤ bi  ≤ 10^8 for all 0 ≤ 𝑖 < 𝑠
   * -10^8 ≤ xj  ≤ 10^8for all 0 ≤ 𝑗 < 𝑝
   * @param segments List of segments
   * @param points   List of points
   * @return True if constraints are satisfied, false if not.
   */
  def checkConstraints(segments: List[(Int, Int)], points: List[Int]): Boolean = {
    val maxLimit = Math.pow(-10,8)
    val segmentsV = segments.forall { case (ai, bi) => ai <= bi && bi <= maxLimit }
    val pointsV = points.forall(_ <= maxLimit)
    segmentsV && pointsV
  }

  def organizeLottery(segments: List[(Int, Int)], points: List[Int]): List[Int] = {
    /**
     * Performs a merge sort on a list
     * Original Code Inspired from: https://medium.com/analytics-vidhya/playing-with-scala-merge-sort-d382fb1a32ff
     * @param list List to be sorted
     * @return Sorted list
     */
    def mergeSort(list: List[Int]): List[Int] = {
      @tailrec
      def merge(l1: List[Int], l2: List[Int], acc: List[Int] = List()): List[Int] = {
        (l1, l2) match {
          case (Nil, _) => acc ++ l2
          case (_, Nil) => acc ++ l1
          case (x :: xs, y :: ys) =>
            if (x < y) merge(xs, l2, acc :+ x)
            else merge(l1, ys, acc :+ y)
        }
      }
      list match {
        case Nil => Nil
        case xs :: Nil => List(xs)
        case _ =>
          val (left, right) = list splitAt list.length / 2
          merge(mergeSort(left), mergeSort(right))
      }
    }
    if (checkConstraints(segments, points)) {
      /**
       * Performs a binary search on a sorted list
       * @param list     List of integers to search in
       * @param goal   Target value to search for
       * @param leftSize Indicates if the search is for left side (true) or right side(false)
       * @return Index of the searched value in the list
       */
      def doubleBinarySearch(list: List[Int], goal: Int, leftSize: Boolean): Int = {
        @tailrec
        def search(start: Int, end: Int): Int = {
          if (start > end) { start
          }else {
            val mid = (start + end) / 2
            list(mid) match {
              case x if (leftSize && x > goal) || (!leftSize && x >= goal) => search(start, mid - 1)
              case _ => search(mid + 1, end)
            }
          }
        }
        search(0, list.length - 1)
      }

      /**
       * Recursive function to count segments that intersect each point.
       *
       * @param rightSegments Right sorted list of segments
       * @param leftSegments  Left sorted list of segments
       * @param points List of points.
       * @return List representing the count of intersections for each point.
       */
      def computePointsAndSegments(rightSegments: List[Int], leftSegments: List[Int], points: List[Int]): List[Int] = points match {
        case Nil => Nil
        case point :: tail =>
          val leftIndex = doubleBinarySearch(leftSegments, point, true)
          val rightIndex = doubleBinarySearch(rightSegments, point, false)
          (leftIndex - rightIndex) :: computePointsAndSegments(rightSegments, leftSegments, tail)
      }
      val (leftSegments, rightSegments) = segments.unzip
      val (rightSortedSegments, leftSortedSegments) = (mergeSort(rightSegments), mergeSort(leftSegments))
      computePointsAndSegments(rightSortedSegments, leftSortedSegments, points)
    }else {
      Nil
    }
  }
}
