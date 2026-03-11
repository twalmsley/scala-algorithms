// For more information on writing tests, see
// https://scalameta.org/munit/docs/getting-started.html
class MySuite extends munit.FunSuite {
  test("fib1 is correct"):
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

}
