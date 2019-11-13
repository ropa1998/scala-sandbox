object Break {

  private class BreakException extends RuntimeException
  private val breakException = new BreakException

  def breakable(op: => Unit): Unit = {
    try {
      op
    } catch {
      case e: BreakException =>
    }
  }

  def break: Unit = {
    throw breakException
  }
}

object BreakDemo extends App {

  import Break._

  var i = 0

  breakable {
    while (true) {
      i = i + 1
      println(i)
      if (i > 10) break
    }
  }
}