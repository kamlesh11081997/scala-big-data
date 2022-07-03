package SparkSQL

import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions.{round, sum}
import org.apache.spark.sql.types.{DoubleType, IntegerType, StructType}

/**
 * Ref : https://sparkbyexamples.com/spark/spark-sql-aggregate-functions/
 */
object TotalSpentByCustomerSortedDataset {

  case class CustomerOrders(cust_id:Int,item_id:Int,amount_spent:Double)

  def main(args: Array[String]): Unit = {

    Logger.getLogger("org").setLevel(Level.ERROR)

    val spark=SparkSession
      .builder()
      .appName("TotalSpentByCustomer")
      .master("local[*]")
      .getOrCreate()

    // creating a schema when reading customer-orders
    val customerOrderSchema=new StructType()
      .add("cust_id",IntegerType,nullable = true)
      .add("item_id",IntegerType,nullable = true)
      .add("amount_spent",DoubleType,nullable = true)

    //Load up the data into spark dataset
    import spark.implicits._
    val customerDS= spark.read
      .schema(customerOrderSchema)
      .csv("data/customer-orders.csv")
      .as[CustomerOrders]

    customerDS.show()
    val totalByCustomer=customerDS
      .groupBy("cust_id")
      .agg(round(sum("amount_spent"),2))
      .alias("total_spent")

    totalByCustomer.show()

    val totalByCustomerSorted=totalByCustomer.sort("round(sum(amount_spent), 2)")
    totalByCustomerSorted.show(totalByCustomer.count.toInt) // passing the no. of row in show() to show the data

  }

}
