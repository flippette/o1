package o1.hofuncs
import o1.*

// This program is introduced in Chapter 6.1.

@main def task9() =
  val exampleNumbers = Vector(100, 25, -12, 0, 50, 0)
  testPhase1(exampleNumbers)
  testPhase2(exampleNumbers)

// PHASE 1/2:
// Implement turnElementsIntoResult so that it computes its return value by
// applying a given function (its third parameter) to the numbers contained in
// a given vector (its first parameter). The second parameter indicates the
// base value of the computation (which gets returned if the vector is empty).
//
// For example, say “numbers” contains the integers 20, 10, and 5; “initialValue”
// equals 0; and “operation” is a function that returns the sum of its two parameters.
//
//   1. The operation is first applied to initialValue and
//      the first number in the vector. 0 + 20 equals 20.
//   2. The operation is then applied to the previous result
//      and the next element. 20 + 10 = 30.
//   3. The operation is again applied to the previous result
//      and the next element. 30 + 5 = 35.
//   4. The last element has been processed, so it’s time to
//      stop and return 35.
//
// As you will have noticed, this example demonstrates one way of summing up
// a vector’s elements. Once you have this phase done, running the test program
// (task9, defined above) should print out the correct sum.
def turnElementsIntoResult(numbers: Vector[Int], initialValue: Int, operation: (Int, Int) => Int) =
  numbers.fold(initialValue)(operation)

def addToSum(oldSum: Int, nextNumber: Int) = oldSum + nextNumber

def testPhase1(nums: Vector[Int])  =
  println("The numbers are: " + nums.mkString(", "))
  println("Sum: " + turnElementsIntoResult(nums, 0, addToSum)) // should print: Sum: 163
  // The example output above assumes this has been called with the exampleNumbers vector.


// PHASE 2/2:
// Edit testPhase2 below. Use turnElementsIntoResult to compute the following:
//   1) the sum of the absolute values of each number in "nums";
//   2) how many positive numbers there are among "nums"; and
//   3) the product of all the other "nums" except for zeros.
// Replace the zero values in testPhase2 with calls to turnElementsIntoResult.
// In order to do that, flesh out the three helper functions below. You’ll need
// to design the helper functions yourself so that they work in combination with
// turnElementsIntoResult to produce the desired results.

def testPhase2(nums: Vector[Int])  =
  val sumOfAbsolutes = turnElementsIntoResult(nums, 0, addAbsolute)
  val howManyPositives = turnElementsIntoResult(nums, 0, positiveCount)
  val product = turnElementsIntoResult(nums, 1, productOfNonZeros)
  println("Sum of absolute values: " + sumOfAbsolutes)         // should print : Sum of absolute values: 187
  println("Number of positive elements: " + howManyPositives)  // should print: Number of positive elements: 3
  println("Product: " + product)                               // should print: Product: -1500000

// (Clarification: You’re not supposed to *call* turnElementsIntoResult from the three
// helper functions below. The only place where you’ll call turnElementsIntoResult is
// the testPhase2 program above. There, the helper functions will be useful in that
// you will *pass them as parameters* to turnElementIntoResult.)

def addAbsolute(acc: Int, n: Int): Int = acc + n.abs

def positiveCount(acc: Int, n: Int): Int = acc + (if n > 0 then 1 else 0)

def productOfNonZeros(acc: Int, n: Int): Int = acc * (if n != 0 then n else 1)

