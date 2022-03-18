/*
--------------------------
 TASK: Lists and Recursion
--------------------------
 */

// 1. Declare a list using 'cons' syntax
val list1 = 1 :: 2 :: 3 :: 4 :: 5 :: 6 :: 7 :: 8 :: 9 :: 10 :: Nil

// 2. Declare the same list as a variable using range function
var list2 = List.range(1, 11)

// 3a. Join both lists using ::
list1 :: list2

// 3b. Join both lists using ::: and observe the difference
list1 ::: list2

// 3c. Join both lists using ++
list1 ++ list2

// 4. Define a function to get the tail of a list
def last(list: List[Int]): Int = {
  if (list.tail != Nil) last(list.tail) else list.head
}

last(list1) // Tail = 10
last(List(1, 2, 3)) // Tail = 3
last(List(2)) // Tail = 2

// 5. Using if-else block to get the last element of a list
def lastIf(ls: List[Int]): Int = {
  if (ls.tail == Nil)
    ls.head
  else
    lastIf(ls.tail)
}

lastIf(list1) // Tail = 10

// 6. Use pattern matching to get the last item of a list
def lastPattern(ls: List[Int]): Int = ls match {
  case h :: Nil => h
  case _ :: tail => last(tail)
}

lastPattern(list1) // Tail = 10


// 7. Use recursion to get the sum of all elements
def Sum(ls: List[Int]): Int = ls match {
  case h :: Nil => h
  case _ :: tail => ls.head + Sum(tail)
}

Sum(list1)  // Sum = 55
list1.sum   // Sum = 55

/*
-----------------------
 TASK 2: Tail Recursion
-----------------------
 */

// 1. Calculate the length of a list with recursion
def listLength(ls:List[_]):Int = ls match {
  case Nil => 0
  case h :: tail => 1 + listLength(ls.tail)
}

listLength(list2) // Length = 10
list2.length // Length = 10

// 2. Create a much longer list from list2
1 to 15 foreach(x=> list2 = list2++list2)
// listLength(list2) // StackOverflowError

// 3. Calculate the length of a list with tail-recursion
def listLengthTailRecursive(ls:List[_]):Int = {
  def listLength_nested(ls:List[_], len: Int):Int = ls match{
    case Nil => len
    case _ :: tail => listLength_nested(tail, len+1)
  }
  listLength_nested(ls, 0)
}

listLengthTailRecursive(list2) // Length = 327680

// 4. Tail recursive version of the earlier sum function
def listSumTailRecursive(ls:List[_]):Int = {
  def listSum_nested(ls: List[_], runningSum: Int): Int = ls match {
    case Nil => runningSum
    case h :: tail => listSum_nested(tail, runningSum + h.toString.toInt)
  }

  listSum_nested(ls, 0)
}

listSumTailRecursive(list2) // Sum = 1802240
list2.sum // Sum = 1802240

// 5. Tail recursive version of the power function from lab 2
def power(value:Int, pow:Int): BigInt = {
  def power_nested(pow:Int, ans:Int): BigInt = pow match {
    case 0 => ans
    case _ => power_nested(pow - 1, ans * value) // tail recursion
  }
  power_nested(pow, 1)
}

power(12, 8) // Ans = 429981696

// 6a. Pattern match version of Fibonacci number calculator
def fibPattern(n: Int): Int = n match {
  case 0 => 0
  case 1 => 1
  case _ => fibPattern(n - 1) + fibPattern(n - 2)
}

fibPattern(6) // Returns 8