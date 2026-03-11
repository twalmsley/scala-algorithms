import scala.annotation.tailrec

def fib1(x: Int): Int = x match
  case 0 => 0
  case 1 => 1
  case _ => fib1(x - 1) + fib1(x - 2)
