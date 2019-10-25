package demo

import java.awt.BasicStroke
import java.awt.geom.Arc2D
import java.math.BigDecimal

import breeze.linalg.{*, Axis, DenseMatrix, DenseVector, convert, eigSym, max, sum, trace}
import breeze.numerics.sqrt
import breeze.stats.covmat
import breeze.stats.corrcoeff
import org.jfree.chart.{ChartFactory, ChartFrame}
import org.jfree.chart.annotations.XYShapeAnnotation
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.xy.{XYSeries, XYSeriesCollection}

object afc {
  def main(args: Array[String]): Unit = {
    val mat = DenseMatrix((68, 119, 26, 7), (15, 54, 14, 10), (5, 29, 14, 16), (20, 84, 17, 94))
    val colonne = DenseVector("brun","chatain","roux","blanc")
    val ligne = DenseVector("marron","noisette","vert","bleu")

  // val mat = DenseMatrix((10,0,0), (0,9,1), (0,3,7))
    //val colonne = DenseVector("ps","pa","pam")
    //val ligne = DenseVector("s","a","am")


    val mat2: DenseVector[Double] = DenseVector.zeros[Double](mat.cols);
    val proba: DenseMatrix[Double] = DenseMatrix.zeros[Double](mat.rows, mat.cols);
    val res: DenseMatrix[Double] = DenseMatrix.zeros[Double](mat.cols, mat.cols);
    val cols_cov: DenseMatrix[Double] = DenseMatrix.zeros[Double](mat.rows, mat.cols);
    //  val marg_col:DenseVector[Double]= DenseVector.zeros[Double](mat.cols);

    // val marg_ligne:DenseVector[Double]= DenseVector.zeros[Double](mat.cols);
    val mat_d = convert(mat, Double)
    // println(mat_d)
    // calculer la matrice de probabile
    val total = sum(mat_d).toDouble
    //println(total)

    val proba2 = mat_d / total

    val marg_col = sum(mat_d(::, *)).inner / total
    val marg_ligne = sum(mat_d(*, ::)) / total

    // println( marg_col)
    //println( marg_ligne)
    //println( proba2)


    val profil_cols = proba2(*, ::) / marg_col
    //   println("Profil colonne '\n'"+ profil_cols.t)

    val profil_ligne = proba2(::,*) / marg_ligne
    //println("Profil ligne '\n'"+  profil_ligne)




    val ponderer = profil_ligne(*,::) / sqrt(marg_col)
    //println("Les valeur Propre " + ponderer)


    val ponderer_c = profil_cols(*,::) / sqrt(marg_ligne)
    //println("Les valeur Propre " + ponderer_c)





    for ( l<- 0 until ponderer.cols ) {
      for ( c<- 0 until ponderer.cols ) {
        var prob=0.0

         println(ponderer(l, c)+"tt  "+sqrt(marg_col(c)))
        for ( d<- 0 until ponderer.rows) {
          prob+=  (marg_ligne(d)*((ponderer(d, l) - sqrt(marg_col(l)))*( ponderer(d, c) - sqrt(marg_col(c)))))
        }
        res(l,c)=prob
      }}
    //println("hhhhh"+res)



    // marice de covariance.
    //var prob=0.0
    for ( l<- 0 until ponderer.rows ) {
      for ( c<- 0 until ponderer.rows ) {
        var prob1=0.0

        //  println(ponderer(l, c)+"  "+sqrt(marg_col(c)))$
        for ( d<- 0 until ponderer.cols) {
          prob1+=  (marg_ligne(d)*((ponderer(d, l) - sqrt(marg_col(l)))*( ponderer(d, c) - sqrt(marg_col(c)))))
        }
        cols_cov(l,c)=prob1
      }}
    //println("hhhhh"+cols_cov)



    val es = eigSym(res)
    val lambda = es.eigenvalues
    println("Les valeur Propre "+lambda)

   println("eeeeeeeee"+max(lambda)/trace(res))

    val es_c = eigSym(cols_cov)
    val lambda_c = es_c.eigenvalues
    //println("Les valeur Propre "+lambda_c)

    val vect_pro = es.eigenvectors
    println("Les vecteur b Propre "+vect_pro)




    val copm_princ=ponderer*vect_pro

    val comp_v = new DenseMatrix[Double](vect_pro.rows,vect_pro.cols)

    // val x=lambda.toDenseMatrix.delete(0,Axis._1).toDenseVector
    val tt=profil_cols.t*copm_princ
    println("Les vect   eurPropre "+ profil_cols.t)

    for(i<- 0 until lambda.length) {
      //val div=lambda(i)/marg_col(i)
      //comp_v(::,i):=vect_pro(::,i)*sqrt(div)
      //val div=1/sqrt(lambda(i))

      comp_v:=tt/sqrt(lambda(i))

    }


    println("Les vecteurPropre "+ comp_v)


    val dataset1: XYSeriesCollection = new XYSeriesCollection
    var series3: XYSeries=null  // la serie prend chaque foit des valeur differente (var)
    var series2: XYSeries=null  // la serie prend chaque foit des valeur differente (var)

    for(i<-0 until ligne.length) {// parcourir tout les label ligne pour cree les 15 serie( point)
      series3 = new XYSeries(ligne(i)) // cree la serie sous le nom de lâ€™individue, il exist 15 serie
      series2 = new XYSeries(colonne(i)) // cree la serie sous le nom de lâ€™individue, il exist 15 serie

      // ajouter les cordonner des point a chaque serie
      series3.add(copm_princ(i, (lambda.length-1)), copm_princ(i, (lambda.length-2)))
      series2.add(comp_v(i,(lambda.length-1)), comp_v(i, (lambda.length-2)))
      dataset1.addSeries(series2)

      //ajouter tout les serie dans lâ€™ensemble la collection des donneÃ©s dataset.
      dataset1.addSeries(series3)
    }
    //




    val chart4 = ChartFactory.createScatterPlot(
      "ArcTest", "X", "Y",dataset1,
      PlotOrientation.VERTICAL, true, true, false);
    val frame = new ChartFrame("First", chart4);
    frame.pack();
    frame.setVisible(true);




    /*var prob=0.0
    for ( c<- 0 until ponderer.cols ) {
      for ( l<- 0 until ponderer.cols ) {
        prob=0.0

        //  println(ponderer(l, c)+"  "+sqrt(marg_col(c)))$
        for ( d<- 0 until ponderer.rows) {
          val x = ponderer(d, c) - sqrt(marg_col(c))
          val y = ponderer(d, l) - sqrt(marg_col(l))

          prob += x*y*marg_ligne(d)

        }

        res(c,l)=prob

      }}
    println("x"+res+ "\n" )
    /// val covariance=corrcoeff(ponderer)
    val es1 = eigSym(res)
   val lambda1 = es1.eigenvalues
    println("Les valeur Propre "+lambda1)*/


  }
}
