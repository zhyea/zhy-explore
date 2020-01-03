package chobit.practice

import java.io.DataInputStream

import chobit.common.SparkUtils._
import chobit.common.HDFS
import org.apache.spark.SparkContext
import org.roaringbitmap.RoaringBitmap

object TypeQueryDriver {

  def main(args: Array[String]): Unit = {
    runJob2(start, context("CustomerGraph"))
  }


  def start(sc: SparkContext): Unit = {
    val bytes = HDFS.get("/chobit/files/71")
    val bitmap = deserialize(bytes)
    println(s"---------------------------------------${bitmap.getCardinality}")
  }


  /**
    * 反序列化
    */
  def deserialize(array: Array[Byte]): RoaringBitmap = {
    val bitmap = new RoaringBitmap()
    try {
      bitmap.deserialize(new DataInputStream(new java.io.InputStream {

        var c: Int = 0

        override def read(): Int = {
          val read = array(c) & 0XFF
          c += 1
          read
        }

        override def read(b: Array[Byte], off: Int, len: Int): Int = {
          System.arraycopy(array, c, b, off, len)
          c += len
          len
        }

      }))
    } catch {
      case e: Exception => throw new RuntimeException("Deserialize to RoaringBitmap error.", e)
    }

    bitmap
  }
}
