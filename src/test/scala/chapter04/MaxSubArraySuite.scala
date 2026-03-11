package chapter04

class MaxSubArraySuite extends munit.FunSuite:
  val as =
    Vector(13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7)

  test("find max crossing sub array"):
    assertEquals(findMaxCrossingSubArray(as, 0, 3, 7), (3, 4, 17))

  test("find max sub array"):
    assertEquals(findMaxSubArray(as, 0, 15), (7, 10, 43))

  test("find max sub array using Kaldane's algorithm 1"):
    assertEquals(findMaxSubArrayKadanesAlgorithm(as, 0, 15), (7, 10, 43))

  test("find max sub array using Kaldane's algorithm 2"):
    assertEquals(findMaxSubArrayKadanesAlgorithm(as, 0, 7), (3, 4, 17))
