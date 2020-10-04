package chobit.common

object Args {

  def check(isSuccess: Boolean, message: String): Unit = {
    if (!isSuccess)
      throw new IllegalArgumentException(message)
  }

}
