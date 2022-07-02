package RDD

object RDDBasics extends App{
  println("Basics of RDD")
  // RDD : Resilient distributed dataset
  /*
  Resilient Distributed Dataset (RDD) is the fundamental data structure of Spark. They are immutable Distributed
  collections of objects of any type. As the name suggests is a Resilient (Fault-tolerant) records of data that
  resides on multiple nodes.
  */

  // Ref : https://www.xenonstack.com/blog/rdd-in-spark/
  // Ref : https://sparkbyexamples.com/apache-spark-rdd/spark-sortbykey-with-rdd-example/

  // The Spark Context :
  /* Created by our driver program
     It is responsible for making RDD's resilient and distributed
     It created RDD's
     The spark shell created a "sc" object for us
  */

  /*
  RDD can also be created from :
  JDBC
  Cassandra
  HBase
  Elasticsearch
  JSON, CSV, sequence files, object files, various compressed formats
  */

  /*
  Transforming RDD's :
  map
  flatmap
  filter
  distinct
  sample
  union, intersection, subtract, cartesian
  */

  /*
  RDD actions :
  collect
  count
  countByValue
  take
  top
  reduce
  ... and more ...
  */

  // Lazy evaluation : Nothing  actually happens in a driver program until an action is called


}
