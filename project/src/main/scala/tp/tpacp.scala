package tp

import breeze.linalg.{*, DenseMatrix, DenseVector, convert, eigSym, sum}
import breeze.stats.corrcoeff
import breeze.stats.mean
import org.jfree.chart.{ChartFactory, ChartFrame}
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.data.xy.{XYDataset, XYSeries, XYSeriesCollection}
object tpacp
{
  val mat = DenseMatrix((5.0,5.0,4.0,0.0,1.0,1.0), (4.0,3.0,3.0,2.0,2.0,1.0),(2.0,1.0,2.0,3.0,2.0,2.0), (5.0,3.0,5.0,3.0,4.0,3.0)
    ,(4.0,4.0,3.0,2.0,3.0,2.0),(2.0,0.0,1.0,3.0,1.0,1.0),
    (3.0,3.0,4.0,2.0,4.0,4.0),(1.0,2.0,1.0,4.0,3.0,3.0),(0.0,1.0,0.0,3.0,1.0,0.0),(2.0,0.0,1.0,3.0,1.0,0.0)
    ,(1.0,2.0,1.0,1.0,0.0,1.0),(4.0,2.0,4.0,2.0,1.0,2.0),(3.0,2.0,3.0,3.0,2.0,3.0)
    ,(1.0,0.0,0.0,3.0,2.0,2.0),(2.0,1.0,1.0,2.0,3.0,2.0))


  def main(args: Array[String]): Unit = {
    import java.awt.BasicStroke;
    import java.awt.Color;
    import java.awt.geom.Arc2D;
    import java.util.Random;
    import org.jfree.chart.ChartFactory;
    import org.jfree.chart.ChartFrame;
    import org.jfree.chart.JFreeChart;
    import org.jfree.chart.annotations.XYLineAnnotation;
    import org.jfree.chart.annotations.XYShapeAnnotation;
    import org.jfree.chart.plot.PlotOrientation;
    import org.jfree.chart.plot.XYPlot;
    import org.jfree.data.xy.XYDataset;
    import org.jfree.data.xy.XYSeries;
    import org.jfree.data.xy.XYSeriesCollection;

    /** @see http://stackoverflow.com/questions/6604211 */

    //  val  r = new Random();
    val PI = 360d;
    val X = 0;
    val Y = 0;
    val W = 2;
    val H = 2;
    val dataset1: XYSeriesCollection = new XYSeriesCollection
    var series3: XYSeries=null  // la serie prend chaque foit des valeur differente (var)
    for(i<-0 until 5) {// parcourir tout les label ligne pour cree les 15 serie( point)
      series3 = new XYSeries("rrr"+i) // cree la serie sous le nom de l’individue, il exist 15 serie
      // ajouter les cordonner des point a chaque serie
      series3.add(mat(i, 5), mat(i, 4))
      //ajouter tout les serie dans l’ensemble la collection des donneés dataset.
      dataset1.addSeries(series3)
    }
        val chart4 = ChartFactory.createScatterPlot(
          "ArcTest", "X", "Y",dataset1,
          PlotOrientation.VERTICAL, true, true, false);
        val plot1 = chart4.getXYPlot();
        val arc = new Arc2D.Double(X, Y, W, 2 * H, PI, PI, Arc2D.CHORD);
        plot1.addAnnotation(new XYShapeAnnotation(arc, new BasicStroke(5.0f), Color.blue));
        val frame = new ChartFrame("First", chart4);
        frame.pack();
        frame.setVisible(true);







  }
  def  createDataset():XYDataset= {
    val result = new XYSeriesCollection();
    val series = new XYSeries("ArcTest");
    series.add(0, 0);
    series.add(44, 44);
    result.addSeries(series);
    return result;
  }


}
