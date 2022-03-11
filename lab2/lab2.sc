// TASK: List operations

// Declare a list as value
val myList = List(1, 3, 1, 7, 9, 5)

// 1. Return the head of the list
myList.head

// 2. Return the rest of the list
myList.tail

// 3a. Sum all elements
myList.sum

// 3b. Reverse the list
myList.reverse

// 4. Split the list in two halves
myList.splitAt(3)

// 5. Filter the list to return (1, 3, 1)
myList.filter(e => e < 4)