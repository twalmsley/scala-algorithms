package monoid

import cats._
import cats.data._
import cats.syntax.all._
import org.scalatest.funsuite.AnyFunSuite

class MonoidTestSuite extends AnyFunSuite:

  def parseInt(s: String): Option[Int] =
    scala.util.Try(Integer.parseInt(s)).toOption

  test("some monoid examples"):

    val w = List("1", "2", "3").map(parseInt)
  
    val x: Int = Foldable[List].fold(List(1, 2, 3))
    val y: String = Foldable[List].fold(List("1", "2", "3"))

    assert(x == 6)
    assert(y == "123")

    val z1 = Traverse[List].traverse(List("1", "2"))(parseInt)
    assert(z1 == Some(List(1, 2)))
