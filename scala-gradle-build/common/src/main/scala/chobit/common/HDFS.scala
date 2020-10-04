package chobit.common

import java.io.{ByteArrayOutputStream, FilterOutputStream}
import java.nio.charset.Charset

import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.{FSDataInputStream, FileSystem, Path}
import org.apache.hadoop.io.IOUtils

object HDFS {

  /**
    * 判断路径在HDFS中是否存在
    */
  def exists(path: String): Boolean = exists(new Path(path))


  /**
    * 判断路径在HDFS中是否存在
    */
  def exists(path: Path): Boolean = fs.exists(path)


  /**
    * 执行删除操作
    */
  def delete(path: String): Boolean = fs.delete(new Path(path), true)


  /**
    * 列出指定目录下的所有文件
    */
  def listFiles(parentPath: String): Iterable[Path] = listFiles(new Path(parentPath))

  /**
    * 列出指定目录下的所有文件
    */
  def listFiles(parent: Path): Iterable[Path] = fs.listStatus(parent).map(f => f.getPath)


  /**
    * 保存文本信息到指定的目录
    */
  def saveText(path: String, text: String, overwrite: Boolean = true): Unit = {
    if (exists(path)) {
      if (overwrite) {
        delete(path)
      } else {
        throw new IllegalStateException(s"'$path' already exists.")
      }
    }
    val out = fs.create(new Path(path))

    new FilterOutputStream(out).write(text.getBytes(Charset.defaultCharset()))
  }

  /**
    * 打开文件，获取InputStream
    */
  def open(path: String): FSDataInputStream = open(new Path(path))


  /**
    * 打开文件，获取InputStream
    */
  def open(path: Path): FSDataInputStream = fs.open(path)

  /**
    * 从HDFS get文件
    */
  def get(path: String): Array[Byte] = {
    var in: FSDataInputStream = null
    var output: ByteArrayOutputStream = null
    try {
      in = open(path)
      output = new ByteArrayOutputStream
      val buff = new Array[Byte](100)
      var rc = in.read(buff, 0, 100)
      while (rc > 0) {
        output.write(buff, 0, rc)
        rc = in.read(buff, 0, 100)
      }

      output.toByteArray
    } finally {
      IOUtils.closeStream(in)
      IOUtils.closeStream(output)
    }
  }


  def getFs(conf: Configuration = config): FileSystem = FileSystem.get(conf)

  private val config = new Configuration()

  private val fs: FileSystem = getFs()


}
