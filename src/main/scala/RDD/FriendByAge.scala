package RDD

import org.apache.log4j.{Level, Logger}
import org.apache.spark.SparkContext

/**
 * key,value RDD
 * spark can do following using key/value data:
 * reduceByKey() : combine the values with the same key using some function. rdd.reduceByKey((x,y)=>x+y) adds them up.
 * groupByKey() : group values with the same key
 * sortByKey() : sort RDD by key values
 * keys(), values() : create an RDD of just the keys, or just the values
 * join, rightOuterJoin, leftOuterJoin, cogroup, subtractByKey
 * with key/values data use : mapValues() and flatMapValues() if transformation doesn't affect the key
 */

object FriendByAge {


  def parseLine(line:String):(Int,Int)={
    //split by commas
    val fields=line.split(",")
    //extracting the age and numFriends fields and converting it to integer and returning a tuple
    (fields(2).toInt,fields(3).toInt)
  }

  def main(args: Array[String]): Unit = {
    Logger.getLogger("org").setLevel(Level.ERROR)

    val sc = new SparkContext("local[*]","FriendByAge")

    val lines = sc.textFile("data/fakefriends.csv")

    // using our parseLine function to convert to (age,numFriends) tuples
    val rdd=lines.map(parseLine)

    // Lots going on here
    // We are starting with an rdd of form (age,numFriends) where age is the KEY and numFriends is the value
    // Then we use reduceByKey to sum up the total numFriends and total instance for each age,by
    // adding together all the numFriends value and 1's respectively.

    val totalsByAge=rdd.mapValues(x=>(x,1)).reduceByKey((x,y) => (x._1+y._1,x._2+y._2))
    // so we have now tuples of (age,(totalFriends,totalInstance))
    //to compute the average we divide total friends/totalInstance for each age

    val averageByAge=totalsByAge.mapValues(x => x._1/x._2)

    // collect the result from the RDD ( This kicks off computing the DAG and actually executes the job)
    val result=averageByAge.collect()

    result.sorted.foreach(println)
    /* Output : (age,averagefriendsPerAge)
    (18,343)
    (19,213)
    (20,165)
     */

    /**
     * Note : we have done the processing using RDD (at very low level)
     * There are much easier way of doing this using SQL or by using datasets
     * Basically it's important to understand what's going on under the hood
     *
     */
  }

}
