// TASK 1

// Declare, print and try to reassign a value
val x = 10
print(x)
// val = 20

// Arithmetic operations and operators as methods
val y = 5
print(x+y)
x.+(y)
x./(y)

// TASK 2
def power(value:Int, power:Int):Int = {
  var result = 1
  for(i <- 0 to power-1) {
    result = result * value
  }
  result
}

power(2, 20)