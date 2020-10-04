package chobit.practice.other

object YieldTest extends App {

  println("--------------------")

  val result = for {
    num <- 0 to 10
    if (num % 2 == 0)
  } yield num

  println(result)


}
