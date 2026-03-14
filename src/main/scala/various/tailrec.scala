import scala.annotation.tailrec

def f1(): Unit =

  @tailrec
  def loop(total: Int, l: List[Int]): Int =
    l match
      case Nil          => total
      case head :: tail => loop(head + total, tail)

  println(loop(0, (1 to 1000).toList))
