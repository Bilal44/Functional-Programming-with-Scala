/*
  ----------------------------
  Task 1 - Mapping and Folding
  ----------------------------
 */

// 1. Use map to transform the list List("1","2","3","4","5") to the list of integers List(1,2,3,4,5)
val ls = List("1","2","3","4","5")
val intList = ls.map(_.toInt)