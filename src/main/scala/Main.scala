import scala.annotation.tailrec
import scala.collection.mutable.HashMap

def fib1(x: Int): Int = x match
  case 0 => 0
  case 1 => 1
  case _ => fib1(x - 1) + fib1(x - 2)

def fib2(x: Int, cache: HashMap[Int, Int]): Int = x match
  case 0 => 0
  case 1 => 1
  case _ =>
    cache.get(x) match
      case Some(y) => y
      case None =>
        val i = fib2(x - 1, cache) + fib2(x - 2, cache)
        cache(x) = i
        i
