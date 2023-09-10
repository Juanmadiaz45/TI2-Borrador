object MergeList {
  def mergeList[T](xs: List[List[T]]): List[T] = {
    def merge(l1: List[T], l2: List[T]): List[T] = (l1, l2) match {
      case (Nil, _) => l2
      case (_, Nil) => l1
      case (m::ms, n::ns) => m::merge(ms, l2)
    }
    val n = xs.length / 2
    if (n == 0) xs.flatten
    else {
      val (l1, l2) = xs splitAt n
      merge(mergeList(l1), mergeList(l2))
    }
  }
}

val input = List(List("Is", "this", "a", "real", "life"), List("Another", "one", "bites", "the", "dust"), List("Is", "this", "love", "that", "I'm", "feeling"))
val result = MergeList.mergeList(input)
println(result)