package misc

import scala.concurrent.Future
import scala.io.Source

object Futures extends App {

  import scala.concurrent.ExecutionContext.Implicits.global

  def downloadSync(url: String): String = {
    val source = Source.fromURL(url)
    val text = source.getLines().mkString("\n")
    source.close()

    text
  }

  def downloadAsync(url: String): Future[String] = {
    Future {
      val source = Source.fromURL(url)
      val text = source.getLines().mkString("\n")
      source.close()

      text
    }
  }

//  val f: Future[String] = Future {
//    println("Hola desde el futuro")
//    "hola"
//  }.map(_.toUpperCase())
//
//  f.onComplete {
//    case Success(value) =>
//      println("Resultado del futuro " + value)
//    case Failure(e) =>
//  }

  val c1: Future[String] = downloadAsync("https://scala-lang.org")
  val c2 = downloadAsync("https://scala-android.org/")
  val c3 = downloadAsync("https://www.wikipedia.org/")

//  c1.foreach { v =>
//    println("c1: " + v.length)
//  }
//
//  c2.foreach { v =>
//    println("c2: " + v.length)
//  }
//
//  c3.foreach { v =>
//    println("c3: " + v.length)
//  }

  val l1: Future[Int] = c1.map(_.length)
  val l2: Future[Int] = c2.map(_.length)
  val l3: Future[Int] = c3.map(_.length)

  val r1: Future[Int] = l1.flatMap { v1 =>
    l2.flatMap { v2 =>
      l3.map { v3 =>
        v1 + v2 + v3
      }
    }
  }

  val r2 = for {
    v1 <- l1
    v2 <- l2
    v3 <- l3
  } yield v1 + v2 + v3

  r2.foreach { v =>
    println("sum: " + v)
  }

  System.in.read()
}
