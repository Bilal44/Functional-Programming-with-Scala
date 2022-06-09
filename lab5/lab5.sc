/*
  -------------------------------------------------
  Task 1. Partial function application and currying
  -------------------------------------------------
 */

// Example 1 – Partially applied function
// 1. Define and test a function isDivisible which has two integer parameters x and y
// and returns true if x is divisible by y, false otherwise.
val isDivisible = (x: Int, y:Int) => x % y == 0