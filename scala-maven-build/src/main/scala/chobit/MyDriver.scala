
package chobit

import org.apache.spark.sql.SparkSession

object MyDriver {

  def main(args: Array[String]): Unit = {

    println(
      s"""
         Args of MyDriver:
         ${args.mkString("\n")}
        """)

    val path = "/brand/test/hp"
    val spark = SparkSession.builder().appName("MyApp").getOrCreate()
    val logData = spark.read.textFile(path).cache()
    val numHarry = logData.filter(line => line.contains("Harry")).count()
    val numVoldemort = logData.filter(line => line.contains("Voldemort")).count()

    println(
      s"""
          Lines with Harry: $numHarry,
          Lines with b: $numVoldemort
          """)
    spark.stop()

  }


}