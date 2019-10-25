package TEST

object exemple {

  def r(x:Int): Int={
    var t=0
    for (y<- 1 until  x+1){
      if(y!=3)
      t+= y

    }
    return t;
  }

  def main(args: Array[String]): Unit = {
    println(r(10))

    def method(x :Int): String= x match {
      case 1=>"hhh";
      case 2=>"ggggg";
      case 3 =>"ggigiuuyiuy"
    }



   // println(method(0))
    val classe=new TEST()
    val test= classe.somme(5,6)
    println(test)
  }

}
