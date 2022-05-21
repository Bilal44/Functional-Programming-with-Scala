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