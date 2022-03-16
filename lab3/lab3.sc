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
def last(list: List[Int]): Int = list match {
  case h :: Nil => h
  case _ :: tail => last(tail)
}

last(list1)
last(List(1, 2, 3))
last(List(2))