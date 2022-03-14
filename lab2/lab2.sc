// TASK: List operations //

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

// 6. Count the number of '1' elements
myList.filter(e => e == 1).length

// 7. Product (List(5, 1), List(3, 1)) in max 3 chained funcs
myList.reverse.filter(e => e < 6).splitAt(2)

// 8. Chain two calls to find the lowest value excluding
// the first three elements
myList.slice(3, myList.length).min


// TASK: String //

val myString = "A Santa Lived As a Devil At NASA"

// 1. Reverse the string
myString.reverse

// 2. Function to check for palindrome ignoring spaces and casing
def IsPalindrome(string: String): Boolean = {
  val stringWithoutSpaces = string.replaceAll("[\\W\\S]", "")
  return stringWithoutSpaces.toLowerCase() == stringWithoutSpaces.reverse.toLowerCase()
}

IsPalindrome(myString)

// 3a. Remove the first word and run palindrome check
val myStringWithoutFirstWord = myString.substring(myString.indexOf(" ") + 1)
IsPalindrome(myStringWithoutFirstWord)

// 3b. Count the number of spaces in myString
val numOfSpaces = myString.count(_ == ' ')

// 3c. Count the number of distinct characters excluding spaces
myString.length - numOfSpaces

// 4. Refactor IsPalindrome to return true for the challenge string
val challengeString = "A man, a plan, a canal, Panama"
IsPalindrome(challengeString)

// TASK: Recursion //

// 1. Pattern matching factorial function
def factorial(n: Int): BigInt = n match {
  case 0 => 1
  case _ => factorial (n-1) * n
}

factorial(10)

// 2. Calculate greatest common denominator using pattern matching
def gcd(a: Int, b: Int): Int = b match {
  case 0 => a
  case _ => gcd(b, a % b)   // tail recursion
}

gcd(15, 12)
gcd(24, 10)