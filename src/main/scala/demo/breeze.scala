package demo

import breeze.linalg.{DenseMatrix, DenseVector}


object breze {


  def main(args:Array[String]): Unit ={


    val vect1=DenseVector(1.0,2.0,3.0)
    val matr= DenseMatrix((1.0,2.0,3.0),(5.0,6.0,3.5))

    println(matr.t)
    println(matr(::,0))

   // val numList = List(1, 2, 3, 4, 5, 6)
    var i=0
    for( i<-vect1){ println(i)}

    println(3/8.0)

  }

}
