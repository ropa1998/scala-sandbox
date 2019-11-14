package misc

case class Conf(indent: Int, app: String)

object Logs {
  def printError(msg: String)(implicit conf: Conf): Unit = {
    val indentStr = " " * conf.indent
    println(s"$indentStr [ERROR] [App: ${conf.app}] $msg")
  }

  def printInfo(msg: String)(implicit conf: Conf): Unit = {
    val indentStr = " " * conf.indent
    println(s"$indentStr [INFO] [App: ${conf.app}] $msg")
  }

  implicit val conf: Conf = Conf(4, "Demo")
}

object ImplicitParams extends App {

  import Logs._

  val conf2: Conf = Conf(10, "Demo2")
  val conf1: Conf = Conf(5, "Demo")

  printInfo("Loading app")
  printInfo("Loading conf...")
  printError("Ups...")(conf2)

}
