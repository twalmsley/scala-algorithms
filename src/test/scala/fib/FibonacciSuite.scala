// For more information on writing tests, see
import scala.collection.mutable.HashMap
import org.scalatest.funsuite.AnyFunSuite
// https://scalameta.org/munit/docs/getting-started.html
class FibonacciSuite extends AnyFunSuite {
  test("fib1 is correct"):
    assertResult(fib1(-2), 0)
    assertResult(fib1(-1), 0)
    assertResult(fib1(0), 0)
    assertResult(fib1(1), 1)
    assertResult(fib1(2), 1)
    assertResult(fib1(3), 2)
    assertResult(fib1(4), 3)
    assertResult(fib1(5), 5)
    assertResult(fib1(6), 8)
    assertResult(fib1(7), 13)
    assertResult(fib1(8), 21)
    assertResult(fib1(9), 34)
    assertResult(fib1(10), 55)
    assertResult(fib1(11), 89)
    assertResult(fib1(12), 144)
    assertResult(fib1(13), 233)
    assertResult(fib1(14), 377)
    assertResult(fib1(15), 610)
    assertResult(fib1(16), 987)
    assertResult(fib1(17), 1597)
    assertResult(fib1(18), 2584)
    assertResult(fib1(19), 4181)

  test("fib2 is correct"):
    assertResult(fib2(-2, HashMap()), 0)
    assertResult(fib2(-1, HashMap()), 0)
    assertResult(fib2(0, HashMap()), 0)
    assertResult(fib2(1, HashMap()), 1)
    assertResult(fib2(2, HashMap()), 1)
    assertResult(fib2(3, HashMap()), 2)
    assertResult(fib2(4, HashMap()), 3)
    assertResult(fib2(5, HashMap()), 5)
    assertResult(fib2(6, HashMap()), 8)
    assertResult(fib2(7, HashMap()), 13)
    assertResult(fib2(8, HashMap()), 21)
    assertResult(fib2(9, HashMap()), 34)
    assertResult(fib2(10, HashMap()), 55)
    assertResult(fib2(11, HashMap()), 89)
    assertResult(fib2(12, HashMap()), 144)
    assertResult(fib2(13, HashMap()), 233)
    assertResult(fib2(14, HashMap()), 377)
    assertResult(fib2(15, HashMap()), 610)
    assertResult(fib2(16, HashMap()), 987)
    assertResult(fib2(17, HashMap()), 1597)
    assertResult(fib2(18, HashMap()), 2584)
    assertResult(fib2(19, HashMap()), 4181)

  test("fib2 is faster than fib1"):
    assert(time(n => fib1(n)) > time(n => fib2(n, HashMap())))
}

def time(f: Int => Int): Long =
  val start = System.nanoTime()
  f(30)
  val duration = System.nanoTime() - start
  println(s"Duration: $duration")
  duration
