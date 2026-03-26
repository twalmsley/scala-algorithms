import cats._
import cats.data._
import cats.syntax.all._
import org.scalatest.funsuite.AnyFunSuite
import scala.util.Try
import scala.util.Success
import scala.util.Failure

class CatsTestSuite extends AnyFunSuite:

  def parseInt(s: String): Try[Int] = Try(Integer.parseInt(s))
  def parseInts(xs: List[String]): Try[List[Int]] = xs.traverse(parseInt)
  val strList = List("1", "2", "3", "4")
  val intList = List(1, 2, 3, 4)

  test("some cats examples - 1"):

    val x: Int = intList.foldl(0)(_ + _)
    val y: String = strList.foldl("")(_ + _)

    assert(x == 10)
    assert(y == "1234")

  test("some cats examples - 2"):

    val z1 = parseInts(strList)
    assert(z1 == Success(List(1, 2, 3, 4)))

    for ns <- z1
    do ns.foldl(0)(_ + _)

    val total = z1 match
      case Success(ns) => ns.foldl(0)(_ + _)
      case _           => -1
    assert(total == 10)

    val sum = z1.map(_.fold(0)(_ + _))
    assert(sum == Success(10))

  test("some cats examples - 3"):

    val strList = List("1", "2", "3", "y")
    val z1 = parseInts(strList)
    assert(
      z1.toString() == Failure(
        new NumberFormatException("For input string: \"y\"")
      ).toString()
    )

  test("sum and length of a list in a single pass"):

    val f: ((Int, Int), Int) => (Int, Int) = (p, n) => (p._1 + n, p._2 + 1)
    def sumLength(xs: List[Int]): (Int, Int) =
      xs.foldl((0, 0))(f)

    assert(sumLength(intList) == (10, 4))

  test("partition failures using Alternative.separate"):
    def parse(s: String): Either[Throwable, Int] =
      Try(Integer.parseInt(s)) match
        case Success(i) => Right(i)
        case Failure(e) => Left(e)

    val strList = List("1", "x", "3", "y")

    // This returns (List[Left[Throwable]], List[Right[Int]])
    val partitionedResults1 = strList.map(parse).partition(_.isLeft)
    println(partitionedResults1)

    // This returns (List[Throwable], List[Int]) and is easier to process
    val partitionedResults2 = strList.map(parse).separate
    println(partitionedResults2)

    // This returns (List[Throwable], List[Int]) and is easier to process, but the code is harder to read.
    val partitionedResults3 = (parse.pure[List] ap strList).separate
    println(partitionedResults3)
