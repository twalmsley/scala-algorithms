package various

import org.scalatest.funsuite.AnyFunSuite

class MySetTestSuite extends AnyFunSuite:

  test("myset works as expected"):
    val s1: MySet[String] = MySet(1, 2, 3, 4, 5, 6)
      .filter(_ < 5)
      .map(x => s"$x")
      .flatMap(x => MySet(x))

    assert(s1.contains("1"))
    assert(s1.contains("2"))
    assert(s1.contains("3"))
    assert(s1.contains("4"))
    assert(!s1.contains("5"))

    s1.foreach(println)

  test("remove works correctly"):
    val s1: MySet[Int] = MySet(1, 2, 3, 4, 5) - 3
    assert(s1.contains(1))
    assert(s1.contains(2))
    assert(!s1.contains(3))
    assert(s1.contains(4))
    assert(s1.contains(5))

  test("intersection works correctly")
  val s1 = MySet(1, 2, 3, 4, 5) & MySet(3, 4, 6, 7)
  assert(!s1.contains(1))
  assert(!s1.contains(2))
  assert(s1.contains(3))
  assert(s1.contains(4))
  assert(!s1.contains(5))
  assert(!s1.contains(6))
  assert(!s1.contains(7))

  test("difference works correctly"):
    val s1 = MySet(1, 2, 3, 4, 5) -- MySet(3, 4, 6, 7)
    println(s1)
    assert(s1.contains(1))
    assert(s1.contains(2))
    assert(!s1.contains(3))
    assert(!s1.contains(4))
    assert(s1.contains(5))
    assert(s1.contains(6))
    assert(s1.contains(7))
