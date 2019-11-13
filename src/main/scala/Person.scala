class Person(name: String, age: Int, children: List[Person] = Nil) {


  def getPersonsWithChildrenWithNAgeAverage(age: Int): List[Person] = {

    if (this.children == Nil) {
      return children
    }

    val ageList: List[Int] = this.children.map(x => x.getAge())
    val average = ageList.sum / ageList.length

    if (average == age) {
      this :: children.flatMap(_.getPersonsWithChildrenWithNAgeAverage(average))
    } else {
      children.flatMap(_.getPersonsWithChildrenWithNAgeAverage(average))
    }
  }

  def findTwins(): List[Person] = {
    var twins: List[Person] = List()
    val zipped: List[(Person, Person)] = this.children.zip(this.children.drop(1))
    for (tuple <- zipped) {
      if (tuple._2.getAge() == tuple._1.getAge()) {
        twins = tuple._1 :: twins
        twins = tuple._2 :: twins
      }
    }
    twins
  }


  def getTwins(): List[Person] = {
    this.findTwins() ++ children.flatMap(_.getTwins())
  }


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

  def getChildless(): List[Person] = {
    if (this.children == Nil) {
      List(this)
    } else {
      children.flatMap(_.getChildless())
    }
  }


}


