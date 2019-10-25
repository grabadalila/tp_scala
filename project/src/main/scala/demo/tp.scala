package demo

import breeze.linalg.{*, DenseMatrix, DenseVector, convert, eigSym, sum}
import breeze.numerics.sqrt
import breeze.stats.corrcoeff
import breeze.stats.variance
import breeze.stats.stddev

import breeze.stats.mean
import org.jfree.chart.{ChartFactory, ChartFrame}
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset

object tp {
  def main(args: Array[String]): Unit = {
    val mat:DenseMatrix[Double] = DenseMatrix((5.0,5.0,4.0,0.0,1.0,1.0), (4.0,3.0,3.0,2.0,2.0,1.0),(2.0,1.0,2.0,3.0,2.0,2.0), (5.0,3.0,5.0,3.0,4.0,3.0)
      ,(4.0,4.0,3.0,2.0,3.0,2.0),(2.0,0.0,1.0,3.0,1.0,1.0),
      (3.0,3.0,4.0,2.0,4.0,4.0),(1.0,2.0,1.0,4.0,3.0,3.0),(0.0,1.0,0.0,3.0,1.0,0.0),(2.0,0.0,1.0,3.0,1.0,0.0)
      ,(1.0,2.0,1.0,1.0,0.0,1.0),(4.0,2.0,4.0,2.0,1.0,2.0),(3.0,2.0,3.0,3.0,2.0,3.0)
      ,(1.0,0.0,0.0,3.0,2.0,2.0),(2.0,1.0,1.0,2.0,3.0,2.0))

    var moy: DenseMatrix[Double] = DenseMatrix.zeros(mat.rows, mat.cols)
   // var matrice_moy: DenseMatrix[Double] = DenseMatrix(mat.rows, mat.cols)
    var matrice_moy1: DenseMatrix[Double]=new DenseMatrix(mat.rows, mat.cols)
    var matrice_moy: DenseMatrix[Double]=new DenseMatrix(mat.rows, mat.cols)

   // var ecart: DenseVector[Double] = new DenseVector(mat.cols)
    var percent_cmule: DenseVector[Double] = new DenseVector(mat.cols)

    val moy1 = mean(mat(::, *))
    val ecart=stddev(mat(::,*))

  //  val eca:DenseVector[Double] = sqrt(variance(mat(::,*)).inner)
  //  println("ggggggg"+ecart)
    for (i<-0 until mat.cols)
      {
        val ecart3:Double=sqrt(sum(moy(::,i) :^ 2.0)/mat.rows.toDouble)

        matrice_moy1(::,i):=(mat(::,i)-moy1(i))/ecart(i)
        matrice_moy(::,i):=(mat(::,i)-moy1(i))/ecart3

      //   matrice_moy1=moy/ecart

      }

     println("ggggggg"+matrice_moy1)



     println("gpqosfùfoùqoùpfq"+matrice_moy1)





  }
}
