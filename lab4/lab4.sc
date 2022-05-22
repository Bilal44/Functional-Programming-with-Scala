import scala.collection.immutable.ListSet

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

var airports = Map("Glasgow" -> "GLA", "Dubai" -> "DXB", "Berlin" -> "TXL")

// 2. Create a map moreAirports containing a single pair:
// Helsinki -> HEL
val moreAirports = Map("Helsinki" -> "HEL")