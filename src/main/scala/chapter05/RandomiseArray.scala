package chapter05

import scala.util.Random

def randomiseArray(xs: Vector[Int]): Vector[Int] =
  val copy = xs.toArray
  for i <- 0 to copy.length - 1
  do
    val tmp = copy(i)
    val rand = Random().between(i, copy.length)
    copy(i) = copy(rand)
    copy(rand) = tmp

  copy.toVector
