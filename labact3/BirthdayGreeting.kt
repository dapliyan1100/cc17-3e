fun main() {
    val j = "John Lee"
   
    val age = 23 *365
    val Border =("==%%")
    val timesToRepeat =3
    println("Hello, world! ${j}")
    println("i am ${age}")
    println("${age} is the best age to learn Kotlin")
    
    //lets make a cake
    println("   ,,,,,   ")
    println("   |||||   ")
    println(" =========")
    println("@@@@@@@@@@@")
    println("{~@~@~@~@~}")
    println("@@@@@@@@@@@")
    println("")
    println("${j} is already ${age} days old")
    
    printBorder(Border,timesToRepeat)
    println("Happy Birthday! ${j}")
    printBorder(Border,timesToRepeat)
}
fun printBorder(Border:String,timesToRepeat:Int){
    
    repeat(timesToRepeat){
        print(Border)
        
    }
    println("")
    
}
