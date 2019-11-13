class Person(name: String, age: Int, children: List[Person] = Nil) {

  def youngerThan(value: Int) = {
    this.getAll().partition(_.getAge() <= value)._1
  }


  def olderThan(value: Int) = {
    this.getAll().partition(_.getAge() >= value)._1
  }


  def descendants(): List[Person] = {
    children ++ children.flatMap(x => x.descendants())
  }

  def getAge(): Int = {
    this.age
  }

  private def getAll(): List[Person] = {
    this :: this.descendants()
  }

}


