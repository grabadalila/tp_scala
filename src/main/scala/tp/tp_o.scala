import breeze.linalg.{*, DenseMatrix, DenseVector, convert, eigSym, sum}
import breeze.stats.corrcoeff
import breeze.stats.mean
import breeze.stats.stddev
import org.jfree.chart.{ChartFactory, ChartFrame}
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
object oo {

  def main(args: Array[String]): Unit = {

    val mat = DenseMatrix((5,5,4,0,1,1), (4,3,3,2,2,1),(2,1,2,3,2,2), (5,3,5,3,4,3) ,
      (4,4,3,2,3,2), (2,0,1,3,1,1), (3,3,4,2,4,4),
      (1,2,1,4,3,3),(0,1,0,3,1,0),(2,0,1,3,1,0) ,
      (1,2,1,1,0,1),(4,2,4,2,1,2),(3,2,3,3,2,3), (1,0,0,3,2,2), (2,1,1,2,3,2))


    val coef_cor=corrcoeff(convert(mat,Double))

    val es=eigSym(coef_cor)
    val val_propre=es.eigenvalues

    val vect_propre=es.eigenvectors

    println(sum(val_propre))

    val pourcent=val_propre*100.0/sum(coef_cor)
    //println(sum(mat))


    val cumule:DenseVector[Double]=new DenseVector[Double](val_propre.length)

    cumule(0)=pourcent(0)
    for(i<-1 until  val_propre.length ){
      cumule(i)=pourcent(i)+pourcent(i-1)
    }


    val lambda_s = DenseVector("lembda 1","lembda 2","lembda 3","lembda 4","lembda 5","lembda 6")
    val dataset= new DefaultCategoryDataset()
    for(i<-0 until val_propre.length) {
      println(val_propre(i))
      dataset.setValue(val_propre(i), "",lambda_s(i))
    }
    val frame=new ChartFrame("Histogramme lambda",
      ChartFactory.createBarChart("Histogramme Lambda test ","lambda yy",
        "lambda", dataset,PlotOrientation.VERTICAL,
        false,true,true))
    frame.pack()
    frame.setVisible(true)



    val mat_double=convert(mat,Double)
    val moyen=mean(mat_double(::,*))
    val ecart=stddev(mat_double(::,*))

    val centre= (mat_double(*,::)- moyen.inner)
    val reduite=centre(*,::)/ecart.inner
    println("jhkjhhhhhhhhhh"+reduite)

    // mat_double(::,*)+moyen.inner
    val centre_reduite =new DenseMatrix[Double](mat.rows,mat.cols)
    for(i<-0 until mat.cols){
      centre_reduite(::,i):= (mat_double(::,i)-moyen(i))/ecart(i)
    }


    val composant_principale=centre_reduite*vect_propre
    println(composant_principale)

  }
}

