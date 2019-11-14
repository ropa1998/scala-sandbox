package misc

object ValueClasses extends App {

  class Meter(val value: Double) extends AnyVal {
    def +(m: Meter): Meter = new Meter(value + m.value)
  }

  val m1 = new Meter(1.2)
  val m2 = new Meter(1.3)

  val m3 = m1 + m2

  val v1 = 1.2
  val v2 = 1.3

  val v3 = v1 + v2


}
