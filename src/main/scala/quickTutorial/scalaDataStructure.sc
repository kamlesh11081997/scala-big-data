// Data structure

//Tuples : Immutable list
val captainStuff = ("Picard","Enterprise-D","NCC-1701-D")
//the above tuple can be considered as single entity
println(captainStuff)
// accessing tuple elements : refer to the individual fields with a one-based index
println(captainStuff._1)
println(captainStuff._2)
println(captainStuff._3)
/*
output :
Picard
Enterprise-D
NCC-1701-D
*/

// syntactic sugar for tuple
val picardShip = "Picard" -> "Enterprise-D"
println(picardShip._1)
println(picardShip._2)

// tuple with different data types
val aBunchOfStuff= ("Kirk",1964,true)
println(aBunchOfStuff)

// List : like a tuple, but more functionality
// It must be of same data type

val shipList=List("Enterprise","Defiant","Voyager","Deep Space Nine")
// accessing list elements :zero based
println(shipList(1))
println(shipList.head) // output : Enterprise
println(shipList.tail) // output : List(Defiant, Voyager, Deep Space Nine)

// iterating over list
for (ship <- shipList) {println(ship)}
/*
Output :
Enterprise
Defiant
Voyager
Deep Space Nine
*/

// reversing the string in list
val backwardShips = shipList.map((ship:String)=>{ship.reverse})
for (ship <-backwardShips) println(ship)
// output: List(esirpretnE, tnaifeD, regayoV, eniN ecapS peeD)

// reduce function : reduce() to combine together all the items in a collection using some function
val numberList=List(1,2,3,4,5)
val sum = numberList.reduce((x:Int,y:Int)=>{x+y})
// the above statement is same as : sum = numberList.sum
println(sum) // output : 15

// filter function : to removes stuff
val iHateFives = numberList.filter((x:Int)=>x!=5)
println(iHateFives) // output : List(1, 2, 3, 4)

val iHateThree = numberList.filter((x:Int)=> x!=3)
println(iHateThree) // output : List(1, 2, 4, 5)

// concatenate the lists
val moreNumbers = List(6,7,8)
val lotsOfNumbers=numberList ++ moreNumbers
println(lotsOfNumbers) //output : List(1, 2, 3, 4, 5, 6, 7, 8)

// some common collection methods
val reversed = numberList.reverse // output : List(5, 4, 3, 2, 1)
val sorted = reversed.sorted // output : List(1, 2, 3, 4, 5)
val lotsOfDuplicates = numberList ++ numberList // output : List(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
val distinctValues = lotsOfDuplicates.distinct // output : List(1, 2, 3, 4, 5)
val maxValue= numberList.max // output : 5
val total= numberList.sum // 15
val hasThree = iHateThree.contains(3) // false


// Scala Maps : A Map is an Iterable consisting of pairs of keys and values
// ref : https://docs.scala-lang.org/overviews/collections/maps.html
val shipMap = Map("Kirk"->"Enteprise","Picard"->"Enterprise-D","Sisko"->"Deep Space Nine","Janeway"->"Voyager")
println(shipMap)
// Look up operation : Lookup operations apply, get, getOrElse, contains, and isDefinedAt
println(shipMap("Janeway")) //output : Voyager
println(shipMap.apply("Janeway")) // output : Voyager
println(shipMap get "Janeway") // Output : Some(Voyager)
println(shipMap getOrElse("Janeway","Not found")) // output : Voyager
println(shipMap getOrElse("Janeways","Not found")) // output : Not found
println(shipMap.contains("Janeway")) // output : true
println(shipMap isDefinedAt("Janeway")) // output : true

// subcollection :
println(shipMap.keySet) // output : Set(Kirk, Picard, Sisko, Janeway)
val keys=shipMap.keys  // it returns iterable of Set()
println(keys) // output : Set(Kirk, Picard, Sisko, Janeway)
val values = shipMap.values
println(values) // output : Iterable(Enteprise, Enterprise-D, Deep Space Nine, Voyager)
for (value <- values) println(value)
/*
output: Enteprise
Enterprise-D
Deep Space Nine
Voyager
*/

// addition and update : Immutable map
println(shipMap + ("Interstellar"->"Christopher Nolan")) // output : HashMap(Sisko -> Deep Space Nine, Picard -> Enterprise-D, Kirk -> Enteprise, Interstellar -> Christopher Nolan, Janeway -> Voyager)
println(shipMap - ("Interstellar")) // output : Map(Kirk -> Enteprise, Picard -> Enterprise-D, Sisko -> Deep Space Nine, Janeway -> Voyager)

















































