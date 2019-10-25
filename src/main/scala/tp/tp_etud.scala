import breeze.linalg.{*, DenseMatrix, DenseVector, convert, eigSym, sum}
import breeze.numerics.sqrt
import breeze.plot.Figure
import breeze.stats.corrcoeff
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.{ChartFactory, ChartFrame}
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.data.xy.{XYSeries, XYSeriesCollection}

object main {

  def main(args: Array[String]): Unit = {


    val mat = DenseMatrix((5.0, 5.0, 4.0, 0.0, 1.0, 1.0), (4.0, 3.0, 3.0, 2.0, 2.0, 1.0), (2.0, 1.0, 2.0, 3.0, 2.0, 2.0), (5.0, 3.0, 5.0, 3.0, 4.0, 3.0)
      , (4.0, 4.0, 3.0, 2.0, 3.0, 2.0), (2.0, 0.0, 1.0, 3.0, 1.0, 1.0),
      (3.0, 3.0, 4.0, 2.0, 4.0, 4.0), (1.0, 2.0, 1.0, 4.0, 3.0, 3.0), (0.0, 1.0, 0.0, 3.0, 1.0, 0.0), (2.0, 0.0, 1.0, 3.0, 1.0, 0.0)
      , (1.0, 2.0, 1.0, 1.0, 0.0, 1.0), (4.0, 2.0, 4.0, 2.0, 1.0, 2.0), (3.0, 2.0, 3.0, 3.0, 2.0, 3.0)
      , (1.0, 0.0, 0.0, 3.0, 2.0, 2.0), (2.0, 1.0, 1.0, 2.0, 3.0, 2.0))

    val cor = corrcoeff(convert(mat, Double))
    println(cor)


  }

}