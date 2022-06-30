package RDD

import org.apache.log4j.{Level, Logger}
import org.apache.spark._

/**
 * Ref : RDD vs DataFrame vs Dataset
 * link : https://data-flair.training/blogs/apache-spark-rdd-vs-dataframe-vs-dataset/
 */

object RatingCounter {
  def main(args: Array[String]): Unit = {

    // setting the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc=new SparkContext("local[*]","RatingsCounter")

    val lines = sc.textFile("data/u.data")
    println(lines)
    lines.foreach(println)

    //convert each line to a string, split it out by tabs, and extract the third field.
    // (The file format is userID, movieID, rating, timestamp)
    val ratings = lines.map(x=>x.split("\t")(2))

    // count up how many times each value (ratings) occurs
    val resutls=ratings.countByValue()

    // sorted the resulting map of (rating,count) tuples by key
    val sortedResults = resutls.toSeq.sortBy(_._1)

    sortedResults.foreach(println)
    /**
    output :
    (1,6110)
    (2,11370)
    (3,27145)
    (4,34174)
    (5,21201)
     **/
  }

}
