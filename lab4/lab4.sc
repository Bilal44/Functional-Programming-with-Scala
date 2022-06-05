import scala.collection.immutable.ListSet
import scala.language.postfixOps

/*
  ----------------------------
  Task 1 - Mapping and Folding
  ----------------------------
 */

// 1. Use map to transform the list List("1","2","3","4","5") to the list of integers List(1,2,3,4,5)
val ls = List("1","2","3","4","5")
val intList = ls.map(_.toInt)

// 2. Use map to transform the list List("aa","bb","cc","dd","ee") to the list of strings List("AA","BB","CC","DD","EE")
val lowerLs = List("aa","bb","cc","dd","ee")
val upperLs = lowerLs map {_.toUpperCase}

// 3. Create a list of integers list containing the values 1 to 19
val list = List.range(1, 20)

// 4. Define and test a function sum that uses the the foldLeft
// method of List to evaluate the sum of the list elements
def sum1(list:List[Int]): Int = {
  list.foldLeft(0)(_ + _)
}

sum1(list) == list.sum // Returns true

// 5. Define and test a function length that uses the
// foldLeft method to compute the length of the list
def len1(list:List[Int]): Int = {
  list.foldLeft(0){ (x, _) => x + 1 }
}

len1(list) == list.length // Returns true

// 6. Define and test a function average that uses the foldLeft
// method to compute the average (mean) of the list elements
def avg1(list:List[Int]): Float = {
  list.foldLeft(0)(_ + _).toFloat/list.foldLeft(0){ (x, _) => x + 1 }
}

avg1(list) == list.sum.toFloat/list.length // Returns true
avg1(list) == list.sum/list.length // Returns true

// 7. Define and test a function last that uses the foldLeft
// method to find the last element of the list
def last1(list:List[Int]): Int = {
  list.foldLeft(list.head)((_, y) => y)
}

// A more genericised version of 7
def lastGeneric[A](list:List[A]): A = {
  list.foldLeft(list.head)((_, y) => y)
}

lastGeneric(list) == list.last  // Returns true
last1(list) == list.last // Returns true

// Alternative approach to compute running average (mean)
// of the list elements using pattern-matching and foldLeft
// Source: https://oldfashionedsoftware.com/2009/07/30/lots-and-lots-of-foldleft-examples/
def avg(list: List[Int]): Float = list match {
  case head :: tail =>
    tail.foldLeft((head.toFloat, 1.0.toFloat)) { (x, y) =>
      println(s"(${x._1} + ($y / ${x._2})) * ${x._2} / (${x._2} + 1), ${x._2 + 1})")
      ((x._1 + (y / x._2)) * x._2 / (x._2 + 1), x._2 + 1)
    }._1
}

avg(list) == avg1(list) // Returns true

// 8. Test the difference between foldLeft and foldRight
// with the help of Scala documentation for the List class

// 8a. Comparing foldLeft and foldRight for adding list elements
def sum2(list:List[Int]): Int = {
  list.foldRight(0)(_ + _)
}

// No changes needed as the sum will remain the same whether
// the addition starts from the beginning or the end of list
sum2(list) == sum1(list) // Returns true

// 8b. Comparing foldLeft and foldRight for counting list elements
def len2(list:List[Int]): Int = {
  // Slight change needed as the parameter order is reversed
  // for foldRight, 1st param being the element value and the
  // 2nd param being the accumulator
  list.foldRight(0){ (_, y) => y + 1 }
}

len2(list) == len1(list) // Returns true

// 8c. Comparing foldLeft and foldRight to return the last item of the list
def last2(list:List[Int]): Int = {
  // Returns the first element of the list as foldRight traverses
  // the list from right to left
  list.foldRight(0){(x, _) => x }
}

last2(list) == list.head // Returns true

// 9. (Challenge) Define and test functions that take a parameter of type List[Int] and
// compute, using foldLeft, each of the following:

// 9a. The penultimate element of the list
def penultimate[A](list: List[A]): A = {
  list.foldLeft(list.head, list.tail.head)((x, y) => (x._2, y))._1
}

penultimate(list) == list(list.length - 2) // Returns true

// 9b. The list in reverse order
def reverse[A](list: List[A]): List[A] = {
  list.foldLeft(List[A]())((x, y) => y :: x)
}

reverse(list) == list.reverse // Returns true

// 9c. The distinct elements of the list
def distinct[A](list: List[A]): List[A] = {
  list.foldLeft(List[A]())((x, y) => if (!x.contains(y)) x :+ y else x)
}

// Test with a list containing the elements (1,2,2,3,3,4,5)
// the result should contain no repeated values
val repeatedList = List(1,2,2,3,3,4,5)
distinct(repeatedList) == repeatedList.distinct // Returns true

// Alternative solution for distinct elements, using Set[A] also
// returns unique elements however does not guarantee ordering
def distinctSet[A](list: List[A]): List[A] = {
  list.foldLeft(ListSet[A]())((x, y) => (x + y)).toList
}

distinctSet(repeatedList) == repeatedList.distinct // Returns true

/*
  -------------
  Task 2 - Maps
  -------------
 */

// 1. Create a mutable map airports containing the following key/value pairs
// representing cities and the codes of their airports (as strings):
// Glasgow -> GLA
// Dubai -> DXB
// Berlin -> TXL

val airports = collection.mutable.Map("Glasgow" -> "GLA", "Dubai" -> "DXB", "Berlin" -> "TXL")

// 2. Create a map moreAirports containing a single pair:
// Helsinki -> HEL
val moreAirports = Map("Helsinki" -> "HEL")

