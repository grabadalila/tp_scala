package linalg.matrix

import breeze.linalg.DenseVector
import org.jfree.chart.plot.PlotOrientation
import org.jfree.chart.{ChartFactory, ChartFrame}
import org.jfree.data.category.DefaultCategoryDataset

import scala.util.Random
import org.jfree.data.statistics._




object test2 {

  def main(args: Array[String]) {




val x:Array[Double]= Array.tabulate(1000){
    (i:Int)=>Random.nextGaussian()}
val dataset =new HistogramDataset()
    dataset.setType(HistogramType.RELATIVE_FREQUENCY)
    dataset.addSeries("Histogram",x,11)
    /*val chart =XYBarChart(dataset)
  chart.plot.getDomainAxis().setLabel("Value")
  chart.plot.getRangeAxis().setLabel("Frequency")
    chart.show()*/

    def matchTest(x: Int): String
    = x match {
      case 1 => "one"
      case 2 => "two"
      case _ => "many"
    }
  println(matchTest(3))  // many
    println( matchTest(1) ) // one

val dataset1= new DefaultCategoryDataset()

    val ligne = DenseVector(1.0,2.0,3.0)
    val r = DenseVector("pp","mm","lm")
    var a=0
    for(i<-0 until ligne.length) {
      println(ligne(i))
     dataset1.setValue(ligne(i), "mm",r(i) )
    }

    val frame=new ChartFrame("hhhhhh",
      ChartFactory.createBarChart("gggggg","hhhhhjkkkk",
      "jbjkhkjhkjh", dataset1,PlotOrientation.VERTICAL,
        true,false,true))
        frame.pack()
    frame.setVisible(true)
  }}
