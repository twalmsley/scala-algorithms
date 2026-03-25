package chapter04

import org.scalatest.funsuite.AnyFunSuite

class MaxSubArraySuite extends AnyFunSuite:
  val as =
    Vector(13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7)

  test("find max crossing sub array"):
    assertResult(findMaxCrossingSubArray(as, 0, 3, 7), (3, 4, 17))

  test("find max sub array"):
    assertResult(findMaxSubArray(as, 0, 15), (7, 10, 43))

  test("find max sub array using Kaldane's algorithm 1"):
    assertResult(findMaxSubArrayKadanesAlgorithm(as, 0, 15), (7, 10, 43))

  test("find max sub array using Kaldane's algorithm 2"):
    assertResult(findMaxSubArrayKadanesAlgorithm(as, 0, 7), (3, 3, 20))

  test("find max sub array using Kaldane's algorithm 3"):
    assertResult(findMaxSubArrayKadanesAlgorithm(as, 4, 9), (7, 8, 38))
