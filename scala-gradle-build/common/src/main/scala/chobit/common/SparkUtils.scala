package chobit.common

import org.apache.spark.internal.Logging
import org.apache.spark.{SparkConf, SparkContext}


object SparkUtils extends Logging {


  /**
    * 执行作业
    */
  def runJob[C <: Config](job: (C, SparkContext) => Unit,
                          args: C,
                          context: SparkContext,
                          errorMsg: String = "任务执行失败"): Unit = {
    val startTime = System.currentTimeMillis()
    try {
      job(args, context)
    } catch {
      case ex: Throwable => ex.printStackTrace(); print(errorMsg); throw ex
    } finally {
      logInfo(s"Cost Time: ${System.currentTimeMillis() - startTime}")
    }
  }

  /**
    * 执行作业
    */
  def runJob2(job: (SparkContext) => Unit,
              context: SparkContext,
              errorMsg: String = "任务执行失败"): Unit = {
    val startTime = System.currentTimeMillis()
    try {
      job(context)
    } catch {
      case ex: Throwable => ex.printStackTrace(); print(errorMsg); throw ex
    } finally {
      logInfo(s"Cost Time: ${System.currentTimeMillis() - startTime}")
    }
  }


  /**
    * 初始化spark上下文
    */
  def context(appName: String, master: String = null): SparkContext = {
    val sparkConf = new SparkConf().setAppName(appName)
    sparkConf.set("spark.files.userClassPathFirst", "true")

    println("------------------------------>>>>" + sparkConf.get("spark.master"))

    if (null == master && null == sparkConf.get("spark.master", null))
      sparkConf.setMaster("local[*]")
    if (null != master)
      sparkConf.setMaster(master)

    new SparkContext(sparkConf)
  }
}
