/*
  -------------------------------------------------
  Task 1. Partial function application and currying
  -------------------------------------------------
 */

// Example 1 – Partially applied function
// 1. Define and test a function isDivisible which has two integer parameters x and y
// and returns true if x is divisible by y, false otherwise.
val isDivisible = (x: Int, y: Int) => x % y == 0

isDivisible(15, 3) // Returns true
isDivisible(15, 6) // Returns false

// 2. Define a variable isEven and assign to it the result of partially applying isDivisible
// as follows:
val isEven = isDivisible(_: Int, 2)