// 3. Create a map evenMoreAirports containing the pairs:
// Glasgow -> PIK
// Los Angeles -> LAX
val evenMoreAirports = Map("Glasgow" -> "PIK", "Los Angeles" -> "LAX")

// 4. Create a new map newAirports by concatenating moreAirports
// and evenMoreAirports using the ++ operator
val newAirports = moreAirports ++ evenMoreAirports

// 5. Add (concatentate) newAirports to airports using
// the ++= operator. Look at the result – what happened to Glasgow?
airports ++= newAirports // The value of Glasgow updated to PIK

// 6. Add the following single entry to airports using the += operator.
// Tokyo -> HAN
airports += "Tokyo" -> "HAN"
println(airports)

// Extracting data from maps

// 1. Get the keys of a map as a list using the keys method
val cities = airports.keys.toList

// 1b. Get all the airport codes as a list
val codes = airports.values.toList

// 2. Get the value for a specified key
val gla = airports.get("Glasgow") // Returns an Option[String]

// 3. Extract the value from the Option using pattern matching
val gla2 = airports.get("Glasgow") match {
  case Some(ap) => ap
  case None => "not found"
}

// 3a. Unsafe extraction of the value from a map using a valid key
val gla3 = airports("Glasgow")

// 4. Use find method to extract the pair from the map
airports.find(_._2 == "LAX")

// 4a. Force to retrieve the only desired value (key/airport name)
airports.find(_._2 == "LAX").get._1

// 4c. Define a default value to safely extract the value with find
val default = ("not found","")
// The key-value pair exists and is returned
airports.find(_._2 == "HAN").getOrElse(default)._1
// The key-value pair does not exist and default is returned
airports.find(_._2 == "GLA").getOrElse(default)._1

// ---------
// Iterating
// ---------

// 1. Print the airports map keys using for expression
for (airport <- airports) {
  println(s"Code - ${airport._1}")
}

// 1b. Print the airports map keys using foreach HOF
airports.foreach {
  case (k, _) => println(s"Code - $k")
}

// 1c. Print the contents of airports map using for expression
for ((k, v) <- airports) {
  println(s"City:$k - Code:$v")
}

// 1d. Print the contents of airports map using foreach HOF
airports.foreach {
  case (k, v) => println(s"City:$k - Code:$v")
}

/*
  ---------------------------
  Task 3 - Tuples and Zipping
  ---------------------------
 */

// Create a tuple and access its elements
val tuple = ("apple", "red")
val fruit = tuple._1
val colour = tuple._2

// Declare a tuple with elements of different types
val getUserInfo = ("Al", 42, 200.0)

// Extract several tuple elements at once and assign them to named variables
val (name, age, weight) = getUserInfo

// 1. Test the variables name, age and weight against the expected values
(name == "Al") && (age == 42) && (weight == 200.0)

// 2. Define a list of tuples
val fruits = List(("apple", "red"), ("banana", "yellow"), ("orange", "orange"))

// 2a. Convert the tuple list to a map (possible because the tuple contains exactly two values)
val fruitMap = fruits.toMap

// 2b. Declare a tuple of lists
val lists = (List(1,2,3), List(4,5,6), List(7,8,9))

// 2c. Create a map of fruits/colours using the above code
// and print the contents by iterating to give:
// Fruit:apple – Colour:red
// Fruit:banana – Colour:yellow
// Fruit:orange – Colour:orange
fruitMap foreach {
  tuple => println(s"Fruit:${tuple._1} - Colour:${tuple._2}")
}

// 3. Create two separate lists of cities and codes:
val cities = List("Glasgow", "Dubai", "Berlin")
val codes = List("GLA","DXB","TXL")

// 4. Evaluate the following expression, which uses the zip operator, and describe the
// result and the effect of zip
val citiesWithCode = cities zip codes

// 5. Use the zip operator and a suitable method call to create a map of cities/codes
// and print the contents by iterating
citiesWithCode.foreach {
  case (city, code) => println(s"City:$city - Code:$code")
}

// 6. Create three lists representing golf players and their scores in two
// rounds of a tournament
val players = List("Stenson", "Mickelson", "Galllacher")
val round1 = List(70, 68, 70)
val round2 = List(65, 72, 68)

// 7. Create a list of tuples contain scores from both rounds
val scores1 = round1 zip round2

// 7b. Print the tuple list containing the scores
scores1 foreach(println)

// 8. Create a new list that contains the total score for each player
val scores2 = round1 zip round2 map{
  case (x, y) => x + y
}

// 8b. Print the total score for each player
scores2 foreach(println)

// 9. Finally, use zip again to create a map of players and total scores and print the
// contents by iterating, to give:
// Player:Stenson - total score 135
// Player:Mickelson - total score 140
// Player:Galllacher - total score 138

val playersWithScore = players zip scores2 toMap

playersWithScore foreach {
  case (p, s) => println(s"Player:$p - total score $s")
}

/*
  -----------------------------------------------------------------
  Personal Bonus Task - Compare the speed of various list functions
  -----------------------------------------------------------------
 */

// Function to measure the execution time
def time[R](block: => R): R = {
  val t0 = System.nanoTime()
  val result = block    // call-by-name
  val t1 = System.nanoTime()
  println("Elapsed time: " + (t1 - t0) + "ns")
  result
}

// Compute the time to populate a list via 'to' function
time(1 to 1000 toList)

// Compute the time to populate a list via 'range' function
time(List.range(1, 1000))

// Compute the time to map list elements via 'list' function
time(list.map(x => x))

// Compute the time to map list elements via 'yield' function
time(for(l <- list) yield l)

// Compute the time to filter a list using an if statement
time(for {
  i <- 1 to 10
  if i % 2 == 0
} yield i)