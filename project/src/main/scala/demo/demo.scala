package linalg.matrix

import java.awt.{BasicStroke, Color}
import java.awt.geom.Arc2D
import java.util.Random
import java.awt.geom.Arc2D

import breeze.linalg.{DenseMatrix, _}
import breeze.numerics._
import breeze.stats.mean
import breeze.stats.stddev
import breeze.stats.corrcoeff
import breeze.stats.variance
import breeze.linalg._
import eigSym.EigSym
import breeze.plot.{Figure, scatter}
import breeze.stats.hist.Histogram
import com.lowagie.text.Image
import com.sun.javafx.geom.Arc2D
import com.sun.prism.BasicStroke
import com.sun.prism.paint.Color
import org.apache.commons.math3.stat.correlation.Covariance
import org.jfree.chart.annotations.XYShapeAnnotation
import org.jfree.data.statistics.HistogramDataset
import org.jfree.chart.{ChartFactory, ChartFrame, ChartUtilities, JFreeChart}
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.data.xy.{XYSeries, XYSeriesCollection}
import java.awt.geom.Arc2D
import java.util
import java.util.Random

import org.jfree.chart.annotations.XYLineAnnotation
import org.jfree.chart.annotations.XYShapeAnnotation
import org.jfree.chart.axis.NumberAxis
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.plot.XYPlot
import org.jfree.data.xy.XYDataset
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection;

object demo {

