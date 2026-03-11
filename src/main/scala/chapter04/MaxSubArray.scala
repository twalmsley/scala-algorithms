package chapter04

def findMaxSubArray(
    as: Vector[Int],
    low: Int,
    high: Int
): (Int, Int, Int) =
  if high == low then (low, high, as(low))
  else
    val mid = (low + high) / 2

    val (leftLow, leftHigh, leftSum) = findMaxSubArray(as, low, mid)
    val (rightLow, rightHigh, rightSum) = findMaxSubArray(as, mid + 1, high)
    val (crossLow, crossHigh, crossSum) =
      findMaxCrossingSubArray(as, low, mid, high)

    if leftSum >= rightSum && leftSum >= crossSum then
      (leftLow, leftHigh, leftSum)
    else if rightSum >= leftSum && rightSum >= crossSum then
      (rightLow, rightHigh, rightSum)
    else (crossLow, crossHigh, crossSum)

def findMaxCrossingSubArray(
    as: Vector[Int],
    low: Int,
    mid: Int,
    high: Int
): (Int, Int, Int) =
  var leftSum = Int.MinValue
  var rightSum = Int.MinValue

  var maxLeft = mid
  var maxRight = mid + 1

  var sum = 0
  for i <- (mid to low by -1)
  do
    sum += as(i)
    if sum > leftSum then
      leftSum = sum
      maxLeft = i

  sum = 0
  for j <- (mid + 1) to high
  do
    sum += as(j)
    if sum > rightSum then
      rightSum = sum
      maxRight = j

  (maxLeft, maxRight, leftSum + rightSum)
