package RDD

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext


/**
 * Objective : Find the minimum temperature by weather station
 * Filtering RDDs : Weather data example
 * filter() removes data from your RDD
 *
 */
object MinTemperature {

  def parseLine(line:String):(String,String,Float)={
    val fields=line.split(",")
    val stationID=fields(0)
    val entryType=fields(2)
    val temperature=fields(3).toFloat*0.1f*(9.0f/5.0f)+32.0f
    (stationID,entryType,temperature)
  }

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    // create a sparkcontext using every core of the local machine
    val sc=new SparkContext("local[*]","MinTemperatures")

    // Read each line of input data
    val lines=sc.textFile("data/1800.csv")

    // convert to (stationID,entryType,temperature) tuples
    val parsedLines=lines.map(parseLine)

    // removing(filtering) all except TMIN entries
    val minTemps=parsedLines.filter(x => x._2 =="TMIN") // for max temp : use : TMAX

    //convert to (stationID,temperature)
    val stationTemps=minTemps.map(x => (x._1,x._3.toFloat))

    // reduced by stationID retaining the minimum temperature found
    val minTempByStation = stationTemps.reduceByKey((x,y)=>Math.min(x,y)) // for max temp: use : Math.max(x,y)

    // collect format and print the result
    val results=minTempByStation.collect()

    for(result <- results.sorted){
      val station=result._1
      val temp=result._2
      val formatedTemp=f"$temp%.2f F"
      println(s"$station minimum temperature: $formatedTemp")
    }






  }
}
