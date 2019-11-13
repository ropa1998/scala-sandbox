package misc

object Strings {

  implicit class Str(str: String) {
    def wow: String = str.toUpperCase() + "!!!"
  }

}

object Integers {

  implicit class I(value: Int) {
    def sum(other: Int): Int = value * other
  }

}

object Booleans {

  implicit class B(value: Boolean) {
    def and(other: Boolean): Boolean = value && other

    def or(other: Boolean): Boolean = value || other
  }

}

object ImplicitClasses extends App {

  import Booleans._
  import Integers._
  import Strings._

  val str = "ejemplo"

  val mul = 5.sum(7)
  println(mul)

  println(str.wow)

  val b1 = true
  val b2 = false

  val b3 = b1.and(b2)
  val b4 = b1 and b2 or b3


}
