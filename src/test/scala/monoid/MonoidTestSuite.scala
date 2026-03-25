package monoid

import SemigroupSyntax._
import IntInstances.given
import StringInstances.given
import org.scalatest.funsuite.AnyFunSuite

class MonoidTestSuite extends AnyFunSuite:

  test("some monoid examples"):

    val x = fold(List(1, 2, 3))
    val y = fold(List("1", "2", "3"))

    assert(x == 6)
    assertResult(y, "123")
