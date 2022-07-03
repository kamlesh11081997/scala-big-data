package SparkSQL

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession

/**
 * Doc : https://i.stack.imgur.com/3rF6p.png
 * Ref : https://spark.apache.org/docs/2.1.0/sql-programming-guide.html
 * Ref : https://www.analyticsvidhya.com/blog/2020/11/what-is-the-difference-between-rdds-dataframes-and-datasets/
 * RDD :       RDD is a distributed collection of data elements without any schema. RDD's can be converted to Dataset with
 *              .toDS()
 *
 * DataFrame : Dataframe is distributed collection of row objects. It automatically finds the schema of the dataset.
 *             Dataframe schema is inferred at the runtime.
 *
 * DataSet :   It is an extension of dataframe with more feature like type safety and object oriented interface. It
 *             also find the schema automatically by using the sql engine. Dataset schema is inferred at the compile time.
 *             Dataset is faster than RDD but slower than Dataframe.
 *
 */

object SparkSQLDataset {

  /*
  We create a SparkSession object instead of SparkContext when using Spark SQL / Datasets
  We can get a SparkContext from this session and use it to issue SQL queries on our dataset.
  We stop the session, when we are done.
   */

  case class Person(id:Int,name:String,age:Int,friends:Int)

  /* Our main function where the action happens */
  def main(args: Array[String]): Unit = {

    // setting the log level to only print errors
    Logger.getLogger("org").setLevel(Level.ERROR)

    // use SparkSession interface
    val spark = SparkSession
      .builder
      .appName("SparkSQL")
      .master("local[*]")
      .getOrCreate()

    // For implicit conversions like converting RDDs to DataFrames
    import spark.implicits._
    val schemaPeople = spark.read
      .option("header","true")
      .option("inferSchema","true")
      .csv("data/fakefriends.csv")
      .as[Person]  // if we don't use as[Person] while creating the schemaPeople, it is going to be a Dataframe
                  // no compile time schema inference would take place

    /*
    If we want to read data from different source (e.g. json)
    // val df = spark.read.json("examples/src/main/resources/people.json")
    // Ref : https://spark.apache.org/docs/2.1.0/sql-programming-guide.html
     */

    println(schemaPeople.getClass) // output : class org.apache.spark.sql.Dataset
    schemaPeople.printSchema()

    schemaPeople.createOrReplaceTempView("people")
    val teenagers = spark.sql("SELECT * FROM people WHERE age >= 13 and age <=19")
    val results=teenagers.collect()
    results.foreach(println)
    spark.stop()

  }

}
