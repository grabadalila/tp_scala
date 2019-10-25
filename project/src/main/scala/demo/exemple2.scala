package demo
//abstract class afficher{
  trait afficher{
  def print()
}



class A4 extends afficher{
  final  def print(){
    println("tester trait")
  }
}
class A5 extends A4{
 // override def print(){
   // println("changer le message")
 //}
}

object MainObject{
  def main(args:Array[String]){
    val a=new A4()
  //  var b = new A5()
    a.print()

  }
}