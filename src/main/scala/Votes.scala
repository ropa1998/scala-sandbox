import scala.io.Source

case class Vote(province: String, district: String, candidate: String, votes: Int)

object VoteUtil {

  def readVotes(path: String): List[Vote] = {
    val lines = Source.fromFile(path).getLines()
    var votes: List[Vote] = List()
    for (line <- lines) {
      val voteList = line.split(",")
      val vote = Vote(voteList(0), voteList(1), voteList(2), voteList(3).toInt)
      votes = vote :: votes
    }
    votes
  }


  def countVotes(votes: List[Vote]) = {
    votes.map(x => x.votes).sum
  }


  def getWinner(votes: List[Vote]) = {
    val gb = votes.groupBy(_.candidate)
    var finalMap: Map[String, Int] = Map()
    for (cand <- gb) {
      val totalVotes: Int = cand._2.map(x => x.votes).sum
      finalMap = finalMap + (cand._1 -> totalVotes)
    }
    println(finalMap)
    finalMap.maxBy(_._2)._1
  }


  def getBestProvinceFor(value: "A", votes: List[Vote]) = {

  }


}

object Votes extends App {

  import VoteUtil._

  val votes: List[Vote] = readVotes("/home/rodrigo/projects/scala-sandbox/src/main/scala/resources/votes.txt")
  println(votes)

  val totalVotes: Int = countVotes(votes)
  val winner = getWinner(votes)
  val bestProvinceForA = getBestProvinceFor("A", votes)

}
