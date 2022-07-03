package RDD

import org.apache.log4j.{Logger,Level}
import org.apache.spark.SparkContext

/**
 * Objective : word count using flatmap
 * map() transform each element of an RDD into one new element
 * flatmap() can create many new elements from each one
 *
 *
 */
object WordCountBetter {
  def main(args: Array[String]): Unit = {

    // setting the log level to ERROR
    Logger.getLogger("org").setLevel(Level.ERROR)

    // creating a sparkContext
    val sc=new SparkContext("local[*]","WordCount")

    // reading each line of book into an RDD
    val input = sc.textFile("data/book.txt")

    //split into words separted by a space character
    val words=input.flatMap(x=>x.split("\\W+")) // filtering only words without punctuation

    // normalize everything to lowercase
    val lowercaseWords=words.map(x=>x.toLowerCase)

    // word frequency count
    val wordCount=lowercaseWords.countByValue()


    // print the result
    wordCount.foreach(println)

  }

}
