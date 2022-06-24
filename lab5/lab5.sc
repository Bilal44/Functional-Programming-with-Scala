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
printMap(airports, printKeyValue) // Prints key - value
printMap(airports, printValueOnly) // Prints "Value" - value

// 7. Define a variable printKeysValues and assign to it the result of partially applying
// printMap, specifying the function parameter only, as printKeyValue. The type of
// printKeysValues should be Map[String,String] => Unit.
val printKeysValues = printMap(_, printKeyValue)

// Test printKeyValues partial function
printKeysValues(airports)

// Example 3 – Curried function
// 1. Create a curried version of printMap, called printMap_curried. This will involve
// simply modifying the parameter list.
val printMap_curried = (printMap _).curried

// Test the curried function
printMap_curried(airports)(printValueOnly)

// 2. Test also by calling the curried function with a lambda expression as the second
// parameter list, and enclosing this in {} instead of () as follows:
printMap_curried(airports){
  (k,v)=> println(s"Key - $k")
}

// 3. How would this compare with calling printMap with a lambda? Which would be
// clearer and more readable?
printMap(airports, {
  (k,v)=> println(s"Key - $k")
})

printMap(airports, (k,_)=> println(s"Key - $k"))

/*
  -------------------------------------------
  Task 2. Map, flatMap and for comprehensions
  -------------------------------------------
 */

// Example 1 – Comparing map and flatMap
// In this task you will extract data from Scala maps using map and flatMap, and you will
// see how the same results can be achieved, more conveniently, using for
// comprehensions.

// 1. Create the following map as your data for this exercise (or continue from Task 1):
// var airports = Map(
// "Glasgow" -> "GLA",
// "Dubai" -> "DXB",
// "Berlin" -> "TXL")

// 2. Use the get method of Map to find the value (airport code) for a given key (city)
airports.get("Glasgow")

// 3. Try doing the same for a key that doesn't exist in the map
airports.get("Edinburgh")

// 4. Now you will search in the airports data for the airport codes for all cities in a list.
// Create the search list as follows:
var searchlist = List("Glasgow", "Edinburgh", "Berlin")

// Note that the search list includes Edinburgh, which is not included in the data.
// How do you think you would approach this problem using an imperative
// approach? In Scala, it’s a one-liner.
val codes = searchlist.map(x => airports.get(x))

// 5. Modify the expression for codes to use flatMap instead of map, and describe the
// difference this makes to the result.
val codes_flatmap = searchlist.flatMap(x => airports.get(x))

// Example 2 – for expression
// Use for comprehension to achieve the same result as flatMap function
val codes_for = for{
  x <- searchlist
  y <- airports.get(x)
} yield y

// Example 3 – Chaining functions
// In example 1 you obtained a list of airport codes for the airports data based on a list of
// search values. Now you will apply a further transformation to the result extracted from
// the data by converting all the codes to lower case

// 1. Enter the following code, which applies the function toLowerCase to each
// element in the result of the search.
// val codes_lower = searchlist.map(x => airports.get(x)).map(y=>y.toLowerCase)

// 2. Modify the expression for codes_lower to use flatMap instead of map, and
// describe the difference this makes. Why does the use of flatMap allow these
// transformations to be chained?
val codes_lower = searchlist.flatMap(x => airports.get(x)).map(y=>y.toLowerCase)

// 3. Write an equivalent for expression to achieve the same result. This will involve
// changing only the yield expression in example 2.
val codes_lower_for = for{
  x <- searchlist
  y <- airports.get(x)
} yield y.toLowerCase

// Example 4 – A more complex for comprehension
// The final example is a bit more complicated, to demonstrate more of the power of for
// comprehensions. You will define the search using the keys of a map rather than a list,
// and you will combine the values in the search map with the values extracted from the
// data.

// 4. Create an initial search map:
var searchmap = Map("Glasgow" -> "Scotland", "Edinburgh" -> "Scotland", "Berlin" -> "Germany")
// The aim of this exercise is to find the airports, if any, matching the keys in these
// map, and for each result found, combine the code with the matching country
// name in the search map, to get the result:
//  List(GLA - Scotland, TXL - Germany)