// For more information on writing tests, see
import scala.collection.mutable.HashMap
// https://scalameta.org/munit/docs/getting-started.html
class FibonacciSuite extends munit.FunSuite {
  test("fib1 is correct"):
    assertEquals(fib1(-2), 0)
    assertEquals(fib1(-1), 0)
    assertEquals(fib1(0), 0)
    assertEquals(fib1(1), 1)
    assertEquals(fib1(2), 1)
    assertEquals(fib1(3), 2)
    assertEquals(fib1(4), 3)
    assertEquals(fib1(5), 5)
    assertEquals(fib1(6), 8)
    assertEquals(fib1(7), 13)
    assertEquals(fib1(8), 21)
    assertEquals(fib1(9), 34)
    assertEquals(fib1(10), 55)
    assertEquals(fib1(11), 89)
    assertEquals(fib1(12), 144)
    assertEquals(fib1(13), 233)
    assertEquals(fib1(14), 377)
    assertEquals(fib1(15), 610)
    assertEquals(fib1(16), 987)
    assertEquals(fib1(17), 1597)
    assertEquals(fib1(18), 2584)
    assertEquals(fib1(19), 4181)

  test("fib2 is correct"):
    assertEquals(fib2(-2, HashMap()), 0)
    assertEquals(fib2(-1, HashMap()), 0)
    assertEquals(fib2(0, HashMap()), 0)
    assertEquals(fib2(1, HashMap()), 1)
    assertEquals(fib2(2, HashMap()), 1)
    assertEquals(fib2(3, HashMap()), 2)
    assertEquals(fib2(4, HashMap()), 3)
    assertEquals(fib2(5, HashMap()), 5)
    assertEquals(fib2(6, HashMap()), 8)
    assertEquals(fib2(7, HashMap()), 13)
    assertEquals(fib2(8, HashMap()), 21)
    assertEquals(fib2(9, HashMap()), 34)
    assertEquals(fib2(10, HashMap()), 55)
    assertEquals(fib2(11, HashMap()), 89)
    assertEquals(fib2(12, HashMap()), 144)
    assertEquals(fib2(13, HashMap()), 233)
    assertEquals(fib2(14, HashMap()), 377)
    assertEquals(fib2(15, HashMap()), 610)
    assertEquals(fib2(16, HashMap()), 987)
    assertEquals(fib2(17, HashMap()), 1597)
    assertEquals(fib2(18, HashMap()), 2584)
    assertEquals(fib2(19, HashMap()), 4181)

  test("fib2 is faster than fib1"):
    assert(time(n => fib1(n)) > time(n => fib2(n, HashMap())))
}

def time(f: Int => Int): Long =
  val start = System.nanoTime()
  f(30)
  val duration = System.nanoTime() - start
  println(s"Duration: $duration")
  duration
