package chobit.common

object Strings {

  /**
    * 判断字符串是否为空
    */
  def isBlank(source: String): Boolean = {
    if (null == source)
      return true
    val length = source.length
    if (0 == length)
      return true
    for (i <- 0 until length) {
      if (!Character.isWhitespace(source.charAt(i)))
        return false
    }
    true
  }


  /**
    * 判断字符串是否为空
    */
  def isNotBlank(source: String): Boolean = !isBlank(source)


  /**
    * 判断字符串是否全部为数字
    */
  def isNumeric(source: String): Boolean = {
    if (null == source)
      return false
    for (i <- 0 until source.length) {
      if (!Character.isDigit(source.charAt(i)))
        return false
    }
    true
  }


  /**
    * 判断字符串是否全部为数字
    */
  def isNotNumeric(source: String): Boolean = !isNumeric(source)

}
