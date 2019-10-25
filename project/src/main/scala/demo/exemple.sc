 lazy val y =3
 println("y");

y+9

y+2

 type x = Int forSome {type Int}
class Vehicle{
  def run(){
    println("vehicle is running")
  }
}

class Bike extends Vehicle{
  override  def run(){
    println("Bike is running")
  }
}

object MainObject{
  def main(args:Array[String]){
    var b = new Bike()
    b.run()
  }
} 