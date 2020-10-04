package chobit.common

import java.text.SimpleDateFormat
import java.util.Date

object DateKit {

  val DEFAULT_PATTERN: String = "yyyy-MM-dd HH:mm:ss"

  def parse(source: String): Date = parse(DEFAULT_PATTERN, source)

  def parse(pattern: String, source: String): Date = new SimpleDateFormat(pattern).parse(source)

  def format(source: Date): String = format(DEFAULT_PATTERN, source)

  def format(pattern: String, timeInMills: Long): String = format(pattern, new Date(timeInMills))

  def format(pattern: String, source: Date): String = new SimpleDateFormat(pattern).format(source)

}
