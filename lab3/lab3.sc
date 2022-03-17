// TASK: Lists and Recursion

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

// TASK 2: Tail Recursion

// 1. Calculate the length of a list with recursion
def listLength(ls:List[_]):Int = ls match {
  case Nil => 0
  case h :: tail => 1 + listLength(ls.tail)
}

listLength(list2) // Length = 10
list2.length // Length = 10

// 2. Create a much longer list from list2
1 to 15 foreach(x=> list2 = list2++list2)
listLength(list2)