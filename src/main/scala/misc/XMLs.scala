package misc

import scala.xml.Elem

object XMLs extends App {

  def html: Elem = <html>
    <body>
      Hello World
    </body>
  </html>


  def html(name: String): Elem = <html>
    <body>
      <h1>
        Hello
        {name}
      </h1>
    </body>
  </html>

  println(html("Juan"))
}
