package demo

import java.math.BigDecimal

import breeze.linalg.{*, DenseMatrix, DenseVector, convert, eigSym, sum}
import breeze.numerics.sqrt
import breeze.stats.covmat
import breeze.stats.corrcoeff

object exo_td {
  def main(args: Array[String]): Unit = {
    val mat = DenseMatrix((68, 119, 26, 7), (15, 54, 14, 10), (5, 29, 14, 16), (20, 84, 17, 94))



    val mat2: DenseVector[Double] = DenseVector.zeros[Double](mat.cols);
    val proba: DenseMatrix[Double] = DenseMatrix.zeros[Double](mat.rows, mat.cols);
    val res: DenseMatrix[Double] = DenseMatrix.zeros[Double](mat.cols, mat.cols);
    val dd: DenseMatrix[Double] = DenseMatrix.zeros[Double](mat.rows, mat.cols);

    //  val marg_col:DenseVector[Double]= DenseVector.zeros[Double](mat.cols);

    // val marg_ligne:DenseVector[Double]= DenseVector.zeros[Double](mat.cols);
    val mat_d = convert(mat, Double)
    // println(mat_d)
    // calculer la matrice de probabile
    val total = sum(mat_d)
    //println(total)

    val proba2 = mat_d / convert(total, Double)

    val marg_col = sum(mat_d(::, *)).inner / total
    val marg_ligne = sum(mat_d(*, ::)) / total
    println( marg_col)
    println( marg_ligne)


    val profil_cols = proba2(*, ::) / marg_col
    //   println("Profil colonne '\n'"+ profil_cols.t)

    val profil_ligne = proba2(::,*) / marg_ligne
    println("Profil ligne '\n'"+  profil_ligne)
    println("Profil ligne '\n'"+  marg_col)


    val ponderer = profil_ligne(::,*) / sqrt(marg_ligne)
    println("Les valeur Propre " + ponderer)



    // marice de covariance.
    var prob=0.0
    for ( l<- 0 until ponderer.rows ) {
      for ( c<- 0 until ponderer.rows ) {
        var prob=0.0

        //  println(ponderer(l, c)+"  "+sqrt(marg_col(c)))$
        for ( d<- 0 until ponderer.cols) {
          prob= prob+ (marg_col(d)*((ponderer(l, d) - sqrt(marg_ligne(l)))*( ponderer(c, d) - sqrt(marg_ligne(c)))))
        }
        res(l,c)=prob
      }}
    println("hhhhh"+res)

    val es = eigSym(res)
    val lambda = es.eigenvalues
    println("Les valeur Propre "+lambda)
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
