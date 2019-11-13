package akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

class Printer extends Actor {

  override def receive: Receive = {
    case msg =>
      println("Msg: " + msg)
  }
}

case class Count(value: Int)
case class Stats(total: Int)
case object GetStats

class Counter extends Actor {

  var total: Int = 0

  override def receive: Receive = {
    case Count(value) =>
      total += value

    case GetStats =>
      sender ! Stats(total)
  }
}

object AkkaApp extends App {

  val system = ActorSystem("mySystem")

  val printer: ActorRef = system.actorOf(Props[Printer], "printer")
  val counter: ActorRef = system.actorOf(Props[Counter], "counter")

//  printer.!("Hello")
//  printer ! "Hello"

  counter ! Count(10)
  counter ! Count(20)
  counter ! Count(5)

  counter ! GetStats

  counter.tell(GetStats, printer)

//  println(printer)
}
