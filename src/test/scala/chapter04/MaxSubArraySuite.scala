package chapter04

class MaxSubArraySuite extends munit.FunSuite:

  test("find max crossing sub array"):
    val as =
      Vector(13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7)
    assertEquals(findMaxCrossingSubArray(as, 0, 3, 7), (3, 4, 17))

  test("find max sub array"):
    val as =
      Vector(13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7)
    assertEquals(findMaxSubArray(as, 0, 15), (7, 10, 43))
