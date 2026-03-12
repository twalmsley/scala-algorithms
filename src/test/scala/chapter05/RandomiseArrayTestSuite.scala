package chapter05

class RandomiseArrayTestSuite extends munit.FunSuite:

  test("Randomise an array works"):
    val xs = (1 to 100).toVector
    val rand = randomiseArray(xs)

    // Unlikely to be the same as xs
    assertNotEquals(xs, rand)

    // Must contain all the values of xs
    for i <- xs do assert(rand.contains(i))
