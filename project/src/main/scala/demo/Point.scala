package demo

import breeze.stats.distributions._


  class Point(xc: Int, yc: Int) {
    var x: Int = xc
    var y: Int = yc

    def move(dx: Int, dy: Int) {
      x = x + dx
      y = y + dy
      println("Point x location : " + x);
      println("Point y location : " + y);
    }


    def show(xs: Int*) {
      for (x <- xs) {
        println(x)
      }
    }
  }




  object Demo {
    def main(args: Array[String]) {
      val pt = new Point(10,20);

      // Move to a new location
      pt.move(10, 10);

      pt.show(5);
    }
  }


