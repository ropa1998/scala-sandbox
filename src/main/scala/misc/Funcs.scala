package misc

object Funcs extends App {

  def mul(n1: Int, n2: Int): Int = n1 * n2

  mul(2, 2)

  def mul2(n1: Int)(n2: Int): Int = n1 * n2

  mul2(3)(4)

  val double1: Int => Int = mul2(2)(_)
  val double2: Function1[Int, Int] = mul2(2)(_)

  double1(4)
  double2(4)

  def f1(p: Int => Int): Unit = {
    println("p: " + p(5))
  }

  val myFunction: Int => Int = (n: Int) => n + 1
  val myFunction2: Function1[Int, Int] = (n: Int) => n + 1

  println(f1(myFunction))

  println(f1((n: Int) => n + 1))
  println(f1(n => n + 1))
  println(f1(_ + 1))

  // Partial Functions ---------------------------

  val partialFunction: PartialFunction[Any, Int] = {
    case n: Int =>
      n + 1
    case s: String =>
      s.length
  }

  def completeFunction(v: Any): Int = {
    v match {
      case n: Int =>
        n + 2
      case s: String =>
        s.length
    }
  }

  println(partialFunction(2))
  println(partialFunction("hola"))

  if (partialFunction.isDefinedAt(true)) {
    println(partialFunction(true))
  }


  val list = List("hello", 12, true, 4)

  val ints: List[Int] = list.collect(partialFunction)

  val int2 = list.collect {
    case str: String => str.length
    case n : Int => n + 1
  }

  println(ints)

  // Functions by value & by name

  def f1(p: Int): Unit = {
    println("f1.start")
    p
    p
    println("f1.end")
  }

  f1({
    println("Generating value for f1")
    10
  })

  println("---")

  def f2(p: () => Int): Unit = {
    println("f2.start")
    p()
    p()
    println("f2.end")
  }

  f2(() => {
    println("Generating value for f2")
    10
  })

  def f3(p: => Int): Unit = {
    println("f3.start")
    p
    p
    println("f3.end")
  }

  println("---")

  f3({
    println("Generating value for f3")
    10
  })

  val users = List("Juan", "Pepe")

  MyLog.enabled = false

  MyLog.logDebug("Usuarios conectados: " + users)
}

object MyLog {
  var enabled = false

  def logDebug(msg: => String): Unit = {
    if (enabled) {
      println("DEBUG: " + msg)
    }
  }
}