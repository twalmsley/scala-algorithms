
@main def intSwap():Unit =
  var x = 99
  var y = 45

  println(s"$x, $y")
  x = x ^ y
  y = y ^ x
  x = y ^ x
  println(s"$x, $y")

