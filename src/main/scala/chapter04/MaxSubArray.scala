package chapter04

/* This uses a divide and conquer approach, but Kadane's algorithn is linear and faster. */
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

def findMaxSubArrayKadanesAlgorithm(
    as: Vector[Int],
    low: Int,
    high: Int
): (Int, Int, Int) =
  var bestSum = 0
  var bestLeft = low
  var bestRight = low
  var currentSum = 0
  var currentLeft = low 
  var currentRight = low

  for j <- low to high
  do
    // currentSum = Math.max(as(j), currentSum + as(j))
    if as(j) > currentSum + as(j) then
      currentSum = as(j)
      currentLeft = j
      currentRight = j
    else
      currentSum += as(j)
      currentRight = j

    if currentSum > bestSum then
      bestSum = currentSum
      bestRight = currentRight
      bestLeft = currentLeft

  (bestLeft, bestRight, bestSum)
