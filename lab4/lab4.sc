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