  def main(args: Array[String]) {





    val mat = DenseMatrix((5.0,5.0,4.0,0.0,1.0,1.0), (4.0,3.0,3.0,2.0,2.0,1.0),(2.0,1.0,2.0,3.0,2.0,2.0), (5.0,3.0,5.0,3.0,4.0,3.0)
      ,(4.0,4.0,3.0,2.0,3.0,2.0),(2.0,0.0,1.0,3.0,1.0,1.0),
      (3.0,3.0,4.0,2.0,4.0,4.0),(1.0,2.0,1.0,4.0,3.0,3.0),(0.0,1.0,0.0,3.0,1.0,0.0),(2.0,0.0,1.0,3.0,1.0,0.0)
      ,(1.0,2.0,1.0,1.0,0.0,1.0),(4.0,2.0,4.0,2.0,1.0,2.0),(3.0,2.0,3.0,3.0,2.0,3.0)
      ,(1.0,0.0,0.0,3.0,2.0,2.0),(2.0,1.0,1.0,2.0,3.0,2.0))


   // val mat =DenseMatrix((2.5,-3.0,0.5),(-3.0,5.0,-2.0),(0.5,-2.0,1.5))


  //  val mat =DenseMatrix((1.0,-0.85,0.26),(-0.85,1.0,-0.73),(0.26,-0.73,1.0))

//val matr=convert(mat,Double)
    val lambda_s = DenseVector("lembda 1","lembda 2","lembda 3","lembda 4","lembda 5","lembda 6")

    val ligne = DenseVector("I1","I2","I3","I4","I5","I6","I7","I8","I9","I10","I11","I12","I13","I14","I15")
    val colone = DenseVector("CUB","PUZ","CAL","MEM","COM","VOC")

    //matrice de corelation
   // val coeffmat=corrcoeff(mat)
    // println(corrcoeff(mat))

    //les valeur propre
    // val EigSym(lambda, evs) = eigSym(mat)
    val es = eigSym(mat)
    val lambda = es.eigenvalues
    val lambda2 = es.eigenvectors

    println("Les valeur Propre "+lambda)

    //val percent=(lambda/sum(lambda))
    val percent:DenseVector[Double] = (lambda*100.0 / sum(lambda))
    println("Les pourcentage  "+sum(lambda))
    //val percent_cmule:DenseVector[Double] = ;
    val percent_cmule:DenseVector[Double]= DenseVector.zeros[Double](percent.length);

    percent_cmule(0)=percent(0)
    for(i<-1 until percent.length) {
      percent_cmule(i)=  percent(i)+percent_cmule(i-1);
    }
    // les vecteur propre
    println("Les pourcentage cumulé " +  percent_cmule)


   /* val f = Figure()
    val p2 = f.subplot(0)
    p2 += hist(lambda,100)

    p2.setYAxisDecimalTickUnits()
    p2.setYAxisDecimalTickUnits()

    p2.ylim(0,4)
    p2.xlim(0,4)
    p2.title = "A normal distribution"
    p2.xlabel="rr"*/

    val dataset= new DefaultCategoryDataset()

    for(i<-0 until lambda.length) {
      println(ligne(i))
      dataset.setValue(lambda(i), "",lambda_s(i) )
    }

    val frame1=new ChartFrame("Histogramme lambda",
      ChartFactory.createBarChart("Histogramme Lambda test ","lambda yy",
        "lambda", dataset,PlotOrientation.VERTICAL,
        false,true,false))
    frame1.pack()
    //frame.setVisible(true)




    // ma trice centrer et reduite
    var moy = DenseMatrix.zeros[Double](mat.rows, mat.cols)
    val centrer_reduite = DenseMatrix.zeros[Double](mat.rows,mat.cols)
    for(i<-0 until mat.cols) {
      val moy1 = mean(mat(::, i))
      println("mprrr"+moy1)
      moy(::, i) := mat(::, i) - moy1  //ecart
      val ecart:Double=sqrt(sum(moy(::,i) :^ 2.0)/mat.rows.toDouble)
      centrer_reduite(::, i):= moy(::, i)/ecart
    }
    // println(centrer_reduite)

    //println(moy)




    //les  composants principales
    val evs = es.eigenvectors
    val n = mat * evs
     println(" composant"+n)


    //les contribution des individue
    val contribution = DenseMatrix.zeros[Double](mat.rows,mat.cols)
    val puiss = DenseMatrix.zeros[Double](mat.rows,mat.cols)

    val contribution2= (n:^2.0)
    val lam= n.rows.toDouble*lambda
    val contribution3= contribution2(*,::)/lam

    println("gggggggg"+contribution3(0,4))

    puiss :=n:^ 2.0

    for(i<-0 until n.cols) {
      contribution(::,i) := puiss(::,i)/(n.rows*lambda(i))*100.0
    }
    println(contribution(0,4))



    //les qualite des individue
    val cosin = DenseMatrix.zeros[Double](mat.rows,mat.cols)
    for(i<-0 until n.rows) {
      cosin(i,::) := puiss(i,::)/(sum(puiss(i,::)))
    }
      println("qualite "+cosin)




    //graphe 1
    val f2 = Figure("pca")
    val p3 = f2.subplot(2,2,0)
    p3 += scatter(n(::, 5), n(::, 4), { _ => 0.1})
    p3.setXAxisDecimalTickUnits()


    //les  composants principales
    val n_v = DenseMatrix.zeros[Double](mat.cols,mat.cols)
    for(i<-0 until n.cols) {
       n_v := centrer_reduite.t * n / (mat.rows.toDouble * sqrt(lambda(i)))
    }
     //println(n_v(::,5))

// contribution
 val puiss2 = DenseMatrix.zeros[Double](mat.cols,mat.cols)

    val cont_v = DenseMatrix.zeros[Double](mat.cols,mat.cols)
    for(i<-0 until n.cols) {
      puiss2(::,i):=n_v(::,i):^2.0
      cont_v(::,i) := puiss2(::,i)/lambda(i).toDouble
    }


    println("jjjjjj "+cont_v(::,5))


    //les qualite des individue
    val cosin_v= DenseMatrix.zeros[Double](mat.cols,mat.cols)
    val puiss3= DenseMatrix.zeros[Double](mat.cols,mat.cols)

    for(i<-0 until n_v.rows) {
      puiss3(::,i) :=n_v(::,i) :^ 2.0
   //   cosin_v(i,::) := puiss3(i,::)/(sum(puiss3(i,::)))
    }
    println(puiss3(::,5))

    val mat_c = DenseMatrix((0.0,0.0))
    //graphe 1
    val f3 = Figure("pca correlation")
    val p4 = f3.subplot(0)
    p4 += scatter(n_v(::, 5), n_v(::, 4), { _ => 0.03})
    p4 += scatter(mat_c(::,0), mat_c(::,1), { _=> 2.0})

   // p4.xlabel_=(ligne)
    //setsetYAxisDecimalTickUnits()
    p4.ylim(-1,1)
    p4.xlim(-1,1)

    val y = DenseVector.rand(20)
    val x = linspace(1, 10, 20)

    val fig = Figure("scatter plot")
    val plt = fig.subplot(0)
    //plt += plot(n_v(::, 5), n_v(::, 4), '+', "blue",ligne(0))



    ///

    println(sum(lambda))


    import java.awt.BasicStroke;
    import java.awt.geom.Arc2D;

    val dataset1: XYSeriesCollection = new XYSeriesCollection
    var series3: XYSeries=null  // la serie prend chaque foit des valeur differente (var)
    for(i<-0 until colone.length) {// parcourir tout les label ligne pour cree les 15 serie( point)
      series3 = new XYSeries(ligne(i)) // cree la serie sous le nom de l’individue, il exist 15 serie
      // ajouter les cordonner des point a chaque serie
      series3.add(n_v(i, 5), n_v(i, 4))
      //ajouter tout les serie dans l’ensemble la collection des donneés dataset.
      dataset1.addSeries(series3)
    }
    //


    /** @see http://stackoverflow.com/questions/6604211 */


    val chart4 = ChartFactory.createScatterPlot(
      "ArcTest", "X", "Y",dataset1,
      PlotOrientation.VERTICAL, true, true, false);

    val plot1 = chart4.getXYPlot();
    val arc = new Arc2D.Double(-1, -1, 2, 2, 90,190d, Arc2D.CHORD);
    val rr=new XYShapeAnnotation(arc, new BasicStroke(5.0f), java.awt.Color.blue)
    plot1.addAnnotation(rr);
    val frame = new ChartFrame("First", chart4);
    frame.pack();
    frame.setVisible(true);



  }}




