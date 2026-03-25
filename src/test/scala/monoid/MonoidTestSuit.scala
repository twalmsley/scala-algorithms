package monoid

import munit.FunSuite
import SemigroupSyntax._
import IntInstances.given
import StringInstances.given

class MonoidTestSuite extends FunSuite:

  test("some monoid examples"):

    val x = fold(List(1, 2, 3))
    val y = fold(List("1", "2", "3"))

    assertEquals(x, 6)
    assertEquals(y, "123")
