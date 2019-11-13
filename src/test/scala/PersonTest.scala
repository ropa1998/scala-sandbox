import org.scalatest.FunSuite

class PersonTest extends FunSuite {

  test("Person.descendants empty works") {
    val juan: Person = new Person("Juan", 23, Nil)
    assert(juan.descendants == Nil)
  }

  test("Person.descendants with descendants works") {
    val juan: Person = new Person("Juan", 17, Nil)
    val pepe: Person = new Person("Pepe", 50, List(juan))
    assert(pepe.descendants == List(juan))
  }

  test("Person.descendants with more descendants works") {
    val juan: Person = new Person("Juan", 17, Nil)
    val pepe: Person = new Person("Pepe", 50, List(juan))
    val ricardo: Person = new Person("Ricardo", 70, List(pepe))
    assert(ricardo.descendants == List(pepe, juan))
  }

  test("Person.descendants with even more descendants works") {
    val juan: Person = new Person("Juan", 17, Nil)
    val pepe: Person = new Person("Pepe", 50, List(juan))
    val ernesto: Person = new Person("Ernesto", 17, Nil)
    val lujan: Person = new Person("Lujan", 50, List(ernesto))
    val ricardo: Person = new Person("Ricardo", 70, List(pepe, lujan))
    assert(ricardo.descendants == List(pepe, lujan, juan, ernesto))
  }

  test("Person.olderThan with one person works") {
    val juan: Person = new Person("Juan", 23, Nil)
    assert(juan.olderThan(10) == List(juan))
    assert(juan.olderThan(40) == Nil)
  }

  test("Person.youngerThan with one person works") {
    val juan: Person = new Person("Juan", 23, Nil)
    assert(juan.youngerThan(40) == List(juan))
    assert(juan.youngerThan(10) == Nil)
  }

  test("Person.youngerThan with more persons works") {
    val juan: Person = new Person("Juan", 17, Nil)
    val pepe: Person = new Person("Pepe", 50, List(juan))
    val ernesto: Person = new Person("Ernesto", 17, Nil)
    val lujan: Person = new Person("Lujan", 50, List(ernesto))
    val ricardo: Person = new Person("Ricardo", 70, List(pepe, lujan))
    val olderThanList: List[Person] = ricardo.olderThan(30)
    assert(olderThanList.contains(ricardo))
    assert(olderThanList.contains(pepe))
    assert(olderThanList.contains(lujan))
    assert(!olderThanList.contains(juan))
    assert(!olderThanList.contains(ernesto))
  }

  test("Person.getChildless with one childless person works") {
    val juan: Person = new Person("Juan", 17, Nil)
    assert(juan.getChildless() == List(juan))
  }

  test("Person.getChildless with one person with one child works") {
    val juan: Person = new Person("Juan", 17, Nil)
    val pepe: Person = new Person("Pepe", 50, List(juan))
    assert(pepe.getChildless() == List(juan))
  }

  test("Person.getChildless with various persons works") {
    val juan: Person = new Person("Juan", 17, Nil)
    val pepe: Person = new Person("Pepe", 50, List(juan))
    val ernesto: Person = new Person("Ernesto", 17, Nil)
    val lujan: Person = new Person("Lujan", 50, List(ernesto))
    val ricardo: Person = new Person("Ricardo", 70, List(pepe, lujan))
    val childlessList: List[Person] = ricardo.getChildless()
    assert(childlessList.contains(juan))
    assert(childlessList.contains(ernesto))
  }

  test("Person.getTwins with one childless person works") {
    val juan: Person = new Person("Juan", 17, Nil)
    assert(juan.getTwins() == Nil)
  }

  test("Person.getTwins with one person with a pair of twins works") {
    val juan: Person = new Person("Juan", 17, Nil)
    val ernesto: Person = new Person("Ernesto", 17, Nil)
    val pepe: Person = new Person("Pepe", 50, List(juan, ernesto))
    assert(pepe.getTwins().contains(juan))
    assert(pepe.getTwins().contains(ernesto))
  }

  test("Person.getPersonsWithChildrenWithNAgeAverage with one childless person") {
    val juan: Person = new Person("Juan", 17, Nil)
    assert(juan.getPersonsWithChildrenWithNAgeAverage(4) == Nil)
  }

  test("Person.getPersonsWithChildrenWithNAgeAverage with a family works") {
    val juan: Person = new Person("Juan", 4, Nil)
    val pepe: Person = new Person("Pepe", 4, Nil)
    val ernesto: Person = new Person("Ernesto", 17, List(juan, pepe))
    assert(ernesto.getPersonsWithChildrenWithNAgeAverage(4).contains(ernesto))
  }


}
