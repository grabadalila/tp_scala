package TEST

import breeze.linalg.{*, DenseMatrix, DenseVector, convert, eigSym, sum}
import breeze.plot.{Figure, scatter}
import breeze.stats.corrcoeff
import breeze.stats.mean
import breeze.stats.stddev
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.{ChartFactory, ChartFrame}
import org.jfree.data.category.DefaultCategoryDataset
object tpacp {

  val mat = DenseMatrix((5.0,5.0,4.0,0.0,1.0,1.0), (4.0,3.0,3.0,2.0,2.0,1.0),(2.0,1.0,2.0,3.0,2.0,2.0), (5.0,3.0,5.0,3.0,4.0,3.0)
    ,(4.0,4.0,3.0,2.0,3.0,2.0),(2.0,0.0,1.0,3.0,1.0,1.0),
    (3.0,3.0,4.0,2.0,4.0,4.0),(1.0,2.0,1.0,4.0,3.0,3.0),(0.0,1.0,0.0,3.0,1.0,0.0),(2.0,0.0,1.0,3.0,1.0,0.0)
    ,(1.0,2.0,1.0,1.0,0.0,1.0),(4.0,2.0,4.0,2.0,1.0,2.0),(3.0,2.0,3.0,3.0,2.0,3.0)
    ,(1.0,0.0,0.0,3.0,2.0,2.0),(2.0,1.0,1.0,2.0,3.0,2.0))

  def main(args: Array[String]): Unit = {
    val coef_cor=corrcoeff(convert(mat,Double))
    val es=eigSym(coef_cor)
    val val_propre=es.eigenvalues
    val vect_propre=es.eigenvectors

    val mat_double=convert(mat,Double)
    val moyen=mean(mat_double(::,*))
    val ecart=stddev(mat_double(::,*))

    val centre= (mat_double(*,::)- moyen.inner)
    val reduite=centre(*,::)/ecart.inner


    val composant_principale=reduite*vect_propre


    val puiss=composant_principale:^2.0
    val contribution=puiss(*,::)/(val_propre*composant_principale.rows.toDouble)
    println(contribution)







  }
}
