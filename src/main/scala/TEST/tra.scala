package TEST

trait tra {
 def xx(i:Int): Int
}


class tt extends tra{
  final def xx(i:Int): Int={
  println("hhhhhhhh"+i)
  return i
  }

}


class tt2 extends tt{
 /* override def xx(i:Int): Int={
    println("hhhhhhhh"+(i+1))
    return i+1
  }
*/
}

object tesrrr{
  def main(args: Array[String]): Unit = {
    val t2= new tt().xx(3)

    val t= new tt2().xx(3)
  }
}