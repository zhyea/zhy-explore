package chobit.common

import java.io.File
import java.util

import com.typesafe.config.ConfigFactory

import scala.collection.JavaConverters._

trait Config {


  private var config = ConfigFactory.load()


  def load(configFile: String):Unit = {
    config = ConfigFactory.parseFile(new File(configFile))
  }


  def getString(path: String, defaultValue: String): String = {
    if (!config.hasPath(path))
      defaultValue
    else
      config.getString(path)
  }

  def getString(path: String): String = getString(path, null)


  def getInt(path: String, defaultValue: Int): Int = {
    if (!config.hasPath(path))
      defaultValue
    else
      config.getInt(path)
  }


  def getInt(path: String): Int = getInt(path, 0)


  def getLong(path: String, defaultValue: Long): Long = {
    if (!config.hasPath(path))
      defaultValue
    else
      config.getLong(path)
  }


  def getLong(path: String): Long = getLong(path, 0L)


  def getAsMap(path: String): Map[String, String] = {
    if (config.hasPath(path))
      config.getAnyRef(path).asInstanceOf[util.HashMap[String, String]].asScala.toMap
    else null
  }
}
