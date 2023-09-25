/**
 * You can edit, run, and share this code.
 * play.kotlinlang.org
 */
fun main() {
    val age =24
    val layers = 5
    
    printCakeCandles(age)
    printCakeTop(age)
    printCakeBottom(age,layers)
    
}
fun printCakeCandles(age:Int){
    repeat(age){
        print("!")
        
    }
    println("")
    
}
fun printCakeTop(age:Int){
    repeat(age){
        print("=")
        
    }
    println("")
}
fun printCakeBottom(age:Int,layers:Int){
   repeat(layers){
       repeat(age){
           print("@")
           
       }
       println("")
   }
}
