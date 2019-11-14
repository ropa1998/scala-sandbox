package additions

object Perf {


  def perf(name: String)(op: => Unit): Unit = {
    val t0 = System.nanoTime()
    op
    val t1 = System.nanoTime()
    println("Block" + name + "executed in " + (t1 - t0) + " milliseconds.")
  }

}

object Main extends App {

  import Perf._

  val list: List[Int] = List(1, 2, 3, 4)
  perf("Reversing a list") {
    list.reverse
  }

}
