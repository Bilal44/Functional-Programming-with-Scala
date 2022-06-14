/*
  -------------------------------------------------
  Task 1. Partial function application and currying
  -------------------------------------------------
 */

// Example 1 – Partially applied function
// 1. Define and test a function isDivisible which has two integer parameters x and y
// and returns true if x is divisible by y, false otherwise.
val isDivisible = (x: Int, y: Int) => x != 0 && x % y == 0

isDivisible(15, 3) // Returns true
isDivisible(15, 6) // Returns false

// 2. Define a variable isEven and assign to it the result of partially applying isDivisible
// as follows:
val isEven = isDivisible(_: Int, 2)

// 3. Test that you can apply isEven to a single parameter and check whether it is
// even, for example
isEven(10)
isEven(11)
isEven(82)
isEven(0)

// 4. Use isEven as a predicate in the filter method of List to transform
// List(1,2,3,4,5,6,7,8,9,10) to a list containing even numbers only.
val list = List(1,2,3,4,5,6,7,8,9,10)
val evenList = list.filter(isEven)

// Example 2 – Another partially applied function
// In lab 4 you worked with a map containing airport cities and codes. In this example you
// will create functions to iterate through and print the contents of a similar map.
// 1. Create the airports map:
var airports = Map("Glasgow" -> "GLA", "Dubai" -> "DXB", "Berlin" -> "TXL")

// 2. Define and test a function to print the keys and values in a map:
def printMap(mymap: Map[String,String]) = {
  for ((k, v) <- mymap) {
    println(s"$k - $v")
  }
}

printMap(airports)

// Note that this function will work only for a map where keys and values are strings.
// A more generic version of the function that could be used with other types for
// keys and values could be defined:
def printMapGeneric[A,B](mymap: Map[A,B]) = {
  for ((k, v) <- mymap) {
    println(s"$k - $v")
  }
}

printMapGeneric(airports)

// 3. Modify printMap as follows so that it becomes a higher-order function, where the
// second parameter is a function that determines how to print each key-value pair
def printMap(mymap: Map[String,String],f:(String,String)=> Unit)
= {
  for ((k, v) <- mymap) {
    f(k,v)
  }
}

// 4. Define the following function that can be used as a parameter – this function
// prints a key and value in the same format as the first version of printMap
def printKeyValue(k:String, v:String) = {
  println(s"$k - $v")
}

// 5. Define a similar function printValueOnly that prints out a value only in the
// following format:
// Value – GLA
def printValueOnly(k:String, v:String) = {
  println(s"Value - $v")
}

// 6. Test the higher-order printMap function using the airports map and each of the
// functions printKeyValue and printValueOnly
printMap(airports, printKeyValue)
printMap(airports, printValueOnly)